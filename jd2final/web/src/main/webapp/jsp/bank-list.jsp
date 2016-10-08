<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2>Banks</h2>
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
    <form action="controller" method="get" id="seachBankForm" role="form">
        <input type="hidden" id="command" name="command" value="BANKLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="bankName" id="bankName" class="form-control"
                   placeholder="Type the Name of the bank"/>
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
    <form action="controller" method="post" id="bankForm" role="form">
        <input type="hidden" id="bankID" name="bankID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty bankList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Name</td>
                        <td>UNN</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="bank" items="${bankList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${bankID == bank.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?bankID=${bank.id}&command=EDITBANK">${bank.id}</a>
                            </td>
                            <td>${bank.name}</td>
                            <td>${bank.UNN}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVEBANK&bankID=${bank.id}"
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
                    No bank found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/jsp/new-bank.jsp">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New bank</button>
    </form>
</div>
</body>
</html>