<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <form action="Servlet1" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="entity" name="entity" value="${entity}">
        <input type="hidden" id="entityID" name="entityID"
               value="${entityid}">
        <h2>${entityName}</h2>

        <div class="form-group col-xs-6">

            <c:forEach var="i" items="${list}">
                <label for="${i.id}" class="control-label col-xs-6">${i.label}:</label>
                <input type="text" name="${i.name}" id="${i.id}" class="form-control" value="${i.value}" ${i.options}
                       }/>
            </c:forEach>

            <br/>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>