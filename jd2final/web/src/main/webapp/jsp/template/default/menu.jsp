<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menu">
    Menu

    <c:url value="/j_spring_security_logout" var="logoutUrl" />

    <ul>
        <li>
            <spring:url value="/home" var="homeUrl" htmlEscape="true"/>
            <a href="${homeUrl}">Home</a>
        </li>
        <li>
            <spring:url value="/about" var="aboutUrl" htmlEscape="true"/>
            <a href="${aboutUrl}">About</a>
        </li>


                <li><a href="${pageContext.request.contextPath}/controller?command=USERROLELIST">Edit user roles</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/user-list">Edit users</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=COMMANDLIST">Edit commands</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=USERROLECOMMANDLIST">Edit user role
                    commands</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=BANKLIST">Edit banks</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYLIST">Edit currencies</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=AGREEMENTLIST">Edit agreement</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLIST">Edit account</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLISTWITHPAGINATION&pg=1&rpp=1">Edit
                        account with pagination</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CARDLIST">Edit card</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=EXCHANGERATELIST">Edit exchange
                    rates</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYEXTLIST">Ext Edit
                    currencies</a>
                </li>
                <li class="divider"></li>
                <li><a href="${logoutUrl}">Logout</a></li>

    </ul>


</div>