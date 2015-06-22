function DeviceService($http) {
	return {
		/**
		 * Returns the latest adverse events
		 */
		latestAdverseEvents: function() {
			return $http.get('/ctf/api/device/recallEvent').then(function(response) {
				return response.data;
			});
		}		
	};
};

DeviceService.$inject = ['$http'];

angular.module('ctf').factory('DeviceService', DeviceService);