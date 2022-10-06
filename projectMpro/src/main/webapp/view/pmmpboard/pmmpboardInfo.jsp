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
			<td>${b.num}</td>
			<td>조회수</td>
			<td>${b.readcnt}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${b.name}</td>
			<td>작성일</td>
			<td>${b.regdate}</td>
		</tr>
		<tr>
			<td>img</td>
			<td colspan="3"><img
				src="<%=request.getContextPath()%>/view/pmmpboard/img/${b.file1}">
			</td>
		<tr>
			<td>글제목</td>
			<td colspan="3">${b.subject}</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td colspan="3">${b.content}</td>
		</tr>
		<tr>
			<td colspan="4">
			<input type ="button" value="답글"
			onclick="document.location.href='<%=request.getContextPath()%>/pmmpboard/pmmpreplyForm?num=${b.num}&ref=${b.ref}&refstep=${b.refstep}&reflevel=${b.reflevel}&pageNum=${pageNum}'"/>
			<input type ="button" value="글 수정"
			onclick="document.location.href='<%=request.getContextPath()%>/pmmpboard/pmmpboardUpdateForm?num=${b.num}'"/>
			<input type ="button" value="글 삭제"
			onclick="document.location.href='<%=request.getContextPath()%>/pmmpboard/pmmpboardDeleteForm?num=${b.num}'"/>
			<input type ="button" value="게시글 목록"
			onclick="document.location.href='<%=request.getContextPath()%>/pmmpboard/pmmpboardList?num=${b.num}'"/>
			</td>
	</table>
</body>
</html>