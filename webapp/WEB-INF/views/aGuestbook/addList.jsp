<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">
		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<div id="container" class="clearfix">
			<!-- aside -->
			<c:import url="/WEB-INF/views/include/asideGuest.jsp"></c:import>
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
								<td colspan="4" class="text-center"><button id="btnSubmit2" type="submit">등록</button></td>
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
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->
	<!----------------------------------------------------------------------------------------------->
	<!-- 삭제모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호 입력창</h4>
				</div>
				<div class="modal-body">
					비밀번호: <input id="modalPassword" type="password" name="password" value=""> <input id="modalNo" type="text" name="no" value="">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- //삭제모달창 -->
</body>
<script type="text/javascript">
	// 로딩되기전에 요청
	//페이지가 dom을 생성하고 로딩이 되기 전일때
	$(document).ready(function() {//ready: 돔이 만들어진 후 페이지를 뿌리기 전
		console.log("리스트 요청(페이지 로딩전)");
		fetchList();//리스트 그리기
	});

	//저장버튼이 클릭될때(파라미터 방식)
	$("#btnSubmit").on("click", function() {
		console.log("클릭");
		//폼에 있는 데이터를 모아야 한다.
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();

		//객체
		var guestbookVo = {
			name : name,
			password : password,
			content : content
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
				render(guestbookVo, 'up');
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	//저장버튼이 클릭될때 json방식 요청
	$("#btnSubmit2").on("click", function() {
		console.log("클릭(json)");

		//폼에 있는 데이터를 모아야 한다.
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();

		//객체
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		};
		console.log(guestbookVo);//확인용

		//요청
		$.ajax({
			//요청할때
			url : "${pageContext.request.contextPath}/api/guestbook/write2",    
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),// 자바스크립트 객체를 제이슨으로 바꿔주는 함수
			
			dataType : "json",
			success : function(guestbookVo) {
				console.log(guestbookVo);
				render(guestbookVo, 'up');
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
	$("#listArea").on("click", ".btnDelPop", function() {//리스트는 나중에 생기기 때문에, 부모인 div를 지정하고, 위임해준다
		//데이타 수집
		var $this = $(this);
		var no = $this.data('no');

		//데이타 초기화
		$('#modalPassword').val('');
		$('#modalNo').val(no);
		$('#delModal').modal('show');
	});

	//모달의 삭제를 눌렀을때
	$("#modalBtnDel").on("click", function() {
		console.log("모달창 삭제버튼 클릭");

		var no = $("#modalNo").val();
		var password = $("#modalPassword").val();
		var delInfoVo = {
			no : no,
			password : password
		}

		console.log(delInfoVo);
		//ajax 요청 no password
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : delInfoVo,

			//dataType : "json",
			success : function(state) {
				if (state === 'success') {
					//해당 테이블 html 삭제
					$('#t' + no).remove();
					//모달창 닫기
					$('#delModal').modal('hide');
				} else {
					alert("비밀번호가 틀렸습니다.");//얼랏
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//리스트 출력
	function fetchList() {
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

				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], 'down'); // 방명록리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	//리스트 그리기
	function render(guestbookVo, updown) {
		var str = "";
		str += '<table id="t'+guestbookVo.no+'" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%">';
		str += '		<col style="width: 40%">';
		str += '		<col style="width: 40%">';
		str += '		<col style="width: 10%">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no + '</td>';
		str += '		<td>' + guestbookVo.name + '</td>';
		str += '		<td>' + guestbookVo.regDate + '</td>';
		str += '		<td><button type="button" class="btnDelPop" data-name="황일영" data-no="' + guestbookVo.no + '">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '	</tr>';
		str += '</table>';

		if (updown == 'down') {
			$("#listArea").append(str);
		} else if (updown == 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("방향오류");
		}
	};
</script>
</html>