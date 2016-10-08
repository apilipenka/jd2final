<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Welcome user</title></head>
<body>
<h3>Welcome, manager</h3>
<hr/>
${user}, hello!
<hr/>
Links for user...<br/>
Debug info - session = ${sessionScope}
<br></br>
<ul>
    <li class="dropdown" style="list-style: none;">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>
        <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/controller?command=BANKLIST">Edit banks</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYLIST">Edit currencies</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/controller?command=AGREEMENTLIST">Edit agreement</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLIST">Edit account</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLISTWITHPAGINATION&pg=0&rpp=5">Edit account with pagination</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/controller?command=CARDLIST">Edit card</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/controller?command=EXCHANGERATELIST">Edit exchange
                rates</a></li>
            <li class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYEXTLIST">Ext Edit
                currencies</a>
            </li>
            <li class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
        </ul>
    </li>
</ul>
</body>
</html>