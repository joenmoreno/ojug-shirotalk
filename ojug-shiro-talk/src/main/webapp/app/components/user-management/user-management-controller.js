"use strict";
angular.module('shirotalk.user.management', [])

    .controller('UserManagementController', ['$scope', 'UserManagementService',
        function ($scope, UserManagementService) {
            $scope.users = [];
            $scope.config = {
            	userFetchError: false,
            	loading: false
            };
            $scope.loadUsers = function() {
            	$scope.config.loading = true;
            	UserManagementService.fetchAllUsers()
		            .success(function (data) {
		                $scope.users = data;
		                $scope.config.userFetchError = false;
		                $scope.config.loading = false;
		            })
		            .error(function (error) {
		            	$scope.config.userFetchError = true;
		            	$scope.users = [];
		            	$scope.config.loading = false;
		            });
            };
        }]);