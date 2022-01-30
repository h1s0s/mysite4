<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>회원</h2>
					<ul>
						<li>회원정보</li>
						<li>로그인</li>
						<li>회원가입</li>
					</ul>
				</div>
				<!-- //aside -->

				<div id="content">

					<div id="content-head">
						<h3>회원가입</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>회원</li>
								<li class="last">회원가입</li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<!-- //content-head -->

					<div id="user">
						<div id="joinForm">
							<form action="${pageContext.request.contextPath}/user/join" method="get">

								<!-- 아이디 -->
								<div class="form-group">
									<label class="form-text" for="input-uid">아이디</label> <input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
									<button type="button" id="btnCheck">중복체크</button>
								</div>
								<div id="msg">
								</div>
								<!-- 비밀번호 -->
								<div class="form-group">
									<label class="form-text" for="input-pass">패스워드</label> <input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
								</div>

								<!-- 이름 -->
								<div class="form-group">
									<label class="form-text" for="input-name">이름</label> <input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
								</div>

								<!-- //나이 -->
								<div class="form-group">
									<span class="form-text">성별</span> <label for="rdo-male">남</label> <input type="radio" id="rdo-male" name="gender" value="male"> <label for="rdo-female">여</label> <input type="radio" id="rdo-female" name="gender" value="female">

								</div>

								<!-- 약관동의 -->
								<div class="form-group">
									<span class="form-text">약관동의</span> <input type="checkbox" id="chk-agree" value="" name=""> <label for="chk-agree">서비스 약관에 동의합니다.</label>
								</div>

								<!-- 버튼영역 -->
								<div class="button-area">
									<button type="submit" id="btn-submit">회원가입</button>
								</div>
							</form>
						</div>
						<!-- //joinForm -->
					</div>
					<!-- //user -->
				</div>
				<!-- //content  -->
			</div>
			<!-- //container  -->

			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

		</div>
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	//중복체크를 클릭할때
	$("#btnCheck").on("click", function(){
		console.log("클릭");
		//입력한 아이디 값을 가져온다.
		var id = $("#input-uid").val();
		var userVo ={
				id: id
		};
		
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/user/check",   
			type : "get",
			contentType : "application/json",
			data : userVo,

			//응답받을때
			//dataType : "json",
			success : function(userVo) {//json --> js로 변환되서 result에 담김
				/*성공시 처리해야될 코드 작성*/
				console.log(userVo);
				if(userVo.id == null){//중복 없음
					success();
				}else{//중복 있음
					fail();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	function success(){
		var str = "";
		str += '<p color="#0F0">사용 가능한 아이디 입니다.</p>';
		$("#msg").html(str);
	}
	function fail(){
		var str = "";
		str += '<p color="#F00">중복된 아이디가 있습니다.</p>';
		$("#msg").html(str);
	}
</script>
</html>