<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>날짜 형식 변환 테스트</title>
</head>
<body>
    <h2>날짜 형식 변환 테스트</h2>

    <form method="get" >
        날짜 입력 (yyyyMMdd 또는 yyyy-MM-dd): 
        <input type="text" name="inputDate" value="${inputDate != null ? inputDate : ''}" />
        <input type="submit" value="변환하기" />
    </form>

    <c:if test="${not empty result}">
        <hr/>
        <div style="color:blue;">
            <c:out value="${result}" escapeXml="false" />
        </div>
    </c:if>
</body>
</html>