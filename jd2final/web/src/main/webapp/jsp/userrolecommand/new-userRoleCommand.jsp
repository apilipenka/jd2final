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
                <c:set var="command" value="ADDUSERROLECOMMAND"/>
            </c:when>
            <c:when test="${command=='NEWUSERROLECOMMAND'}">
                <c:set var="command" value="ADDUSERROLECOMMAND"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="userRoleCommandID" name="userRoleCommandID"
               value="${userRoleCommand.id}">
        <h2>User role command</h2>
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
                    value="${userRoleCommand.getId()}"
                    readonly/>
            <label for="role"
                   class="control-label col-xs-6">Role:</label>
            <select name="role"
                    id="role" class="form-control">
                <c:forEach items="${roles}" var="roles">

                    <option value="${roles.getId()}" ${roles.getId() == userRoleCommand.userRoleID ? 'selected="selected"' : ''}>${roles.getDescription()}</option>

                </c:forEach>

            </select>
            <label for="commandf" class="control-label col-xs-6">Command:</label>
            <select name="commandf"
                    id="commandf" class="form-control">
                <c:forEach items="${commands}" var="commands">
                    <option value="${commands.getId()}" ${commands.getId() == userRoleCommand.commandID ? 'selected="selected"' : ''}>${commands.getComment()}</option>
                </c:forEach>
            </select>
            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>