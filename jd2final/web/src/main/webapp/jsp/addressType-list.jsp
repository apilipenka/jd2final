<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2>Address type</h2>
    <!--Search Form -->
    <form action="addressType" method="get" id="seachAddressTypeForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchAddressTypeByName">
        <div class="form-group col-xs-5">
            <input type="text" name="addressTypeName" id="addressTypeName" class="form-control"
                   placeholder="Type the Name of the address type"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="addressType" method="post" id="addressTypeForm" role="form">
        <input type="hidden" id="addressTypeID" name="addressTypeID">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty addressTypeList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Name</td>
                        <td>Description</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="addressType" items="${addressTypeList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${addressTypeID == addressType.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="addressType?addressTypeID=${addressType.id}&searchAction=searchAddressTypeById">${addressType.id}</a>
                            </td>
                            <td>${addressType.name}</td>
                            <td>${addressType.description}</td>
                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'removeAddressType';document.getElementById('addressTypeID').value = '${addressType.id}';

                                           document.getElementById('addressTypeForm').submit();">
                                <span class="glyphicon glyphicon-trash"/>
                            </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No address type found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/jsp/new-addressType.jsp">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New address type</button>
    </form>
</div>
</body>
</html>