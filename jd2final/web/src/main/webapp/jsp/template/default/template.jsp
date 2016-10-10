<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Default tiles template</title>
    <style type="text/css">
        body {
            margin: 0px;
            padding: 0px;
            height: 100%;
            overflow: hidden;
        }

        .page {
            min-height: 100%;
            position: relative;
        }

        .header {
            padding: 10px;
            width: 100%;
            text-align: center;
        }

        .content {
            padding: 10px;
            padding-bottom: 20px; /* Height of the footer element */
            overflow: hidden;
        }

        .menu {
            padding: 50px 10px 0px 10px;
            width: 200px;
            float: left;
        }

        .body {
            margin: 50px 10px 0px 250px;
        }

        .footer {
            clear: both;
            position: absolute;
            bottom: 0;
            left: 0;
            text-align: center;
            width: 100%;
            height: 20px;
        }

    </style>
</head>
<body>
<div class="page">
    <tiles:insertAttribute name="header"/>
    <div class="content">
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="body"/>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>