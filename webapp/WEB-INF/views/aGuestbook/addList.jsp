<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->
		<div id="container" class="clearfix">
			<!-- aside -->
			<jsp:include page="/WEB-INF/views/include/asideGuest.jsp"></jsp:include>
			<!-- /aside -->
			<div id="content">
				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/guest/add" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					</form>
					<%-- 					<c:forEach items="${requestScope.guestbookList}" var="vo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${vo.no}</td>
								<td>${vo.name}</td>
								<td>${vo.regDate}</td>
								<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no=${vo.no}">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${vo.content}</td>
							</tr>
						</table>
					</c:forEach> --%>
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	// 로딩되기전에 요청
	//ready : dom이 만들어지고 페이지에 뿌리기 전에
	$(document).ready(function() {
		console.log("리스트 요청(페이지 로딩전)");
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/api/gustbook/list",// 컨트롤러/메소드      
			type : "get",//get, post(어차피 차이 없음)
			//contentType : "application/json",
			//data : {name: "홍길동"},

			//응답받을때
			//dataType : "json",
			success : function(result) {
				/*성공시 처리해야될 코드 작성*/
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
</script>
</html>