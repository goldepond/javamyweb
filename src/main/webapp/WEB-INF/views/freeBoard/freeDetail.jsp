<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<section>
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-md-9 write-wrap">
							<div class="titlebox">
								<p>상세보기</p>
							</div>

							<form action="freeModify" method="post">
								<div>
									<label>DATE</label>
									<p>
										<fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd" />
									</p>
								</div>
								<div class="form-group">
									<label>번호</label> <input class="form-control" name='bno' value='${vo.bno}' readonly>
								</div>
								<div class="form-group">
									<label>작성자</label> <input class="form-control" name='writer' value='${vo.writer}'
										readonly>
								</div>
								<div class="form-group">
									<label>제목</label> <input class="form-control" name='title' value='${vo.title}'
										readonly>
								</div>

								<div class="form-group">
									<label>내용</label>
									<textarea class="form-control" rows="10" name='content'
										readonly>${vo.content}</textarea>
								</div>

								<button type="submit" class="btn btn-primary">변경</button>
								<button type="button" class="btn btn-dark"
									onclick="location.href='freeList'">목록</button>
							</form>
						</div>
					</div>
				</div>
			</section>

			<section style="margin-top: 80px;">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-md-9 write-wrap">
							<form class="reply-wrap">
								<div class="reply-image">
									<img src="../resources/img/profile.png">
								</div>
								<!--form-control은 부트스트랩의 클래스입니다-->
								<div class="reply-content">
									<textarea class="form-control" rows="3" name="reply" id="reply"></textarea>
									<div class="reply-group">
										<div class="reply-input">
											<input type="text" class="form-control" placeholder="이름" name="replyID"
												id="replyID"> <input type="password" class="form-control"
												placeholder="비밀번호" name="replyPW" id="replyPW">
										</div>

										<button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
									</div>

								</div>
							</form>

							<!--여기에접근 반복-->
							<!-- 
				<div id="replyList">
					<div class='reply-wrap'>
						<div class='reply-image'>
							<img src='../resources/img/profile.png'>
						</div>
						<div class='reply-content'>
							<div class='reply-group'>
								<strong class='left'>honggildong</strong> 
								<small class='left'>2019/12/10</small> 
								<a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a> 
								<a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
							</div>
							<p class='clearfix'>여기는 댓글영역</p>
						</div>
					</div>
				</div>
				 -->
							<div id="comment_people">

							</div>
							<button type="button" class="btn btn-default btn-block" id="moreList">더보기</button>
						</div>
					</div>
				</div>
			</section>

			<!-- 모달 제이쿼리 모달 ("show")-->
			<div class="modal fade" id="replyModal" role="dialog">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
							<h4 class="modal-title">댓글수정</h4>
						</div>
						<div class="modal-body">
							<!-- 수정폼 id값을 확인하세요-->
							<div class="reply-content">
								<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
								<div class="reply-group">
									<div class="reply-input">
										<input type="hidden" id="modalRno">
										<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
									</div>
									<button class="right btn btn-info" id="modalModBtn">수정하기</button>
									<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
								</div>
							</div>
							<!-- 수정폼끝 -->
						</div>
					</div>
				</div>
			</div>





			<script>

				//뎃글 등록
				$(document).ready(function () {


					$("#replyRegist").click(function () {
						var bno = '${vo.bno}'; //글번호
						var reply = $("#reply").val();
						var replyID = $("#replyID").val();
						var replyPW = $("#replyPW").val();
						if (reply == '' || replyID == '' || replyPW == '') {
							alert("이름 비번 내용은 필수입니다");
							return;
						}
						//ajax = 비동기
						$.ajax({

							type: "post",
							url: "../reply/repltRegist",
							contentType: "application/json; charset=UTF-8",
							dataType: "json",
							data: JSON.stringify({ "bno": bno, "reply": reply, "replyID": replyID, "replyPW": replyPW }),
							success: function (data) {
								if (data == 1) {
									$("#reply").val("");
									$("#replyID").val("");
									$("#replyPW").val("");
									getList();
								}
								else {
									alert("등록에 실패했습니다");
								}
							},

							//실패시 실행함수
							error: function (status, error) {

								alert("등록에 실패했습니다");

							}

						});

					})
				});


				//=======================================================
				var page = 1;
				$("#moreList").click(function () {
					getList(++page, false);
				})

				//=========================================
				getList(1, true);//데이터 조회메서드

				function getList(pageNum, reset) {
					var bno = '${vo.bno}';
					$.getJSON("../reply/getList/" + bno + "/" + pageNum, function (data) {
						console.log(data);
						console.log("//////////////////////////");
						var total = data.total;
						var data = data.list;
						console.log(data);
						if (page * 20 >= total) {
							$("#moreList").css("display", "none");
						} else {
							$("#moreList").css("display", "block");
						}

						if (reset == true) {
							$("#comment_people").html("");
							page = 1;
						}

						for (var a = 0; a < data.length; a++) {
							$("#comment_people").append
								("<div id=" + "replyList'" + ">\
									<div class='reply-wrap'>\
										<div class='reply-image'>\
											<img src='../resources/img/profile.png'>\
										</div>\
											<div class="+ "'reply-content'" + ">\
												<div class='reply-group'>\
													<strong class='left'>" + data[a].replyID + " : 회원 아이디  " + "</strong>\
													<small class='left'>"+ data[a].timegap + ": 등록 시간" + "</small>\
													 <a href='"+ data[a].rno + "' class='right replyModify'><span class='glyphicon glyphicon-pencil '></span>수정</a>\
													  <a href='"+ data[a].rno + "' class='right replyDelete'><span class='glyphicon glyphicon-remove '></span>삭제</a>\
												</div>\
												<p class='clearfix'>"+ "내용 : " + data[a].reply + "</p>\
											</div>\
										</div>\
									</div>");
						}

					})

				}
				//에이젝스 실행이 더 늦게 완료가 되므로(비동기)
				//실제 이벤트 등록(아래 함수)이 먼저 일어나게 되서 정상적으로 동작하지 않음
				//
				//부모에 on함수를 이용해서 이벤트를걸고 이벤트를 a태그에 전파시켜서 사용해야됨

				$("#comment_people").on("click", "a", function () {
					event.preventDefault();

					var rno = $(this).attr("href");
					$("#modalRno").val(rno);


					if ($(this).hasClass("replyModify")) {
						$(".modal-title").html("댓글 수정");
						$("#replyModal").modal("show");

						$("#modalModBtn").css("display", "inline");
						$("#modalReply").css("display", "inline");

						$("#modalDelBtn").css("display", "none");
					}
					else if ($(this).hasClass("replyDelete")) {
						$(".modal-title").html("댓글 삭제");
						$("#modalModBtn").css("display", "none");
						$("#modalReply").css("display", "none");

						$("#modalDelBtn").css("display", "inline");
					}
					else {
						alert("잘못된 접근입니다")
					}

					$("#replyModal").modal("show");

				});


				$("#modalModBtn").click(function () {
					// alert("수정처리")
					var rno = $("#modalRno").val();
					var reply = $("#modalReply").val();
					var replyPW = $("#modalPw").val();
					if (rno == '' || reply == '' || replyPW == '') {
						alert("내용 비번은 필수");
						return;
					}

					$.ajax({
						type: "post",
						url: "../reply/update",
						contentType: "application/json; charset=UTF-8",
						data: JSON.stringify({ "rno": rno, "reply": reply, "replyPW": replyPW }),

						success: function (data) {

							if (data == 1) {
								$("#modalReply").val("");
								$("#modalPw").val("");
								$("#modalRNO").val("");
								$("#replyModal").modal("hide");
								getList(1, true);
							} else {
								alert("비번확인 오류");
								$("#modalPw").val("");
							}

						},
						error: function (data) {
							alert("수정 오류");
						}

					});
				})

				$("#modalDelBtn").click(function () {
					//모랄창에서 rno replay password값 얻어옴
					//ajax함수를이용해 post방식으로 reply/delete
					//필요한 값은 rest API애서 json

					//서버에서는 비번확인후 비번일치하면 삭제 진행
					var rno = $("#modalRno").val();
					var replyPW = $("#modalPw").val();


					if (rno == '' || replyPW == '') {
						alert("내용 비번은 필수");
						return;
					}
					$.ajax({
						type: "post",
						url: "../reply/delete",
						contentType: "application/json; charset=UTF-8",
						data: JSON.stringify({ "rno": rno, "replyPW": replyPW }),

						success: function (data) {
							if (data == 1) {
								$("#modalReply").val("");
								$("#modalPw").val("");
								$("#modalRNO").val("");
								$("#replyModal").modal("hide");
								getList(1, true);
							}
							else {
								alert("비번확인 오류");
							}

						},
						error: function (data) {
							alert("오류");
						}

					})

				});




			///======================================================

			</script>
