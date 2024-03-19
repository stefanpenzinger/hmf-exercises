<%--
/**
 * Administration View.
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
<title>SimpleBlog Administration</title>
<style>
table, td {border: 1px solid black}
</style>
</head>

<body>
<h1>SimpleBlog Administration</h1>

<form name="admin" action="admin" method="get">
<c:choose>
<c:when test="${empty requestScope['blogEntries']}">
<p>No entries available.</p>
</c:when>
<c:otherwise>
<table>
<c:forEach var="blogEntry" items='${requestScope["blogEntries"]}'>
<tr>
<td><input type="radio" name="entryId" value="${blogEntry.id}"></td>
<td>${blogEntry.id}</td>
<td><c:out value="${blogEntry.contents}" escapeXml="true" /></td>
<td>${blogEntry.timestamp}</td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
<p>
<input type="hidden" name="cmd" value="">
<input type="button" value="New" onClick="admin.cmd.value='add';admin.submit()">
<input type="button" value="Update" onClick="admin.cmd.value='update';admin.submit()">
<input type="button" value="Delete" onClick="admin.cmd.value='delete';admin.submit()">
</form>
<a href="..">Home</a>
</body>
</html>
