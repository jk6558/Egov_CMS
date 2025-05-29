<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>문자열 인덱스 테스트</title>
</head>
<body>
    <h2>문자열 인덱스 찾기</h2>

    <form method="get" >
        전체 문자열: <input type="text" name="fullText" size="50" value="${fullText != null ? fullText : ''}" /><br/><br/>
        찾을 문자열: <input type="text" name="keyword" value="${keyword != null ? keyword : ''}" />
        <input type="submit" value="위치 찾기" />
    </form>

    <c:if test="${not empty result}">
        <hr/>
        <div style="color:green;">
            <c:out value="${result}" />
        </div>
    </c:if>
</body>
</html>