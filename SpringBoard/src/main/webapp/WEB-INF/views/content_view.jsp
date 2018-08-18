<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="modify" method="post">
	<input type="hidden" name="bId" value="${content_view.bId }">
	<table width="500" border="1" cellpadding="0">
		<tr>
			<td>번호</td>
			<td>${content_view.bId }</td>
		</tr>
		<tr>
			<td>히트</td>
			<td>${content_view.bHit }</td>
		</tr>	
		<tr>
			<td>이름</td>
			<td><input type="text" name="bName" value="${content_view.bName }"></td>
		</tr>	
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" value="${content_view.bTitle }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" name="bContent">${content_view.bContent }</textarea></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" value="수정">
				<input type="button" onclick="location.href='list'" value="목록보기">
				<input type="submit" value="삭제"> 
				<input type="submit" value="답변"> 
			</td>			
		</tr>		
	</table>
</form>
</body>
</html>