<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <div class="titlebox">
                       	 회원가입
                    </div>
                    <form action="joinForm" id="joinForm" method="post">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <div class="input-group"><!--input2탭의 input-addon을 가져온다 -->
                                <input type="text" class="form-control" id="userID" name="userID" placeholder="아이디를 (영문포함 4~12자 이상)">
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary" id="idCheck" >아이디중복체크</button>
                                </div>
                            </div>
                            <span id="msgId"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group"><!--기본 폼그룹을 가져온다-->
                            <label for="password">비밀번호</label>
                            <input type="password" class="form-control" name="userPW" id="userPw" placeholder="비밀번호 (영 대/소문자, 숫자 조합 8~16자 이상)">
                            <span id="msgPw"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group">
                            <label for="password-confrim">비밀번호 확인</label>
                            <input type="password" class="form-control" name="pwConfirm"  id="pwConfirm" placeholder="비밀번호를 확인해주세요.">
                             <span id="msgPw-c"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" name="userName" id="userName" placeholder="이름을 입력하세요.">
                        </div>
                        <!--input2탭의 input-addon을 가져온다 
                        <div class="form-group">
                            <label for="hp">휴대폰번호</label>
                            <div class="input-group">
				<select class="form-control phone1" id="userPhone1">
					<option>010</option>
					<option>011</option>
					<option>017</option>
					<option>018</option>
				</select> 
				<input type="text" class="form-control phone2" id="userPhone2" placeholder="휴대폰번호를 입력하세요.">
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary">본인인증</button>
                                </div>
                            </div>
                        </div>-->
			<div class="form-group email-form">
			  <label for="email">이메일</label><br>
			  <input type="text" class="form-control" id="userEmail1" name="userEmail1" placeholder="이메일">
			  <select class="form-control" id="userEmail2"  name="userEmail2">
			    <option>@naver.com</option>
			    <option>@daum.net</option>
			    <option>@gmail.com</option>
			    <option>@hanmail.com</option>
			    <option>@yahoo.co.kr</option>
			  </select>
			</div>
                        <!--readonly 속성 추가시 자동으로 블락-->
                        <div class="form-group">
                            <label for="addr-num">주소</label>
                            <div class="input-group">
                                <input type="text" class="form-control"  name ="addrZipNum" id="addrZipNum" placeholder="우편번호" readonly>
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary" onclick="goPopup()">주소찾기</button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="addrBasic" id="addrBasic" placeholder="기본주소">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control"  name="addrDetail" id="addrDetail" placeholder="상세주소">
                        </div>

                        <!--button탭에 들어가서 버튼종류를 확인한다-->
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-success btn-block" id="joinBtn">회원가입</button>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-info btn-block" onclick="location.href='userLogin'">로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

<script>
	$('#idCheck').click(function(){

        var userID = $("#userID").val();
        
        // console.log(userID);
        // console.log(userID.length);
        if(userID == '' || userID.length < 4 ){
            $("#userID").focus();
            alert("아이디형식을 준수하십쇼");
            return;
        }
        
        $.ajax({
            type: "post",
            url : "idCheck",
            contentType : "application/json",
            data : JSON.stringify({"userID":userID}),
            success : function(data)
            {
                console.log(data);
            	if (data == 0)
            	{
            		$("#userID").attr("readonly",true);
                    $("#msgId").html("사용가능 아이디입니다");
            	}
            	else{
                    $("#msgId").html("중복 아이디입니다");
            	}

            },
            error : function(status, error){
                alert("관리자 문의 부탁");
            }

        })

    })




$("#joinBtn").click(function(){
    if($("#userID").attr("readonly")!= 'readonly')
    {
        alert("중복검사는 필수입니다");
        $("#userID").focus();
        return;
    }
    else if($("#userPw").val() =='' || $("#userPw").val()  != $("#pwConfirm").val()){
        console.log($("#userPw").val());
        console.log("///////////////////////////////");
        console.log($("#pwConfirm").val());
        $("#pwConfirm").focus(); alert("비밀번호를 확인하셈");
    }
    else if($("#userName").val() ==''){
        $("#userName").focus();
        alert("이름은 필수");
    }
    else{
        $("#joinForm").submit();
    }

    //부모에 이벤트걸어서 일단 자식한테도 전부 전파
    $("#joinForm").keyup(function(event){
        // console.log(event.keyCode);
        if(event.keyCode != 13){ //엔터의 키값
            return; //함수 종료
        }
        $("#joinBtn").click();
    });

})
</script>




<script>

    //주소팝업
    function goPopup(){
        var pop = window.open("${pageContext.request.contextPath}/resources/pop/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
    }
	
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
        document.getElementById("addrBasic").value = roadAddrPart1;
        document.getElementById("addrDetail").value = addrDetail;
        document.getElementById("addrZipNum").value = zipNo;
}
</script>

























    <script>
        /*아이디 형식 검사 스크립트*/
        var id = document.getElementById("userID");
        id.onkeyup = function() {
            /*자바스크립트의 정규표현식 입니다*/
            /*test메서드를 통해 비교하며, 매칭되면 true, 아니면 false반*/
            var regex = /^[A-Za-z0-9+]{4,12}$/; 
            if(regex.test(document.getElementById("userID").value )) {
                document.getElementById("userID").style.borderColor = "green";
                document.getElementById("msgId").innerHTML = "아이디중복체크는 필수 입니다";

            } else {
                document.getElementById("userID").style.borderColor = "red";
                document.getElementById("msgId").innerHTML = "";
            }
        }
        /*비밀번호 형식 검사 스크립트*/
        var pw = document.getElementById("userPw");
        pw.onkeyup = function(){
            var regex = /^[A-Za-z0-9+]{8,16}$/;
             if(regex.test(document.getElementById("userPw").value )) {
                document.getElementById("userPw").style.borderColor = "green";
                document.getElementById("msgPw").innerHTML = "사용가능합니다";
            } else {
                document.getElementById("userPw").style.borderColor = "red";
                document.getElementById("msgPw").innerHTML = "";
            }
        }
        /*비밀번호 확인검사*/
        var pwConfirm = document.getElementById("pwConfirm");
        pwConfirm.onkeyup = function() {
            var regex = /^[A-Za-z0-9+]{8,16}$/;
            if(document.getElementById("pwConfirm").value == document.getElementById("userPw").value ) {
                document.getElementById("pwConfirm").style.borderColor = "green";
                document.getElementById("msgPw-c").innerHTML = "비밀번호가 일치합니다";
            } else {
                document.getElementById("pwConfirm").style.borderColor = "red";
                document.getElementById("msgPw-c").innerHTML = "비밀번호 확인란을 확인하세요";
            }
        }        
    </script>
