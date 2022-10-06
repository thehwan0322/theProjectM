<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="w3-table-all">
		<tr>
			<td>글번호</td>
			<td>${pmb.num}</td>
			<td>조회수</td>
			<td>${pmb.readcnt}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${pmb.name}</td>
			<td>작성일</td>
			<td>${pmb.regdate}</td>
		</tr>
		<tr>
			<td>img</td>
			<td colspan="3"><img
				src="<%=request.getContextPath()%>/view/pmboard/pmimg/${pmb.file1}">
			</td>
		<tr>
			<td>글제목</td>
			<td colspan="3">${pmb.subject}</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td colspan="3">${pmb.content}</td>
		</tr>
		<tr>
			<td colspan="4">
			<input type ="button" value="답글"
			onclick="document.location.href='<%=request.getContextPath()%>/pmboard/pmreplyForm?num=${pmb.num}&ref=${pmb.ref}&refstep=${pmb.refstep}&reflevel=${b.reflevel}&pageNum=${pageNum}'"/>
			<input type ="button" value="글 수정"
			onclick="document.location.href='<%=request.getContextPath()%>/pmboard/pmboardUpdateForm?num=${pmb.num}'"/>
			<input type ="button" value="글 삭제"
			onclick="document.location.href='<%=request.getContextPath()%>/pmboard/pmboardDeleteForm?num=${pmb.num}'"/>
			<input type ="button" value="게시글 목록"
			onclick="document.location.href='<%=request.getContextPath()%>/pmboard/pmboardList?num=${pmb.num}'"/>
			</td>
	</table>
</body>
</html>