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
                    controller: 'LoginController',
                    data: {
                        needsAuthc: false
                    }
                })
                .state("about", {
                    url: "/about",
                    templateUrl: 'app/components/about/about.html',
                    data: {
                        needsAuthc: true
                    }
                })
                .state("dashboard", {
                    url: "/dashboard",
                    templateUrl: 'app/components/dashboard/dashboard.html',
                    controller: 'DashboardController',
                    data: {
                        needsAuthc: true
                    }
                })
                .state('404', {
                    url: '/404',
                    templateUrl: '404.html',
                    data: {
                        needsAuthc: false
                    }
                });
        }
    ]
);