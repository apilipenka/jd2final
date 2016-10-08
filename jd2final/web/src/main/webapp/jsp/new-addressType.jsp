<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <form action="addressType" method="post" role="form"
          data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="addAddressType"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="addressTypeID" name="addressTypeID"
               value="${addressType.id}">
        <h2>Address type</h2>
        <div class="form-group col-xs-6">
            <label for="id" class="control-label col-xs-6">Id:</label>
            <input
                    type="text" name="id" id="id" class="form-control"
                    value="${addressType.getId()}"
                    readonly/>
            <label for="name" class="control-label col-xs-6">Name:</label>
            <input
                    type="text" name="name" id="name" class="form-control"
                    value="${addressType.name}" required="true"/>
            <label
                    for="description" class="control-label col-xs-6">Description:</label>
            <input type="text" name="description" id="description"
                   class="form-control" value="${addressType.description}" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>