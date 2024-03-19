<%--
/**
 * Update Entry Form.
 * 
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 * 
 * @author Stefan Penzinger
 */
--%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SimpleBlog Admin Update</title>
</head>

<body>
<h1>SimpleBlog Administration: Update</h1>

<form name="admin" action="admin" method="get">

<p>
    You are currently updating blog entry #${requestScope.blogEntry.id}
</p>

<label for="contents">Contents:</label><br>
<textarea name="contents" cols="80" rows="10" id="contents">${requestScope.blogEntry.contents}</textarea>
<br>

<input type=hidden name="cmd" value="">
<input type="hidden" name="token" value='${requestScope["token"]}'>
<input type="hidden" name="id" value="${requestScope.blogEntry.id}">

<input type="button" value="Update Blog Entry" onClick="admin.cmd.value='do-update';admin.submit()">
<input type="button" value="Abort" onClick="admin.cmd.value='menu';admin.submit()">

</form>
</body>
</html>
