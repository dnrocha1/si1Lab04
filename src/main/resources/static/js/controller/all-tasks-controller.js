/**
 * Created by Daniyel on 10/02/2017.
 */
angular.module('todoApp').controller("allTasksController", function ($scope, $http) {

    $scope.allTasks = [];

    var loadAllTasks = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/tasks'
        }).then(function (responseSuccess) {
            $scope.allTasks = responseSuccess.data;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };
    loadAllTasks();

    $scope.getColor = function (task) {
        if (task.taskPriority == "LOW") {
            return "success";
        }
        if (task.taskPriority == "MIDDLE") {
            return "warning";
        }
        if (task.taskPriority == "HIGH") {
            return "danger";
        }
    };

    getNumCompletedTasks = function () {
        var count = 0;
        angular.forEach($scope.allTasks, function (task) {
            if (task.completed) {
                count++;
            }
        });
        return count;
    };

    $scope.getPercentage = function () {
        return $scope.percentage = (getNumCompletedTasks() / $scope.allTasks.length) * 100;
    };

});