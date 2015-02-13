"use strict";

angular.module('shirotalk', [ 
	'ui.bootstrap',
	'shirotalk.login',
	'shirotalk.dashboard',
	'shirotalk.routes',
	'shirotalk.user.management',
	'shirotalk.user.management.service'
])

	.factory('AuthService', function($http, Session, $state) {
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
		
		authService.hasRole = function(role) {
	        var i=0, len=Session.user.userRoles.length;
	        for (; i<len; i++) {
	            if (Session.user.userRoles[i].toUpperCase() === role.toUpperCase()) {
	                return true;
	            };
	        };
	        return false;
		};
	
		return authService;
	})
	
	.service('Session', function () {
        this.user = {
            username: null,
            firstName: null,
            email: null,
            userRoles: []
        };
        
        this.createUser = function (username, firstName, email, roles) {
            this.user = {
                username: username,
                firstName: firstName,
                email: email,
                userRoles: roles ? roles : []
            };
        };


        this.destroy = function () {
            this.user = {
                userId: null,
                firstName: null,
                email: null,
                userRoles: []
            };
        };
        return this;
    })

	.controller('mainController', ['$scope', '$state', 'AuthService', 'Session',
        function ($scope, $state, AuthService, Session) {
			$scope.isCollapsed = true;

            $scope.isAuthenticated = function() {
                return AuthService.isAuthenticated();
            };
            
            $scope.hasRole = function(role) {
            	return AuthService.hasRole(role);
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