<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 답글쓰기</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/pmboard/pmreplyPro" method="post" name="f">
	<input type="hidden" name="num" value="${board.num}">
	<input type="hidden" name="ref" value="${board.ref}">
	<input type="hidden" name="refstep" value="${board.refstep}">
	<input type="hidden" name="reflevel" value="${board.reflevel}">
		<div class="w3-content">
			<table class="w3-table-all">
			<caption>${boardName} : 답글쓰기</caption>
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="re:${board.subject}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="15" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="w3-center">
					<input class="w3-button" type="submit" value="[답글등록]"></td>
			</tr>
			</table>
		</div>
	</form>
</body>
</html>