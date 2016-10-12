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
    <s:form id="commandForm" name="commandForm" action="insertCommand" modelAttribute="command" data-toggle="validator">


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
               value="${command.id}">
        <h2>Command</h2>
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

            <label for="commandf" class="control-label col-xs-6"><spring:message code="label.command"/></label>
            <s:errors path="commandp" cssStyle="color: red"/>
            <s:input type="text" name="commandf" id="commandf" class="form-control" path="commandp" required="true"/>


            <label
                    for="url" class="control-label col-xs-6"><spring:message code="label.url"/></label>
            <s:errors path="url" cssStyle="color: red"/>
            <s:input type="text" name="url" id="url" class="form-control" path="url" required="true"/>



            <label
                    for="label" class="control-label col-xs-6"><spring:message code="label.label"/></label>
            <s:errors path="label" cssStyle="color: red"/>
            <s:input type="text" name="label" id="label" class="form-control" path="label" required="true"/>


            <label
                    for="comment" class="control-label col-xs-6"><spring:message code="label.comment"/></label>
            <s:errors path="comment" cssStyle="color: red"/>
            <s:input type="text" name="comment" id="comment" class="form-control" path="comment" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md" name = "action1" value="Action1"><spring:message code="label.accept"/></button>
            <button type="cancel" class="btn btn-md" name = "action2" value="Action2"><spring:message code="label.cancel"/></button>
        </div>
    </s:form>
</div>
</body>
</html>