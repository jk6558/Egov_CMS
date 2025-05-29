<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="egovframework.com.utl.sim.service.EgovFileTool" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>파일/디렉토리 삭제 테스트</title>
</head>
<body>
    <h2>파일 또는 디렉토리 삭제</h2>

    <form method="post">
        삭제할 경로 입력: <input type="text" name="deletePath" size="60" value="${param.deletePath != null ? param.deletePath : ''}" />
        <input type="submit" value="삭제 실행" />
    </form>

    <%
        String deletePath = request.getParameter("deletePath");
        String deleteResult = "";

        if ("POST".equalsIgnoreCase(request.getMethod()) && deletePath != null) {
            boolean success = EgovFileTool.delPath(deletePath);
            deleteResult = success ? "삭제 성공!" : "삭제 실패! 경로를 확인하거나 권한을 확인하세요.";
        }
    %>

    <c:if test="${not empty param.deletePath}">
        <hr/>
        <p><strong>결과:</strong> <span style="color:red;"><%= deleteResult %></span></p>
    </c:if>
</body>
</html>