"use strict";
angular.module('shirotalk.login', [])

    .controller('LoginController', ['$scope', 'AuthService', 'Session', '$state',
        function ($scope, AuthService, Session, $state) {
            $scope.config = {
                loading: false
            };
            $scope.login = function () {
                AuthService.login($scope.credentials)
                    .success(function (data) {
                        $scope.config.loading = false;
                        Session.createUser(data.username, data.firstName, data.email);
                        $state.go('dashboard');

                    })
                    .error(function (error) {
                        $scope.config.loading = false;
                        Session.destroy();
                        $scope.errorMessage = 'We could not verify your username/email address and/or password. Please try again.';
                        $scope.credentials.password = '';
                    });
            };
        }]);