/**
 * Created by Daniyel on 04/02/2017.
 */

//todoListController
angular.module('todoApp').controller("todoListController", function ($scope, $http) {

    $scope.todoListInput = "";
    $scope.todoList = {};
    $scope.todoLists = [];

    $scope.addTodoList = function () {
        $scope.todoList = {
            title: $scope.todoListInput
        };
        $scope.todoListInput = "";
        saveTodoLists();
    };

    var saveTodoLists = function () {
        $http({
            method: 'POST',
            url: '/todoList',
            data: $scope.todoList
        }).then(function (responseSuccess) {
            $scope.todoLists.push(responseSuccess.data);
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.removeAll = function () {
        if (confirm("Deseja deletar todas as tarefas?")) {
            angular.forEach($scope.todoLists, function (todoList) {
                $scope.deleteTodoList(todoList);
            });
        }
    };

    var loadTodoLists = function () {
        $http({
            method: 'GET',
            url: '/todoList'
        }).then(function (responseSuccess) {
            $scope.todoLists = responseSuccess.data;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.deleteTodoList = function (todoList) {
        $http({
            method: 'DELETE',
            url: '/todoList/' + todoList.id
        }).then(function (responseSuccess) {
            var pos = $scope.todoLists.indexOf(todoList);
            $scope.todoLists.splice(pos, 1);
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.updateTodoList = function (todoList) {
        $scope.todoList = todoList;
    };

    $scope.editTodoList = function () {
        var previousTodo = $scope.todoList;
        $scope.todoList.title = $scope.todoListInput;
        $http({
            method: 'PUT',
            url: '/todoList',
            data: $scope.todoList
        }).then(function (responseSuccess) {
            var pos = $scope.todoLists.indexOf(previousTodo);
            $scope.todoLists.splice(pos, 1);
            $scope.todoLists.push($scope.todoList);
            $scope.todoListInput = "";
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    loadTodoLists();

});