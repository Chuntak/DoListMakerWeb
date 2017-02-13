/**
 * Created by rvtru on 2/12/2017.
 */

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});

var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {

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
    $scope.save = function(){
        var y = $http({
            method: 'GET',
            url: '/addList',
            params: {"email": "Chuntak@gmail.com", "private": true, "listName":"Chuntak todo list", "iD": -1}
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
            params: {"id":$scope.selectedList.entity.properties.real_ID, "email":"OMG@gmail.com", "listName":"Changed"}
        }).then(function (response) {
            $scope.responseData = response.data;
            alert("it worked!");
            //$scope.posts[index]["comments"].splice(commentIndex, 1);
        }, function errorCallBack(response) {
            alert("get messages error\n");
        });
    }

});
