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
                <c:set var="command" value="ADDCOMMAND"/>
            </c:when>
            <c:when test="${command=='NEWCOMMAND'}">
                <c:set var="command" value="ADDCOMMAND"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="commandID" name="commandID"
               value="${userRole.id}">
        <h2>Command</h2>
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
                    value="${commando.getId()}"
                    readonly/>
            <label for="commandf" class="control-label col-xs-6">Command:</label>
            <input
                    type="text" name="commandf" id="commandf" class="form-control"
                    value="${commando.command}" required="true"/>
            <label
                    for="url" class="control-label col-xs-6">Url:</label>
            <input type="text" name="url" id="url"
                   class="form-control" value="${commando.url}" required="true"/>
            <label
                    for="label" class="control-label col-xs-6">Label:</label>
            <input type="text" name="label" id="label"
                   class="form-control" value="${commando.label}" required="true"/>
            <label
                    for="comment" class="control-label col-xs-6">Comment:</label>
            <input type="text" name="comment" id="comment"
                   class="form-control" value="${commando.comment}" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>