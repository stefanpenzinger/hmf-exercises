<%--
/**
 * List View.
 * 
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 * 
 * @author Rimbert Rudisch-Sommer
 */
--%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SimpleBlog List</title>
</head>

<body>
<h1>SimpleBlog List</h1>
<c:choose>
<c:when test="${empty requestScope['blogEntries']}">
<p>No entries available.</p>
</c:when>
<c:otherwise>
<c:forEach var="blogEntry" items='${requestScope["blogEntries"]}'>
<p> 
<strong>${blogEntry.timestamp}</strong><br>
<c:out value="${blogEntry.contents}" escapeXml="true" />
</p>
</c:forEach>
</c:otherwise>
</c:choose>
<a href="..">Home</a>
</body>
</html>
