"use strict";

angular.module('shirotalk', [ 
	'ui.bootstrap',
	'ngIdle',
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

	.controller('mainController', ['$scope', '$state', 'AuthService', 'Session', 'Idle', 'Keepalive', '$log',
        function ($scope, $state, AuthService, Session, Idle, Keepalive, $log) {
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
            	Idle.unwatch();
            	Keepalive.stop();
                AuthService.logout()
	                .then(function (data) {
	                	Session.destroy();
	                    $state.go('login');
	                });
            };
            
            $scope.$on('IdleStart', function() {
            	$log.debug('IdleStart');
            });

            $scope.$on('IdleWarn', function(e, countdown) {
            	$log.debug('IdleWarn');
            });

            $scope.$on('IdleTimeout', function() {
            	$log.debug('IdleTimeout. Logging user out.');
            	$scope.logout();
            });

            $scope.$on('IdleEnd', function() {
            	$log.debug('IdleEnd');
            });

            $scope.$on('Keepalive', function() {
            	$log.debug('Keepalive');
            });

    }])
    
    .config(['IdleProvider', 'KeepaliveProvider', function(IdleProvider, KeepaliveProvider) {
        // configure Idle settings
        IdleProvider.idle(60); // in seconds
        IdleProvider.timeout(20); // in seconds
        KeepaliveProvider.interval(15); // in seconds
        KeepaliveProvider.http('api/ka');
    }])
    
    .run(function ($rootScope, AuthService, Session, $state, $log) {

        $rootScope.$on('$stateChangeStart',
            function interceptor(event, toState, toParams, fromState, fromParams){
                $log.debug('From State: ' + fromState.name);
                $log.debug('To State: ' + toState.name);
                if(toState.data.needsAuthc && !AuthService.isAuthenticated()) {
                    event.preventDefault();
                    $state.go('login');
                }
            }
        );
    });