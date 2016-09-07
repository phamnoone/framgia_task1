<%-- 
    Document   : home
    Created on : Aug 26, 2016, 1:38:57 PM
    Author     : thanhpham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
    </head>
    <body>

        <h1><a href="<%=request.getContextPath()%>/userslist.html">${greeting}</a></h1>
        <a href="<%=request.getContextPath()%>/index.html">index</a>s
        <form:form method="GET" action="/task1/userslist/search.html">
            <input name="key" type="text" value=""/>
            <input type="submit" class="btn btn-default" value="Seach"/>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addModal" >Add</button>
        </form:form>
        <br> <div>${error}</div> 　  <br>  
        <c:if test="${not empty listUsers}">

            <ul>
                <table >
                    <tr>
                        <th>id</th>
                        <th>name</th> 
                        <th>action</th>
                    </tr>
                    <c:forEach var="listValue" items="${listUsers}">

                        <tr>
                            <th>${listValue.id}</th>
                            <th>${listValue.name}</th> 
                            <th>  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#detailModal" data-name =${listValue.name} data-id = ${listValue.id}>Detail</button>
                            </th>
                            <th>  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editModal" data-name =${listValue.name} data-id = ${listValue.id}>Edit</button>
                            </th> 

                            <th>  <a href="#myModal_${listValue.id}" role="button" class="btn btn-default" data-toggle="modal">Delete</a>
                            </th>
                        <div id="myModal_${listValue.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title">Confirm Delete</h4>
                                    </div>

                                    <div class="modal-body">
                                        <p>Are you sure you want to delete this user? </p>
                                    </div>
                                    <div class="modal-footer">

                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <a href="${pageContext.request.contextPath}/delete/${listValue.id}.html" title="Delete"><i class="fa fa-trash-o"></i>Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>  
                        </tr>
                    </c:forEach>
                </table>
            </ul>

        </c:if>

    </body>
    <!--Modal-->   
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Detail </h4>
                </div>
                <div class="modal-body">
                    <label id="DetailId">Id</label><br>
                    <label id="DetailName">Name</label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>
    <!--edit modal-->

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">Edit user</h4>
                </div>
                <div class="modal-body">
                    <form:form method="POST" action="/task1/edit.html" modelAttribute="employee">
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">ID: </label>

                            <form:input id ="editIdLabel" path="id" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">Name</label>
                            <form:input id ="editNameLabel" path="name"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <input  class="btn btn-default" type="submit" value="Submit"/>
                        </div>
                    </form:form>
                </div>

            </div>

        </div>
    </div>
    <!--add modal-->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">Edit user</h4>
                </div>
                <div class="modal-body">
                    <form:form method="POST" action="/task1/add.html" modelAttribute="employee">
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">ID: </label>

                            <form:input id ="editIdLabel" path="id"/>
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="control-label">Name</label>
                            <form:input id ="editNameLabel" path="name"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <input  class="btn btn-default" type="submit" value="Submit"/>
                        </div>
                    </form:form>
                </div>

            </div>

        </div>
    </div>
</html>
<style>
    table, th, td {
        border: 1px solid black;
        width: 50%;
        text-align: center;
        padding: 15px;
    }
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $('#detailModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var id = button.data('id');
        var name = button.data('name');

        document.getElementById('DetailId').innerHTML = id;
        document.getElementById('DetailName').innerHTML = name;
    })
    $('#editModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var id = button.data('id');
        var name = button.data('name');

        document.getElementById('editIdLabel').value = id;
        document.getElementById('editNameLabel').value = name;
    })

</script>

</head>

