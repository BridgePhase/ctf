function MedicineService($http) {
	return {
		search: function(medications) {
			var normalized = [];
			for (var i = 0; i < medications.length; i++) {
				normalized.push(medications[i].input);
			}
			return $http.post('/ctf/api/drug/search', normalized).then(function(response) {
				return response.data;
			});
		}
	}
}

MedicineService.$inject = ['$http'];

angular.module('ctf').factory('MedicineService', MedicineService);