/**
 * Created by Daniyel on 04/02/2017.
 */
angular.module('todoApp').controller("taskController", function ($scope, $http, $stateParams) {
    var todoListId = $stateParams.todoListId;
    var todoList = {};
    $scope.taskCategoryId = "";
    $scope.task = {};
    $scope.taskInput = "";
    $scope.descriptionInput = "";
    $scope.tasks = [];
    $scope.subTasks = [];
    $scope.subTaskInput = "";
    $scope.subTask = {};
    $scope.categoryInput = "";
    $scope.categories = [];
    var category = {};
    $scope.taskPriority = "MIDDLE";

    var loadTodoList = function () {
        $http({
            method: 'GET',
            url: '/todoList/' + todoListId
        }).then(function (responseSuccess) {
            todoList = responseSuccess.data;
            $scope.tasks = todoList.tasks;
            $scope.todoList = todoList.title;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    loadTodoList();

    $scope.addTask = function () {
        $scope.task = {
            title: $scope.taskInput,
            description: $scope.descriptionInput,
            taskPriority: getPriority(),
            taskCategory: {id: $scope.taskCategoryId},
            toDoList: {id: todoList.id}
        };
        saveTask();
    };

    var saveTask = function () {
        $http({
            method: 'POST',
            url: '/tasks',
            data: $scope.task
        }).then(function (responseSuccess) {
            $scope.tasks.push(responseSuccess.data);
            $scope.task = {};
            $scope.taskInput = "";
            $scope.descriptionInput = "";
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.setPriority = function (priority) {
        $scope.taskPriority = priority;
    };

    var getPriority = function () {
        return $scope.taskPriority;
    };

    $scope.completedTask = function (task) {
        task.subTasks = null;
        $http({
            method: 'PUT',
            url: '/tasks',
            data: task
        }).then(function (responseSuccess) {
            loadTodoList();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.deleteTask = function (task) {
        $http({
            method: 'DELETE',
            url: '/tasks/' + task.id
        }).then(function (responseSuccess) {
            loadTodoList();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.editTask = function () {
        $scope.task.title = $scope.taskInput;
        $scope.task.description = $scope.descriptionInput;
        $scope.task.taskPriority = getPriority();
        $scope.task.category = {id: $scope.taskCategoryId};
        $http({
            method: 'PUT',
            url: '/tasks',
            data: $scope.task
        }).then(function (responseSuccess) {
            loadTodoList();
            $scope.cleanFields();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.updateTask = function (task) {
        $scope.taskInput = task.title;
        $scope.descriptionInput = task.description;
        $scope.taskCategory = task.taskCategory;
        $scope.task.subTasks = task.subTasks;
        $scope.setPriority(task.taskPriority);
        $scope.task = task;
    };

    $scope.cleanFields = function () {
        $scope.taskInput = "";
        $scope.descriptionInput = "";
        $scope.subTaskInput = "";
        $scope.categoryInput = "";
    };

    $scope.setTask = function (task) {
        $scope.task = task;
        loadSubTasks();
    };

    $scope.getColor = function (task) {
        if(task.taskPriority == "LOW"){
            return "success";
        }
        if(task.taskPriority == "MIDDLE"){
            return "warning";
        }
        if(task.taskPriority == "HIGH"){
            return "danger";
        }
    };

    var loadSubTasks = function () {
        $http({
            method: 'GET',
            url: '/tasks/' + $scope.task.id
        }).then(function (responseSuccess) {
            $scope.task = responseSuccess.data;
            $scope.subTasks = $scope.task.subTasks;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.addSubtask = function () {
        $scope.subTask = {
            title: $scope.subTaskInput,
            completed: false,
            task: {id: $scope.task.id}
        };
        saveSubTask();
    };

    var saveSubTask = function () {
        $http({
            method: 'POST',
            url: '/subtasks',
            data: $scope.subTask
        }).then(function (responseSuccess) {
            $scope.subTasks.push(responseSuccess.data);
            $scope.cleanFields();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.deleteSubTask = function (subTask) {
        $http({
            method: 'DELETE',
            url: '/subtasks/' + subTask.id
        }).then(function (responseSuccess) {
            loadSubTasks();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.completedSubTask = function (subTask) {
        subTask.task = {id: $scope.task.id};
        $http({
            method: 'PUT',
            url: '/subtasks',
            data: subTask
        }).then(function (responseSuccess) {
            loadTodoList();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.addCategory = function () {
        category = {title:$scope.categoryInput};
        saveCategory();
        $scope.categoryInput = "";
    };

    var saveCategory = function () {
        $http({
            method: 'PUT',
            url: '/taskcategories',
            data: category
        }).then(function (responseSuccess) {
            $scope.categories.push(responseSuccess.data);
            loadCategories();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.allCategories = [];

    var loadCategories = function () {
        $http({
            method: 'GET',
            url: '/taskcategories'
        }).then(function (responseSuccess) {
            $scope.allCategories = responseSuccess.data;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };
    loadCategories();
});