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
                <c:set var="command" value="ADDUSERROLE"/>
            </c:when>
            <c:when test="${command=='NEWUSER'}">
                <c:set var="command" value="ADDUSERROLE"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="userRoleID" name="userRoleID"
               value="${userRole.id}">
        <h2>User role</h2>
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
                    value="${userRole.getId()}"
                    readonly/>
            <label for="name" class="control-label col-xs-6">Name:</label>
            <input
                    type="text" name="name" id="name" class="form-control"
                    value="${userRole.name}" required="true"/>
            <label
                    for="description" class="control-label col-xs-6">Description:</label>
            <input type="text" name="description" id="description"
                   class="form-control" value="${userRole.description}" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>