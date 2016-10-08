<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Welcome user</title></head>
<body>
<h3>Welcome, user</h3>
<hr/>
${user}, hello!
<hr/>
Links for user...<br/>
Debug info - session = ${sessionScope}
<a href="controller?command=logout">Logout</a>
</body>
</html>