<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="egovframework.com.utl.sim.service.EgovMenuGov" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>메뉴 파일 생성 테스트</title>
</head>
<body>
    <h2>메뉴 파일 생성 테스트</h2>

    <form method="post">
        생성할 파일 경로 (예: C:/egov/menu/sampleMenu.txt):<br/>
        <input type="text" name="filePath" size="80" value="${param.filePath}" /><br/><br/>

        메뉴 내용:<br/>
        <textarea name="menuContent" rows="10" cols="80"><c:out value="${param.menuContent}" /></textarea><br/><br/>

        <input type="submit" value="메뉴 파일 생성" />
    </form>

    <%
        String filePath = request.getParameter("filePath");
        String menuContent = request.getParameter("menuContent");
        String resultMessage = "";

        if ("POST".equalsIgnoreCase(request.getMethod()) && filePath != null && menuContent != null) {
            boolean result = EgovMenuGov.createMenuFile(filePath, menuContent);
            resultMessage = result ? "파일이 성공적으로 생성되었습니다." : "파일 생성에 실패했습니다.";
        }
    %>

    <c:if test="${not empty param.filePath}">
        <hr/>
        <p><strong>결과:</strong> <span style="color:blue;"><%= resultMessage %></span></p>
    </c:if>
</body>
</html>