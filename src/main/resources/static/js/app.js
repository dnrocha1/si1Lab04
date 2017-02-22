var app = angular.module('todoApp', ['ui.router']);


//ui.router
app.config(function ($stateProvider) {
    $stateProvider
        .state('todoList',{
            url:'/todoList',
            templateUrl: 'view/todoList.html',
            controller: 'todoListController'
        })
        .state('task',{
            url:'/tasks/:todoListId',
            templateUrl: 'view/task.html',
            controller: 'taskController'
        })
        .state('contact',{
            url:'/contact',
            templateUrl: 'view/contact.html'
        })
        .state('allTasks',{
            url:'/allTasks',
            templateUrl: 'view/allTasks.html',
            controller: 'allTasksController'
        })
        .state('category',{
            url:'/taskcategories',
            templateUrl: 'view/category.html',
            controller: 'categoryController'
        })
});