<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>숫자 체크 테스트</title>
</head>
<body>
    <h2>숫자 여부 확인</h2>

    <form method="get" >
        입력 문자열: 
        <input type="text" name="input" value="${input != null ? input : ''}" />
        <input type="submit" value="확인" />
    </form>

    <c:if test="${not empty result}">
        <hr/>
        <div style="color:blue;">
            <strong>${result}</strong>
        </div>
    </c:if>
</body>
</html>