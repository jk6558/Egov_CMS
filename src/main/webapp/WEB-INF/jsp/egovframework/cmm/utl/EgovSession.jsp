<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>세션 테스트</title>
</head>
<body>
    <h2>세션 값 설정 및 조회</h2>

    <form method="get" >
        세션 이름: <input type="text" name="name" value="${param.name}" />
        세션 값: <input type="text" name="value" value="${param.value}" />
        <input type="submit" value="세션 설정" />
    </form>

    <hr/>
    <h3>현재 세션 값 목록</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>세션 이름</th>
            <th>세션 값</th>
        </tr>
        <%
            java.util.Enumeration<String> sessionNames = session.getAttributeNames();
            while (sessionNames.hasMoreElements()) {
                String key = sessionNames.nextElement();
                Object val = session.getAttribute(key);
        %>
        <tr>
            <td><%= key %></td>
            <td><%= val %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>