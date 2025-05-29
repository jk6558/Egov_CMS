<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>랜덤 숫자 생성 테스트</title>
</head>
<body>
    <h2>랜덤 숫자 생성기</h2>

    <form method="get" >
        최소값: <input type="text" name="min" value="${min != null ? min : ''}" />
        최대값: <input type="text" name="max" value="${max != null ? max : ''}" />
        <input type="submit" value="랜덤 숫자 생성" />
    </form>

    <c:if test="${not empty result}">
        <hr/>
        <div style="color:blue;">
            <strong>${result}</strong>
        </div>
    </c:if>
</body>
</html>