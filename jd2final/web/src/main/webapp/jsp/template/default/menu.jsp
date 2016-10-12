<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menu">
    <spring:message code="menu.title"/>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

    <ul>
        <li><a href="${pageContext.request.contextPath}/userRole-list"><spring:message
                code="menu.edituserroles.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/user-list"><spring:message code="menu.editusers.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/command-list"><spring:message code="menu.editcommands.label"/></a></li>
        <li class="divider"></li>
        <li><a href="${pageContext.request.contextPath}/bank-list"><spring:message code="menu.editbanks.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/currency-list"><spring:message code="menu.editcurrencies.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/agreement-list"><spring:message code="menu.editagreements.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/account-list"><spring:message
                code="menu.editaccounts.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/card-list"><spring:message code="menu.editcards.label"/></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/exchangeRate-list"><spring:message code="menu.editexchangerates.label"/></a></li>
        <li class="divider"></li>
        <li><a href="${logoutUrl}">Logout</a></li>

    </ul>


</div>