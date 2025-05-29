<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<html>
<head>
    <title>요일 계산 테스트</title>
</head>
<body>
    <h2>날짜로 요일 계산하기</h2>

    <form method="post">
        날짜 입력 (yyyy-MM-dd): <input type="text" name="inputDate" value="<%= request.getParameter("inputDate") == null ? "" : request.getParameter("inputDate") %>"/>
        <input type="submit" value="계산하기"/>
    </form>

    <%
        String inputDate = request.getParameter("inputDate");

        if (inputDate != null && !inputDate.trim().equals("")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(inputDate);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                String[] weekDays = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 (일요일) ~ 7 (토요일)

                String weekDayStr = weekDays[dayOfWeek - 1];
    %>
                <p><strong><%= inputDate %></strong>는 <strong><%= weekDayStr %></strong>입니다.</p>
    <%
            } catch (Exception e) {
    %>
                <p style="color:red;">입력 형식이 올바르지 않습니다. (예: 2025-05-16)</p>
    <%
            }
        }
    %>
</body>
</html>