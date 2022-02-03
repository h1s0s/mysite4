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
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px">
								<col>
								<col style="width: 70px">
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
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
					<!-- </form> -->
					<div id="listArea">
						<!-- 테이블을 넣을 영역 -->
					</div>
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
	//페이지가 dom을 생성하고 로딩이 되기 전일때
	$(document).ready(function() {//ready: 돔이 만들어진 후 페이지를 뿌리기 전
		console.log("리스트 요청(페이지 로딩전)");
		fetchList();//리스트 그리기
	});
	
	
	
	//저장버튼이 클릭될때
	$("#btnSubmit").on("click", function(){
		console.log("클릭");
		//폼에 있는 데이터를 모아야 한다.
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();
		
		//객체
		var guestbookVo = {
			name: name,
			password: password,
			content: content
		};
		console.log(guestbookVo);//확인용
		
		//요청
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/api/guestbook/write",// 주소.    
			type : "get",//get, post(어차피 차이 없음)
			contentType : "application/json",
			data : guestbookVo,
/* 			= {name: guestbookVo.name,
					password : guestbookVo.password,
					content : guestbookVo.content},  */
					//데이터를 보낼때 파라미터로 변함

			//응답받을때
			//dataType : "json",
			success : function(guestbookVo) {//json --> js로 변환되서 result에 담김
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo,'up');
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	//삭제팝업 버튼을 눌렀을때
	$("#listArea").on("click", ".btnDelPop", function(){//리스트는 나중에 생기기 때문에, 부모인 div를 지정하고, 위임해준다
		var $this = $(this);
		console.log($this);
		
		//회색 바탕
		//회색바탕 위에 팝업창을 만듬
		
	});
	
	//리스트 출력
	function fetchList(){
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/api/guestbook/list",// 주소.    
			type : "post",//get, post(어차피 차이 없음)
			//contentType : "application/json",
			//data : {name: "홍길동"},

			//응답받을때
			dataType : "json",
			success : function(guestbookList) {//json --> js로 변환되서 result에 담김
				/*성공시 처리해야될 코드 작성*/

				console.log(guestbookList);
			
				for(var i=0; i<guestbookList.length; i++){
					render(guestbookList[i], 'down'); // 방명록리스트 그리기
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	//리스트 그리기
	function render(guestbookVo, updown){
		var str = "";
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%">';
		str += '		<col style="width: 40%">';
		str += '		<col style="width: 40%">';
		str += '		<col style="width: 10%">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+guestbookVo.no+'</td>';
		str += '		<td>'+guestbookVo.name+'</td>';
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><button type="button" class="btnDelPop" ' + guestbookVo.no + '>삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(updown == 'down'){ 
			$("#listArea").append(str);
		}else if(updown =='up') {
			$("#listArea").prepend(str);
		}else {
			console.log("방향오류");
		}
		
	};
</script>
</html>