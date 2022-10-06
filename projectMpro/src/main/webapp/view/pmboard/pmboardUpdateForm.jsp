<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 화면</title>
<script>
	function file_delete() {
		file_desc = document.getElementById("file_desc")
		document.f.file1.value = "";
		file_desc.style.display = "none";
	}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/pmboard/pmboardUpdatePro"
		method="post" enctype="multipart/form-data" name="f">
		<input type="hidden" name="num" value="${b.num}">
		<input type="hidden" name="file1" value="${b.file1}">
		<table class="w3-table-all">
			<caption>${boardName}수정</caption>
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="name" value="${b.name}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="${b.subject}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="15" name="content">${b.content}</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td style="text-align: Left">
				<c:if test="${b.file1 != null && b.file1 ne ''}">
					<div id="file_desc">${board.file1}
						<a href="javascript:file_delete()">[첨부파일 삭제]</a>
					</div>
				</c:if>
				<input type="file" name="uploadfile"></td>
			</tr>
			<tr>
				<td colspan="2"><a href="javascript:document.f.submit()">[게시물 수정]</a></td>
			</tr>
		</table>
	</form>
</body>
</html>