function DeviceController($scope, $timeout, DeviceService) {
	var that = this;
	
	that.adverseEventMetadata = null;
	that.adverseEvents = [];
	
	that.loadLatestAdverseEvents = function() {
		DeviceService.latestAdverseEvents().then(function(result) {
			that.adverseEventMetadata = result.meta;
			that.adverseEvents = result.results;
			$timeout(function() {
				$scope.$broadcast('update-datatable-deviceAdverseEvents', {
					sort: [[0, 'desc']]
				});
			}, 0);
		});
	};
	
	that.selectDevice = function(event) {
		that.selectedEvent = event;
		
		that.selectedEventNarrative = '';
		console.log(event);
		for (var i = 0; i < event.mdr_text.length; i++) {
			var mdr = event.mdr_text[i];
			if (mdr.text_type_code.indexOf('Description') >= 0) {
				that.selectedEventNarrative = mdr.text;
			}
		}
		window.scroll(0, document.getElementById("resultsSection").offsetTop);
	}
	
	
	that.loadLatestAdverseEvents();
}

DeviceController.$inject = ['$scope', '$timeout', 'DeviceService'];

angular.module('ctf').controller('DeviceController', DeviceController);