/* global angular */
/**
 * Starter Application
 * 
 * This can be used as a template to create a starter UI for the application.
 * This will configure states and their related views.
 * 
 * @author Jaime Garcia
 */
angular.module('ctf', [ 'ui.router' ]).config(
	[ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		// this will set the default state to be /home
		$urlRouterProvider.otherwise('/home')

		$stateProvider.state('home', {
			url: '/home',
			templateUrl: 'partials/main'
		}).state('noun', {
			url: '/noun/:noun',
			templateUrl: 'partials/noun',
			controller: ['$state', '$stateParams', function($state, $stateParams) {
				$state.go('noun.' + $stateParams.noun);
			}]
		}).state('noun.food', {
			templateUrl: 'partials/food'
		});
	} ])