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




    <s:form id="personForm" name="personForm" action="" modelAttribute="user" method="post">


        <c:if test="${empty action}">
            <c:set var="action" value="addUser"/>
        </c:if>


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDUSER"/>
            </c:when>
            <c:when test="${command=='NEWUSER'}">
                <c:set var="command" value="ADDUSER"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="userID" name="userID" value="${user.id}">
        <input type="hidden" id="source" name="source" value="${source}">
        <h2>User</h2>
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
            <s:errors path="id" cssStyle="color: red" />
            <s:input type="text" name="id" id="id" class="form-control" readonly="true"  path="id"/>

            <label for="login" class="control-label col-xs-6">Login:</label>
            <s:errors path="login" cssStyle="color: red" />
            <s:input type="text" name="login" id="login" class="form-control" path="login" required="true"/>

            <label
                    for="firstName" class="control-label col-xs-6">First name:</label>
            <s:errors path="firstName" cssStyle="color: red" />
            <s:input type="text" name="login" id="login" class="form-control" path="firstName" required="true"/>


            <label for="lastName" class="control-label col-xs-6">Last
                name:</label>
            <s:errors path="lastName" cssStyle="color: red" />
            <s:input type="text" name="lastName" id="lastName" class="form-control" path="lastName" required="true"/>



            <label for="password" class="control-label col-xs-6">Password:</label>
            <%-- <input type="text"  pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="${user.birthDate}" maxlength="10" placeholder="dd-MM-yyy" required="true"/> --%>
            <s:errors path="password" cssStyle="color: red" />
            <s:input type="text" name="password" id="password" class="form-control" path="password" required="true"/>




            <label for="personalNumber" class="control-label col-xs-6">Personal
                number:</label>
            <s:errors path="personalNumber" cssStyle="color: red" />
            <s:input type="text" name="personalNumber" id="personalNumber" class="form-control" path="personalNumber" required="true"/>



            <label for="birthDate" class="control-label col-xs-6">Birth date:</label>
            <s:errors path="birthDate" cssStyle="color: red" />
            <s:input type="text" name="birthDate" id="birthDate" class="form-control" path="birthDate" required="true"/>



            <label for="role"
                   class="control-label col-xs-6">Role:</label>
            <br></br>
            <s:errors path="userRoleID" cssStyle="color: red" />
            <form:select path="userRoleID" id="role" name="role" class="form-control">
                <form:options items="${roles}" />
            </form:select>



            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </s:form>
</div>
</body>
</html>