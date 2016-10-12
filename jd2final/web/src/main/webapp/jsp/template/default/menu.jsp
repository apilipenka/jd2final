<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menu">
    <spring:message code="menu.title"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

    <ul>
        <li><a href="${pageContext.request.contextPath}/controller?command=USERROLELIST"><spring:message
                code="menu.edituserroles.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/user-list"><spring:message code="menu.editusers.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/controller?command=COMMANDLIST">Edit commands</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=USERROLECOMMANDLIST">Edit user role
            commands</a></li>
        <li class="divider"></li>
        <li><a href="${pageContext.request.contextPath}/bank-list"><spring:message code="menu.editbanks.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYLIST">Edit currencies</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/controller?command=AGREEMENTLIST">Edit agreement</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/account-list"><spring:message
                code="menu.editaccounts.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/controller?command=CARDLIST">Edit card</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/controller?command=EXCHANGERATELIST">Edit exchange
            rates</a></li>
        <li class="divider"></li>
        <li><a href="${logoutUrl}">Logout</a></li>

    </ul>


</div>