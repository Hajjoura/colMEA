angular.module('ColMEA', ['ui.router', 'ColMEA.controllers','LocalStorageModule', 'uiGmapgoogle-maps'])

.run(function($rootScope, localStorageService) {
    $rootScope.endpoint = "http://localhost:8080/ColMEA.Web/";
})

.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/home');

    $stateProvider


        .state('home', {
            url: '/home',
            templateUrl: 'templates/home.html',
			controller: 'HomeCtrl'
        })

        .state('login', {
            url: '/login',
            templateUrl: 'templates/login.html',
            controller: 'LoginCtrl'
        })

        .state('addUser', {
        url: '/addUser',
        templateUrl: 'Views/Manager/AddUser.html',
        controller: 'addUserCtrl'
    })
        .state('HomeManager', {
            url: '/HomeManager',
            templateUrl: 'Views/Manager/HomeManager.html',


        })
        .state('HomeManager.DashboardM', {
            url: '/Dashboard',
            templateUrl: 'Views/Manager/Dashboard.html',

        })
        .state('HomeManager.ProjectM', {
            url: '/ProjectM',
            templateUrl: 'Views/Manager/Project.html',
            controller: 'ProjectMCtrl'
        })
        .state('HomeManager.PartitionM', {
            url: '/PartitionM',
            templateUrl: 'Views/Manager/Partition.html',
            controller: 'PartitionMCtrl'
        })
        .state('HomeManager.CoordinatorM', {
            url: '/CoordinatorM',
            templateUrl: 'Views/Manager/Coordinator.html',
            controller: 'CoordinatorMCtrl'
        })
        .state('HomeManager.EngineerM', {
            url: '/EngineerM',
            templateUrl: 'Views/Manager/Engineer.html',
            controller: 'EngineerMCtrl'
        })
        .state('HomeManager.TeamM', {
            url: '/TeamM',
            templateUrl: 'Views/Manager/Team.html',
            controller: 'TeamMCtrl'
        })
        .state('HomeManager.DomainM', {
            url: '/DomainM',
            templateUrl: 'Views/Manager/Domain.html',
            controller: 'DomainMCtrl'
        })
        .state('logout', {
            url: '/logout',
            controller: 'LogoutCtrl'
        })

        .state('offers', {
            url: '/offers',
            templateUrl: 'templates/offers.html',
            controller: 'OffersCtrl'
        })
    
        .state('users', {
            url: '/users',
            templateUrl: 'templates/users.html',
            controller: 'UsersCtrl'
        })


        .state('HomeCoordinator', {
            url: '/HomeCoordinator',
            templateUrl: 'Views/Coordinator/HomeCoordinator.html',
        })
        .state('HomeCoordinator.DashboardC', {
            url: '/DashboardC',
            templateUrl: 'Views/Coordinator/Dashboard.html',

        })
        .state('HomeCoordinator.ProjectC', {
            url: '/ProjectM',
            templateUrl: 'Views/Coordinator/Project.html',
            controller: 'ProjectMCtrl'
        })
        .state('HomeCoordinator.PartitionC', {
            url: '/PartitionC',
            templateUrl: 'Views/Coordinator/Partition.html',
            controller: 'PartitionMCtrl'
        })

        .state('HomeCoordinator.EngineerC', {
            url: '/EngineerC',
            templateUrl: 'Views/Coordinator/Engineer.html',
            controller: 'EngineerMCtrl'
        })
        .state('HomeCoordinator.TeamC', {
            url: '/TeamC',
            templateUrl: 'Views/Coordinator/Team.html',
            controller: 'TeamMCtrl'
        })
        .state('HomeEngineer', {
            url: '/HomeEngineer',
            templateUrl: 'Views/Engineer/HomeEngineer.html',
        })

        .state('HomeEngineer.DashboardE', {
            url: '/DashboardE',
            templateUrl: 'Views/Engineer/Dashboard.html',

        })
        .state('HomeEngineer.PartitionE', {
            url: '/PartitionE',
            templateUrl: 'Views/Engineer/Partition.html',

        })

	.state('profile', {
            url: '/profile',
            templateUrl: 'templates/myprofile.html',
            controller: 'ProfileCtrl'
        })

       .state('suitableOffers', {
            url: '/suitableOffers',
            templateUrl: 'templates/suitableOffers.html',
            controller: 'SuitableOffersCtrl'
        })
	  .state('offersByplace', {
            url: '/offersByplace',
            templateUrl: 'templates/offersByplace.html',
            controller: 'OffersByplaceCtrl'
        })
        .state('about', {
            url: '/about',
            templateUrl: 'templates/about.html'
        })
        .state('services', {
            url: '/services',
            templateUrl: 'templates/services.html'
        })
        .state('contact', {
            url: '/contact',
            templateUrl: 'templates/contact.html'
        })
    ;

});