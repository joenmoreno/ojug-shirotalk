"use strict";

angular.module('shirotalk.routes', ['ui.router'])

    .config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.when('', '/login');
            $urlRouterProvider.otherwise('/404');

            $stateProvider

                .state("login", {
                    url: "/login",
                    templateUrl: 'app/components/login/login.html',
                    controller: 'LoginController'
                })
                .state("admin", {
                	 abstract: true,
                     url: "/admin",
                     templateUrl: 'app/components/admin/admin.html',
                })
                .state('admin.usermanagement', {
                    url: '',
                    templateUrl: 'app/components/user-management/user-management.html',
                    controller: 'UserManagementController'
                })
                .state("about", {
                    url: "/about",
                    templateUrl: 'app/components/about/about.html'
                })
                .state("dashboard", {
                    url: "/dashboard",
                    templateUrl: 'app/components/dashboard/dashboard.html',
                    controller: 'DashboardController'
                })
                .state('404', {
                    url: '/404',
                    templateUrl: '404.html'
                });
        }
    ]
);