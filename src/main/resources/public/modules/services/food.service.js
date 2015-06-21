function FoodService($http) {
	return {
		recallsByState: function(state) {
			return $http.get('/ctf/food/' + state).then(function(response) {
				return response.data;
			})
		}
	}
}

FoodService.$inject = ['$http'];

angular.module('ctf').factory('FoodService', FoodService);