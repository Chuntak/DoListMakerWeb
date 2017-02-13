
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- ADDED BY ROBERT FROM CLANS -->
<%--<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />--%>
<%--<c:set var="id" value="${pageContext.request.servletContext.contextPath}" scope="request" />--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="<c:url value="/resources/app/css/todolist_maker.css" />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>TODO LIST MAKER</title>

    <%--<!-- Favicon -->--%>
    <%--<link rel="icon" type="image/png" href="<c:url value="/favicon.ico" />">--%>

    <%--<!-- Bootstrap -->--%>
    <%--<link rel="stylesheet" media="screen" href="<c:url value="/resources/library-vendor/bootstrap/css/bootstrap.min.css" />" >--%>

    <%--<!-- Normalize  for improved cross-browser rendering -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/library-vendor/normalize/normalize.css" />" >--%>

    <%--<!-- Google Code Prettify -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/library-vendor/google-code-prettify/prettify.css" />">--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/library-vendor/google-code-prettify/desert.css" />">--%>

    <%--<!-- jqTree -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/library-vendor/jqTree-master/jqtree.css" />">--%>

    <%--<!-- Pnotify - pinesframework  -->--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/library-vendor/pnotify/pnotify.custom.min.css" />">--%>

    <%--<style>--%>
    <%--body {--%>
    <%--padding-top: 50px;--%>
    <%--}--%>
    <%--.main-container {--%>
    <%--padding: 40px 15px;--%>
    <%--}--%>

    <%--.pull-left {--%>
    <%--float: left !important;--%>
    <%--}--%>
    <%--.pull-right {--%>
    <%--float: right !important;--%>
    <%--}--%>

    <%--/* tree  */--%>
    <%--ul.jqtree-tree .jqtree-element {--%>
    <%--position: relative;--%>

    <%--/* Store - new css */--%>
    <%--padding: 7px;--%>
    <%--border-radius: 4px;--%>
    <%--}--%>

    <%--ul.jqtree-tree li {--%>
    <%--/* Store - new css */--%>
    <%--border: 1px solid #143546;--%>
    <%--margin: 7px;--%>
    <%--border-radius: 4px;--%>
    <%--}--%>
    <%--ul.jqtree-tree span.jqtree-border {--%>
    <%--/* Store - new css */--%>
    <%--padding: 7px;--%>
    <%--border-radius: 4px;--%>
    <%--}--%>
    <%--/* tree ends */--%>
    <%--</style>--%>

</head>
<body>

<div id="toolbar" ng-app="myApp" ng-controller="myCtrl">
    <button type="button" class="btn btn-success">
    <span class="glyphicon glyphicon-plus-sign">
        <a href="#" data-toggle="tooltip" title="Create">Create</a>
    </span>
    </button>

    <button type="button" ng-click="getToDoList()" class="btn btn-default">
    <span class="glyphicon glyphicon-folder-open">
        <a href="#" data-toggle="tooltip" title="Load">Load</a>
    </span>
    </button>

    <button type="button" ng-click="save()" class="btn btn-primary">
    <span class="glyphicon glyphicon-floppy-disk">
        <a href="#" data-toggle="tooltip" title="Save">Save</a>
    </span>
    </button>




    <div id="mid">
        <h1>To Do List</h1>
    </div>

    <div id="details">
        <h2>Details</h2>
        <br/>
        <div class="form-group">
            <label for="nameOfList">Name of List:</label>
            <input type="text" class="form-control" id="nameOfList">
        </div>
        <div class="form-group">
            <label for="author">Author:</label>
            <input type="text" class="form-control" id="author">
        </div>
    </div>

    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a ng-click="getToDoList()"  data-toggle="collapse" href="#collapse4">Get to do list</a>
                </h4>
            </div>
            <div id="collapse4" class="panel-collapse collapse" >
                <select ng-model="selectedList" ng-options="toDoList.listName for toDoList in userToDoList">
                </select>
                <button type="button" ng-click="updateList()">Update</button>
                <%--<div ng-repeat="toDoList in userToDoList">--%>
                    <%--<h4> {{toDoList.entity.key.id}} {{toDoList.email}} {{toDoList.private}} {{toDoList.listName}} </h4>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>


    <div id="items">
        <h3>Items</h3>
        <span id="item-btns">

            <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus-sign">
            <a href="#" data-toggle="tooltip" title="Create">Add Item</a>
        </span>
        </button>


        <button type="button" class="btn btn-default">
            <a href="#" data-toggle="tooltip" title="Save">Delete Item</a>
            <span class="glyphicon glyphicon-minus-sign"></span>
        </button>

        <button type="button" class="btn btn-default">
            <span class="glyphicons glyphicons-circle-arrow-top">
                <a href="#" data-toggle="tooltip" title="Save">Move Item Up</a>
            </span>
        </button>

        <button type="button" class="btn btn-default">
            <span class="glyphicons glyphicons-circle-arrow-down">
                <a href="#" data-toggle="tooltip" title="Save">Move Item Down</a>
            </span>
        </button>
        </span>
    </div>

    <div class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" ng-click="close(false)" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Yes or No?</h4>
                </div>
                <div class="modal-body">
                    <p>It's your call...</p>
                </div>
                <div class="modal-footer">
                    <button type="button" ng-click="close(false)" class="btn btn-default" data-dismiss="modal">No</button>
                    <button type="button" ng-click="close(true)" class="btn btn-primary" data-dismiss="modal">Yes</button>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- JavaScripts -->
<section>

    <!-- jQuery Core -->
    <script src="<c:url value="/resources/library-vendor/jquery/jquery-2.0.3.min.js" />"></script>

    <!-- jQuery Validation -->
    <script src="<c:url value="/resources/library-vendor/jquery/validation/jquery.validate.min.js" />"  type="text/javascript" ></script>

    <!-- jQuery Validation Additional Methods -->
    <script src="<c:url value="/resources/library-vendor/jquery/validation/additional-methods.min.js" />"  type="text/javascript" ></script>

    <!-- Bootstrap -->
    <script src="<c:url value="/resources/library-vendor/bootstrap/js/bootstrap.min.js" />" type="text/javascript"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries, (https://github.com/scottjehl/Respond), (https://code.google.com/p/html5shiv/) -->
    <script src="<c:url value="/resources/library-vendor/respond/respond.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/library-vendor/html5shiv/html5shiv.js" />" type="text/javascript"></script>

    <!-- Google Code Prettify -->
    <script src="<c:url value="/resources/library-vendor/google-code-prettify/prettify.js" />" type="text/javascript"></script>

    <!-- jqTree  -->
    <script src="<c:url value="/resources/library-vendor/jqTree-master/tree.jquery.js" />"  type="text/javascript" ></script>

    <!-- Cookie Jquery  -->
    <script src="<c:url value="/resources/library-vendor/jquery-cookie-master/jquery.cookie.js" />"  type="text/javascript" ></script>

    <!-- Pnotify - pinesframework  -->
    <script src="<c:url value="/resources/library-vendor/pnotify/pnotify.custom.min.js" />"  type="text/javascript" ></script>

    <!-- App Base -->
    <script src="<c:url value="/resources/app/js/app.js" />"  type="text/javascript" ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="<c:url value="/resources/app/js/ang_test.js" />"  type="text/javascript" ></script>

    <!-- App -->
    <script src="<c:url value="/resources/app/js/categories/app.categories.js" />"  type="text/javascript" ></script>


</section>

</body>
</html>