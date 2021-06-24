<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl 임포트  -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl중에서 fmt항목(시간관련)사용 목적 임포트  -->


<section>
	<div class="container-fluid">
		<div class="row">
			<!--lg에서 9그리드, xs에서 전체그리드-->
			<div class="col-lg-9 col-xs-12 board-table">
				<div class="titlebox">
					<p>자유게시판</p>
				</div>
				<hr>

				<!--form select를 가져온다 -->
				<form action="freeList">
					<!-- freeList로 폼 액션을 보냄 -->

					<div class="search-wrap">

						<button type="submit" class="btn btn-info search-btn">검색</button>
						<!-- 폼 안에 input 값을 서브밋으로 보냄  -->

						<input type="text" class="form-control search-input" name="searchName" value="${pageVO.cri.searchName }">
						<!-- 인풋 : 찾을 대상 검색 벨류값으로 이전 검색 기록 표시 -->

						<select class="form-control search-select" name="searchType">
							<!-- 셀렉트로 제목(타이틀)/내용(컨텐츠)/글쓴이(롸이터)/제목+내용(팉컨트)중에 선택하여 검색
                     마찬가지로 이전 검색 기록 표시-->
							<option value="title" ${pageVO.cri.searchType eq 'title' ? 'selected' : '' }>제목</option>
							<!-- pageVO 안에 있는 cri객체 안에 있는  searchType이 "title"로 설정되어 값이 넘어왔으면 select옵션중에 선택되어서 화면에 보여짐(selected) 아니면 화면에 안보여짐('') -->
							<option value="content" ${pageVO.cri.searchType eq 'content' ? 'selected': '' }>내용</option>
							<option value="writer" ${pageVO.cri.searchType eq 'writer' ? 'selected': ''}>작성자</option>
							<option value="titcont">제목+내용</option>
						</select>

					</div>

					<input type="hidden" name="pageNum" value="1">
					<!-- 검색했으면 무조건 1페이지부터 보여줌 -->
					<input type="hidden" name="amount" value="${pageVO.cri.amount }">
					<!-- 결과값의 크기값만큼 표시 -->
				</form>

				<!-- /////////////////////////////////////////////////////////////////////////////////////////////// -->

				<table class="table table-bordered">
					<thead>
						<tr>
							<th>번호</th>
							<th class="board-title">제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="vo" items="${list}">
							<!-- forEach는 jstl문중에서 리스트값을 하나씩 다 떄려박아보는 문법 
                        ${list}로 받아온 리스트 값을 vo에 담음.
                        ${list}을 누가 보냈냐? FreeBoardController에서 freeList 메소드가 model.addAttribute("list",list);로 리스트를 보냄-->
							<tr>
								<!-- 테이블 태그 가로줄-->
								<td>${vo.bno}</td>
								<td><a href="freeDetail?bno=${vo.bno}">${vo.title}</a></td>
								<!-- a 태그로 연결된 타이틀을 누르면  FreeBoardController에서  freeDetail로 슝날라감. 단 vo.bno값으로 현재 누른 글번호와 같이-->
								<td>${vo.writer}</td>

								<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd" /></td>\
								<!-- fmt:formatDate는 날짜 정보를 담고있는 객체를 적당히 변환시켜 출력하는 태그-->

								<td><fmt:formatDate value="${vo.updatedate}" pattern="yyy-MM-dd HH:mm:ss" /></td>

							</tr>
							<!-- 테이블 태그 가로줄 끝-->

						</c:forEach>

					</tbody>

				</table>


				<!--페이지 네이션을 가져옴-->
				<form action="freeList" name="pageForm">
					<!-- 누루면 freeList로 보냄  이 태그 이름은 pageForm -->

					<div class="text-center">
						<hr>
						<!--  선긋기 -->


						<ul class="pagination pagination-sm">
							<c:if test="${pageVO.prev}">
								<!--  jstl식 if 조건절  -->
								<li><a href="#" data-pagenum="${pageVO.startPage - 1}">이전</a></li>
								<!-- a태그에 HTML에서 data문법을 이용해  data-pagenum을 만든뒤 그 속에 {pageVO.startPage - 1}을 넣음
                        			 자바스크립트에서 data-*형식에 접근하기 위해선 dataset을 통해 가져올 수 있음
                      				 즉 HTML로 만든 data-pagenum정보를 
                          			 자바스크립트에서 dataset.pagenum으로 값을 가져올 수 있음.
                        -->
							</c:if>

							<c:forEach var="num" begin="${pageVO.startPage}" end="${pageVO.endPage}">
							<!--<!--
							Html에서 for문쓰기 => forEach begin end .  num변수로 받을 꺼임
							<c:forEach var="num" begin="${pageVO.startPage}" end="${pageVO.endPage}">
							 == for(int num = (begin); num <(end); num++) 
							 이거랑 똑같은 구문 --> -->		
								<li class="${pageVO.pageNum eq num ? 'active': '' }">
									<!-- pageVO에 pageNum 값이 위에서 순차적으로 들오올 num 값이 랑 같으면 선택가능--> <a href="#" data-pagenum="${num}">${num}</a> <!-- 순차적으로 들어온 num값을 data-paragrnum에 넣고 화면에 num값을 출력 -->
								</li>
							</c:forEach>

							<c:if test="${pageVO.next}">
								<li><a href="#" data-pagenum="${pageVO.endPage + 1}">다음</a></li>
							</c:if>
						</ul>

						<button type="button" class="btn btn-info" onclick="location.href='freeRegist'">글쓰기</button>
					</div>

					<input type="hidden" name="pageNum" value="${pageVO.cri.pageNum}"> 
					<input type="hidden" name="amount" value="${pageVO.cri.amount}"> 
					<input type="hidden" name="searchType" value="${pageVO.cri.searchType}"> 
					<input type="hidden" name="searchName" value="${pageVO.cri.searchName}">
				</form>

			</div>
		</div>
	</div>
</section>

<script>
	//페이지 처리
	//모든 a버튼을 눌렀을 때 a가 가지고 있는 pagnum 값을 가지고 form태그로 이동하도록 처리

	var pagination = document.querySelector(".pagination");
	//pagination객체를 자바스크립트 document.querySelector로 css패턴으로 찾은 (".pagination")(pagination클래스)에 연결
	
	pagination.onclick = function() {
		//pagination객체를 클릭하면 함수 실행
		event.preventDefault();
		//이벤트 일단정지
		if (event.target.tagName != 'A')
			return;

		console.log("빵빵레후");
		document.pageForm.pageNum.value = event.target.dataset.pagenum;
		document.pageForm.submit();
	}

	window.onload = function() {
		if (history.state == '')
			return;
		var msg = '<c:out value="${msg }" />';
		if (msg != '') {
			alert(msg);
			//기존기록을 삭제하고 새로운 기록을 추가(이렇게 변경된값은 history.state로 확인 가능)
			history.replaceState('', null, null); //브라우저 기록 컨트롤러 (추가할 데이터, 제목, url주소)
			console.log(history.state);
		}
	}
</script>

