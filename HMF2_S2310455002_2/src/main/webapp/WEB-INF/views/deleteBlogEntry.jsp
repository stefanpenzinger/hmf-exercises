<%--
/**
 * Delete Entry Form.
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
<title>SimpleBlog Admin Delete</title>
</head>

<body>
<h1>SimpleBlog Administration: Delete</h1>

<form name="admin" action="admin" method="get">

Do you really want to delete this entry?<br><br>
<c:out value="${requestScope.blogEntry.contents }" escapeXml="true" />
<br><br>
<input type="hidden" name="entryId" value="${requestScope['blogEntry'].id }">
<input type="hidden" name="cmd" value="">

<input type="hidden" name="token" value='${requestScope["token"]}'>

<input type="button" value="Delete Blog Entry" onClick="admin.cmd.value='do-delete';admin.submit()">
<input type="button" value="Abort" onClick="admin.cmd.value='menu';admin.submit()">

</form>
</body>
</html>
