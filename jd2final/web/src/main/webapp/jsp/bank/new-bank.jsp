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
    <s:form id="bankForm" name="bankForm" action="insertBank" modelAttribute="bank" data-toggle="validator">


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDBANK"/>
            </c:when>
            <c:when test="${command=='NEWBANK'}">
                <c:set var="command" value="ADDBANK"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="bankID" name="bankID"
               value="${bank.id}">
        <h2><spring:message code="bank.title"/></h2>
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

            <label for="name" class="control-label col-xs-6"><spring:message code="label.name"/></label>
            <s:errors path="name" cssStyle="color: red"/>
            <s:input type="text" name="name" id="name" class="form-control" path="name" required="true"/>



            <label
                    for="unn" class="control-label col-xs-6"><spring:message code="label.unn"/></label>
            <s:errors path="UNN" cssStyle="color: red"/>
            <s:input type="text" unn="name" id="unn" class="form-control" path="UNN" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md" name = "action1" value="Action1"><spring:message code="label.accept"/></button>
            <button type="cancel" class="btn btn-md" name = "action2" value="Action2"><spring:message code="label.cancel"/></button>
        </div>
    </s:form>
</div>
</body>
</html>