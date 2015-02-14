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
                .state("admin", {
                	 abstract: true,
                     url: "/admin",
                     templateUrl: 'app/components/admin/admin.html',
                     data: {
                         needsAuthc: true
                     }
                })
                .state('admin.usermanagement', {
                    url: '',
                    templateUrl: 'app/components/user-management/user-management.html',
                    controller: 'UserManagementController',
                    data: {
                        needsAuthc: true
                    }
                })
                .state("about", {
                    url: "/about",
                    templateUrl: 'app/components/about/about.html',
                    data: {
                        needsAuthc: false
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
                .state("myaccount", {
                	 abstract: true,
                     url: "/my-account",
                     templateUrl: 'app/components/my-account/my-account.html',
                     data: {
                         needsAuthc: true
                     }
                })
                .state('myaccount.accountdetail', {
                    url: '',
                    templateUrl: 'app/components/my-account/my-account-detail.html',
                    controller: 'UserManagementController',
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