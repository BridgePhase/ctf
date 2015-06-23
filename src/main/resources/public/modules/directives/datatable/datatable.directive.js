/* global $ */
/* global angular */
angular.module('ctf').directive('datatable', ['$timeout', function($timeout) {
	return {
		restrict: 'A',
		link: function(scope, element, attributes) {
			var elem = element[0];
			scope.$on('update-datatable-' + elem.id, function(event, params) {
				var sort = [[0, 'asc']];
				console.log(params);
				if (params) {
					sort = params.sort
				}
				$timeout(function() {
					$('#' + elem.id).dataTable({
						paging: false,
						scrollY: 300,
						searching: false,
						order: sort
					});				
				}, 0);
			});
		}
	};
}]);