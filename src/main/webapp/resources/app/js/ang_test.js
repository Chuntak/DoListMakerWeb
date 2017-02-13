/**
 * Created by rvtru on 2/12/2017.
 */

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});

var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {

    /* INIT SOME SCOPE VARIABLES */
    $scope.nameOfList = "";

    /* SHOW CONTENT AFTER 'CREATE' BUTTON IS CLICKED */
    $scope.show = false;
    $scope.newList = function(){
        $scope.show = true;
    }


    $scope.getToDoList = function(){
        var y = $http({
            method: 'GET',
            url: '/getMessages',
            params: {}
        }).then(function (response) {
            $scope.userToDoList = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }
    // $scope.save = function(){
    //     var y = $http({
    //         method: 'GET',
    //         url: '/addList',
    //         params: {"email": "Chuntak@gmail.com", "private": true, "listName":"Chuntak todo list", "iD": -1}
    //     }).then(function (response) {
    //         alert("it worked!");
    //         //$scope.posts[index]["comments"].splice(commentIndex, 1);
    //     }, function errorCallBack(response) {
    //         alert("get messages error\n");
    //     });
    // }



    $scope.addList = function(){
        var y = $http({
            method: 'GET',
            url: '/addList',
            params: {"id": 30}
        }).then(function (response) {
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.updateList = function() {
        debugger
        var y = $http({
            method: 'GET',
            url: '/updateList',
            params: {"iD":$scope.selectedList.entity.properties.real_ID, "email":"OMG@gmail.com", "listName":"Changed"}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.close = function(result) {
        close(result, 500); // close, but give 500ms for bootstrap to animate
    };

    /*TODO STARTING FROM DOWN*/

    $scope.getItems = function () {
        $scope.getToDoList = function(){
            var y = $http({
                method: 'GET',
                url: '/getItems',
                params: {"listId": 98765456543543}
            }).then(function (response) {
                $scope.responseData = response.data;
                alert("it worked!");
                //$scope.posts[index]["comments"].splice(commentIndex, 1);
            }, function errorCallBack(response) {
                alert("get messages error\n");
            });
        }
    }

    $scope.addItem = function () {
        var y = $http({
            method: 'GET',
            url: '/addItem',
            params: {"listId": 9876543567, "category": "", "description": "",
                "startDate": "", "endDate": "", "completed": true, "positionInList": 1}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

    $scope.updateItem = function () {
        var y = $http({
            method: 'GET',
            url: '/updateItem',
            params: {"iD": "", "listId": 9876543567, "category": "", "description": "",
                "startDate": "", "endDate": "", "completed": true, "positionInList": 1}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }
    $scope.deleteList = function () {
        var y = $http({
            method: 'GET',
            url: '/deleteList',
            params: {"iD" : ""}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }
    $scope.deleteItem = function () {
        var y = $http({
            method: 'GET',
            url: '/deleteItem',
            params: {"iD" : ""}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

});
