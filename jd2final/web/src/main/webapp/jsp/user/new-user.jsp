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


    <s:form id="personForm" name="personForm" action="insertUser" modelAttribute="user" method="post">


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
        <h2><spring:message code="user.title"/></h2>
        <c:if test="${not empty error}">
            <div class="alert alert-success">
                <spring:message code="${error}"/>
            </div>
        </c:if>
        <c:if test="${not empty mesage}">
            <div class="alert alert-success">
                <spring:message code="${message}"/>
            </div>
        </c:if>
        <div class="form-group col-xs-6">
            <label for="id" class="control-label col-xs-6">Id:</label>
            <s:errors path="id" cssStyle="color: red"/>
            <s:input type="text" name="id" id="id" class="form-control" readonly="true" path="id"/>

            <label for="login" class="control-label col-xs-6"><spring:message code="label.Login"/></label>
            <s:errors path="login" cssStyle="color: red"/>
            <s:input type="text" name="login" id="login" class="form-control" path="login" required="true"/>

            <label
                    for="firstName" class="control-label col-xs-6"><spring:message code="label.firstname"/></label>
            <s:errors path="firstName" cssStyle="color: red"/>
            <s:input type="text" name="login" id="login" class="form-control" path="firstName" required="true"/>


            <label for="lastName" class="control-label col-xs-6"><spring:message code="label.lastname"/></label>
            <s:errors path="lastName" cssStyle="color: red"/>
            <s:input type="text" name="lastName" id="lastName" class="form-control" path="lastName" required="true"/>


            <label for="password" class="control-label col-xs-6"><spring:message code="label.password"/></label>
                <%-- <input type="text"  pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="${user.birthDate}" maxlength="10" placeholder="dd-MM-yyy" required="true"/> --%>
            <s:errors path="password" cssStyle="color: red"/>
            <s:input type="text" name="password" id="password" class="form-control" path="password" required="true"/>


            <label for="personalNumber" class="control-label col-xs-6"><spring:message code="label.personalnumber"/></label>
            <s:errors path="personalNumber" cssStyle="color: red"/>
            <s:input type="text" name="personalNumber" id="personalNumber" class="form-control" path="personalNumber"
                     required="true"/>


            <label for="birthDate" class="control-label col-xs-6"><spring:message code="label.birthdate"/></label>
            <s:errors path="birthDate" cssStyle="color: red"/>
            <s:input type="text" name="birthDate" id="birthDate" class="form-control" path="birthDate" required="true"/>


            <label for="role"
                   class="control-label col-xs-6"><spring:message code="label.role"/></label>
            <s:errors path="userRoleID" cssStyle="color: red"/>
            <s:select id="role" path="userRoleID"
                         class="form-control" required="true">
                <c:forEach items="${roles}" var="roles">

                    <option value="${roles.getId()}" ${roles.getId() == user.userRoleID ? 'selected="selected"' : ''}>${roles.getDescription()}</option>

                </c:forEach>
            </s:select>


            <br></br>
            <button type="submit" class="btn btn-primary  btn-md" name = "action1" value="Action1"><spring:message code="label.accept"/></button>
            <button type="cancel" class="btn btn-md" name = "action2" value="Action2"><spring:message code="label.cancel"/></button>
        </div>
    </s:form>


</div>
</body>
</html>