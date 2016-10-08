<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2>Currencies</h2>
    <ul>
        <li class="dropdown" style="list-style: none;">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<b class="caret"></b></a>
            <ul class="dropdown-menu">


                <c:forEach items="${sessionScope.user.getUserRole().getCommands()}" var="command">
                    <li><a href="${pageContext.request.contextPath}/${command.getUrl()}">${command.getLabel()}</a>
                    </li>
                </c:forEach>

                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
            </ul>
        </li>
    </ul>
    <!--Search Form -->
    <form action="controller" method="get" id="seachCurrencyForm" role="form">
        <input type="hidden" id="command" name="command" value="CURRENCYLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="currencyName" id="currencyName" class="form-control"
                   placeholder="Type the Name of the currency"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="controller" method="post" id="currencyForm" role="form">
        <input type="hidden" id="currencyID" name="currencyID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty currencyList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Code</td>
                        <td>MnemoCode</td>
                        <td>Name</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="currency" items="${currencyList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${currencyID == currency.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?currencyID=${currency.id}&command=EDITCURRENCY">${currency.id}</a>
                            </td>
                            <td>${currency.code}</td>
                            <td>${currency.mnemoCode}</td>
                            <td>${currency.name}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVECURRENCY&currencyID=${currency.id}"
                                   id="remove">
                                    <span class="glyphicon glyphicon-trash"/>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No currency found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/jsp/new-currency.jsp">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New currency</button>
    </form>
</div>
</body>
</html>