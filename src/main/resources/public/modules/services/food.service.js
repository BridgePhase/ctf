function FoodService($http) {
	return {
		recallsByState: function(state) {
			return $http.get('/ctf/api/food/' + state).then(function(response) {
				return response.data;
			})
		}
	}
}

FoodService.$inject = ['$http'];

angular.module('ctf').factory('FoodService', FoodService);