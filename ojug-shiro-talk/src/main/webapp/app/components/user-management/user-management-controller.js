"use strict";
angular.module('shirotalk.user.management', [])

    .controller('UserManagementController', ['$scope', 'UserManagementService',
        function ($scope, UserManagementService) {
            $scope.users = [];
            UserManagementService.fetchAllUsers()
	            .success(function (data) {
	                $scope.users = data;
	            })
	            .error(function (error) {
	            	$scope.users = [];
	            });
        }]);