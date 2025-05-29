<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="egovframework.com.utl.sim.service.EgovFileTool" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>파일 작성자 추출 테스트</title>
</head>
<body>
    <h2>파일 작성자 추정 도구</h2>

    <form method="get">
        파일 이름: 
        <input type="text" name="fileName" value="${param.fileName != null ? param.fileName : ''}" />
        <input type="submit" value="작성자 확인" />
    </form>

    <c:if test="${not empty param.fileName}">
        <hr/>
        <%
            String fileName = request.getParameter("fileName");
            String author = EgovFileTool.extractAuthorFromFileName(fileName);
        %>
        <p><strong>입력 파일명:</strong> <%= fileName %></p>
        <p><strong>추정된 작성자:</strong> <span style="color:blue;"><%= author %></span></p>
    </c:if>
</body>
</html>