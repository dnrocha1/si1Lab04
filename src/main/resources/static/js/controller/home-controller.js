/**
 * Created by Daniyel on 22/02/2017.
 */
angular.module('todoApp').controller("homeController", function ($scope, $http) {

    $scope.allTodoLists = [];
    $scope.todoListId = "";
    var currentTodoList = {};
    var docDefinition = {};

    var loadTodoLists = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/todoList'
        }).then(function (responseSuccess) {
            $scope.allTodoLists = responseSuccess.data;
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };
    loadTodoLists();

    var loadTodoList = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/todoList/' + $scope.todoListId
        }).then(function (responseSuccess) {
            currentTodoList = responseSuccess.data;
            //console.log(currentTodoList);
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.updateId = function () {
        loadTodoList();
        console.log($scope.todoListId);
    };

    $scope.export = function () {
        docDefinition = {content: todoListToString()};
        //console.log(docDefinition);
        pdfMake.createPdf(docDefinition).download(currentTodoList.title + ".pdf");
    };

    var todoListToString = function () {
        var exit = "";
        exit = "Lista de Tarefas: " + currentTodoList.title + "\n\n";
        for (var i = 0; i < currentTodoList.tasks.length; i++) {
            var task = currentTodoList.tasks[i];
            exit += "Título: " + task.title;
            if (task.completed == true) {
                exit += " (Concluída)" + "\n";
            } else {
                exit += " (Não concluída)" + "\n";
            }
            exit += ">>>Prioridade: ";
            if (task.taskPriority === 'HIGH') {
                exit += "Alta" + "\n";
            }
            if (task.taskPriority === 'MIDDLE') {
                exit += "Média" + "\n";
            }
            if (task.taskPriority === 'LOW') {
                exit += "Baixa" + "\n";
            }
            if (task.subTasks.length > 0) {
                exit += ">>>Subtarefa(s): \n";
                for (var j = 0; j < task.subTasks.length; j++) {
                    exit += "----------" +
                        task.subTasks[j].title;
                    if (task.subTasks[j].completed == true) {
                        exit += " (Concluída)" + "\n";
                    } else {
                        exit += " (Não concluída)" + "\n";
                    }
                }
            }
            exit += "\n";
        }
        return exit;
    };

    //docDefinition = { content: todoListToString() };

});