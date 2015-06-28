function MedicineService($http) {
	return {
		search: function(medications) {
			return $http.get('/ctf/api/drug/search?medications=' + medications).then(function(response) {
				return response.data;
			});
		},
		
		drugCount: function(drugs) {
			return $http.get('/ctf/api/drug/stats?medications=' + drugs).then(function(response) {
				return response.data
			});
		}
	}
}

MedicineService.$inject = ['$http'];

angular.module('ctf').factory('MedicineService', MedicineService);