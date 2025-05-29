<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="egovframework.com.utl.sim.service.EgovFileTool" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>디렉토리 복사 테스트</title>
</head>
<body>
    <h2>디렉토리 복사 도구</h2>

    <form method="post">
        원본 디렉토리 경로: <input type="text" name="sourcePath" size="50" value="${param.sourcePath != null ? param.sourcePath : ''}" /><br/><br/>
        대상 디렉토리 경로: <input type="text" name="targetPath" size="50" value="${param.targetPath != null ? param.targetPath : ''}" /><br/><br/>
        <input type="submit" value="복사 실행" />
    </form>

    <%
        String sourcePath = request.getParameter("sourcePath");
        String targetPath = request.getParameter("targetPath");
        String copyResult = "";

        if ("POST".equalsIgnoreCase(request.getMethod()) && sourcePath != null && targetPath != null) {
            boolean success = EgovFileTool.copyDirectory(sourcePath, targetPath);
            copyResult = success ? "디렉토리 복사 성공!" : "복사 실패! 경로를 확인하세요.";
        }
    %>

    <c:if test="${not empty param.sourcePath}">
        <hr/>
        <p><strong>결과:</strong> <span style="color:blue;"><%= copyResult %></span></p>
    </c:if>
</body>
</html>