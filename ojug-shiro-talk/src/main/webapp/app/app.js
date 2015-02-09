"use strict";

angular.module('shirotalk', [ 
	'ui.bootstrap',
	'shirotalk.login',
	'shirotalk.dashboard',
	'shirotalk.routes'
])

.factory('AuthService',
	function($http, Session, $state) {
		var authService = {};
	
		authService.login = function(credentials) {
			return $http.post('api/login', credentials);
		};
	
		authService.isAuthenticated = function() {
			return !!Session.user.username;
		};
	
		authService.logout = function() {
			Session.destroy();
			return $http.post('api/logout', {});
		};
	
		return authService;
	})
	
	.service('Session', function () {
        this.user = {
            username: null,
            firstName: null,
            email: null,
            accountStatus: null
        };
        
        this.createUser = function (username, firstName, email, accountStatus) {
            this.user = {
                username: username,
                firstName: firstName,
                email: email
            };
        };


        this.destroy = function () {
            this.user = {
                userId: null,
                firstName: null,
                email: null
            };
        };
        return this;
    })

	.controller('mainController', ['$scope', '$state', 'AuthService', 'Session',
        function ($scope, $state, AuthService, Session) {

            $scope.isAuthenticated = function() {
                return AuthService.isAuthenticated();
            };

            $scope.username = function() {
                return Session.user.username;
            };


            $scope.logout = function () {
                AuthService.logout()
	                .success(function (data) {
	                    $state.go('login');
	                })
	                .error(function (error) {
	                	$state.go('login');
	                });
            };

    }]);