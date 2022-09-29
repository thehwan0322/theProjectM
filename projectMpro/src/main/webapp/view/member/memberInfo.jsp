<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form class="w3-container" action="memberUpdatePro.jsp" method="post"
		onsubmit="return inputCheck(this)">
		<table class="w3-table-all">
			<caption>MODEL2 로 구현한 회원정보수정</caption>
			<tr>
				<td rowspan="4" valign="bottom" width="100px"><img
					src="${pageContext.request.contextPath}/view/member/picture/${m.picture}"
					width="100" height="120" id="pic"><br></td>
				<td>아이디</td>
				<td>${sessionScope.id}</td>
			</tr>
			<tr>
				<td></td>
				<td>이름</td>
				<td>${m.name}</td>
			</tr>
			<tr>
				<td></td>
				<td>성별</td>
				<td>${m.gender == 1 ? "남" : "여"}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2">${m.tel}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td colspan="2">${m.email}</td>
			</tr>
			<tr>
				<td colspan="3" class="w3-center"><a class="w3-button w3-black"
					href="${pageContext.request.contextPath}/member/memberUpdateForm">회원정보수정</a>
					<a class="w3-button w3-black"
					href="${pageContext.request.contextPath}/member/memberDelete">회원탈퇴</a>
					<a class="w3-button w3-black"
					href="${pageContext.request.contextPath}/member/memberPassUpdate">비밀번호수정</a></td>
			</tr>
		</table>
	</form>
</body>
</html>