"use strict";
angular.module('shirotalk.user.management.service', [])

    .factory('UserManagementService', function ($http) {
        var userManagementService = {};

        userManagementService.fetchAllUsers = function () {
            return $http
                .get('api/admin/users');
        };

        return userManagementService;
    });