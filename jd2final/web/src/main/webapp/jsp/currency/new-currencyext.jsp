<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <form action="controller" method="post" role="form"
          data-toggle="validator">


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDCURRENCYEXT"/>
            </c:when>
            <c:when test="${command=='NEWCURRENCYEXT'}">
                <c:set var="command" value="ADDCURRENCYEXT"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="currencyID" name="currencyID"
               value="${currency.id}">
        <h2>Currency</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-success">
                    ${error}
            </div>
        </c:if>
        <c:if test="${not empty mesage}">
            <div class="alert alert-success">
                    ${mesage}
            </div>
        </c:if>
        <div class="form-group col-xs-6">
            <label for="id" class="control-label col-xs-6">Id:</label>
            <input
                    type="text" name="id" id="id" class="form-control"
                    value="${currency.getId()}"
                    readonly/>
            <label for="code" class="control-label col-xs-6">Code:</label>
            <input
                    type="text" name="code" id="code" class="form-control"
                    value="${currency.code}" required="true"/>
            <label for="mnemoCode" class="control-label col-xs-6">Mnemo code:</label>
            <input
                    type="text" name="mnemoCode" id="mnemocode" class="form-control"
                    value="${currency.mnemoCode}" required="true"/>
            <label for="name" class="control-label col-xs-6">Name:</label>
            <input
                    type="text" name="name" id="name" class="form-control"
                    value="${currency.name}" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
            <br></br>


            <c:if test="${not empty currency}">
                <h2>Exchange rates</h2>
                <!--Search Form -->
                <form action="controller" method="get" id="seachExchangeRateForm" role="form">
                    <input type="hidden" id="command" name="command" value="EXCHANGERATELIST">
                    <div class="form-group col-xs-5">
                        <input type="text" name="exchangeRateName" id="exchangeRateName" class="form-control"
                               placeholder="Type the currency mnemocode of the exchange rate"/>
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
                <form action="controller" method="post" id="exchangerateForm" role="form">
                    <input type="hidden" id="exchangeRateID" name="exchangeRateID">
                    <input type="hidden" id="action" name="action">
                    <c:choose>
                        <c:when test="${not empty exchangeRateExtList}">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Rate date</td>
                                    <td>Rate</td>
                                    <td>Currency</td>
                                    <td>Target currency</td>
                                    <td></td>
                                </tr>
                                </thead>
                                <c:forEach var="exchangeRate" items="${exchangeRateExtList}">
                                    <c:set var="classSucess" value=""/>
                                    <c:if test="${exchangeRateID == exchangeRate.id}">
                                        <c:set var="classSucess" value="info"/>
                                    </c:if>
                                    <tr class="${classSucess}">
                                        <td>
                                            <a href="controller?exchangeRateID=${exchangeRate.id}&command=EDITEXCHANGERATE&source=exchangeRatelist">${exchangeRate.id}</a>
                                        </td>
                                        <td>${exchangeRate.rateDate}</td>
                                        <td>${exchangeRate.rate}</td>
                                        <td>${exchangeRate.currencyMnemoCode}</td>
                                        <td>${exchangeRate.targetCurrencyMnemoCode}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/controller?command=REMOVEEXCHANGERATE&exchangeRateID=${exchangeRate.id}">
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
                                No exchange rate found matching your search criteria
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>
                <form action="${pageContext.request.contextPath}/jsp/new-exchangeRate.jsp?source=exchangeRateList">
                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">New exchange rate</button>
                </form>
            </c:if>

        </div>
    </form>
</div>
</body>
</html>