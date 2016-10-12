<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>


<body>
<div class="container">
    <h2><spring:message code="commands.title"/></h2>

    <!--Search Form -->
    <form action="command-list" method="get" id="seachCommandForm" role="form">
        <input type="hidden" id="command" name="command" value="COMMANDLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="commandName" id="commandName" class="form-control"
                   placeholder="<spring:message code="placeholder.commandsearch"/>"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> <spring:message code="label.search"/>
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <spring:message code="${message}"/>
        </div>
    </c:if>
    <form action="command-list" method="get" id="commandForm" role="form">
        <input type="hidden" id="commandID" name="commandID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty commandList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td><spring:message code="label.command"/></td>
                        <td><spring:message code="label.url"/></td>
                        <td><spring:message code="label.label"/></td>
                        <td><spring:message code="label.comment"/></td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="commando1" items="${commandList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${commandID ==commando1.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="editcommand?commandID=${commando1.id}">${commando1.id}</a>
                            </td>
                            <td>${commando1.commandp}</td>
                            <td>${commando1.url}</td>
                            <td>${commando1.label}</td>
                            <td>${commando1.comment}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deletecommand?commandID=${commando1.id}"
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
                    <spring:message code="label.commandnotfound"/>


                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/insertCommand">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newcommand"/></button>
    </form>
</div>
</body>
</html>