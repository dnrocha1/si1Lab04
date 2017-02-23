/**
 * Created by Daniyel on 10/02/2017.
 */
angular.module('todoApp').controller("categoryController", function ($scope, $http) {

    $scope.allCategories = [];
    $scope.categoryInput = "";
    $scope.category = {};

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


    $scope.deleteCategory = function (category) {
        $http({
            method: 'DELETE',
            url: '/taskcategories/' + category.id
        }).then(function (responseSuccess) {
            loadCategories();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.removeAll = function () {
        if (confirm("Deseja deletar todas as categorias?")) {
            angular.forEach($scope.allCategories, function (category) {
                $scope.deleteCategory(category);
            });
        }
    };

    $scope.addCategory = function () {
        $scope.category = {
            title: $scope.categoryInput
        };
        $scope.categoryInput = "";
        saveCategory();
    };

    var saveCategory = function () {
        $http({
            method: 'POST',
            url: '/taskcategories',
            data: $scope.category
        }).then(function (responseSuccess) {
            loadCategories();
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

    $scope.updateCategory = function (category) {
        $scope.category = category;
    };

    $scope.editCategory = function () {
        $scope.category.title = $scope.categoryInput;
        $http({
            method: 'PUT',
            url: '/taskcategories',
            data: $scope.category
        }).then(function (responseSuccess) {
            loadCategories();
            $scope.categoryInput = "";
        }, function (responseFail) {
            console.log(responseFail.data);
            console.log(responseFail.status);
        });
    };

});
