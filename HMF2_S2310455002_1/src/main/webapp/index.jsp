<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>HMF Exercise 1</title>
</head>
<body>
<h1><%= "HMF Exercise 1" %>
</h1>
<br/>
<% if (request.getMethod().equals("GET")) { %>
    <!-- output form (interval, value) -->

    <form action="index.jsp" method="post">
        Param:<br/>
        <input name="param"/><br/>
        Timeout (in seconds):<br/>
        <input name="time"/><br/>
        <input type="submit"/>
    </form>

<% } else { // POST %>
    <!-- implementation like thread error servlet code -->

    <%! private final long DEFAULT_TIME = 10000; %>
    <%! private String instanceParameter; %>
    <%
        String localParameter;

        String timeStr = request.getParameter("time");
        long time;
        try {
            time = Long.parseLong(timeStr) * 1000;
        } catch (Exception e) {
            time = DEFAULT_TIME;
        }

        instanceParameter = request.getParameter("param");
        localParameter = instanceParameter;

        // "wait <time> milliseconds:"
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    %>

    <div>
        <strong>Instance Parameter: </strong> <%= instanceParameter %>
        <br>
        Local Parameter: <%=localParameter%>
    </div>
<% } %>
</body>
</html>