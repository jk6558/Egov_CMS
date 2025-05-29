<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>날짜 유효성 검사</title>
</head>
<body>
    <h2>날짜 유효성 검사 테스트</h2>

    <form method="get" >
        날짜 입력 (yyyyMMdd 형식): <input type="text" name="inputDate" value="${inputDate}" />
        <input type="submit" value="검사" />
    </form>

    <c:if test="${not empty inputDate}">
        <hr/>
        <p>입력한 날짜: <strong>${inputDate}</strong></p>
        <p>유효성 결과:
            <strong style="color:${isValid ? 'green' : 'red'};">
                ${isValid ? '유효한 날짜입니다.' : '유효하지 않은 날짜입니다.'}
            </strong>
        </p>
    </c:if>
</body>
</html>