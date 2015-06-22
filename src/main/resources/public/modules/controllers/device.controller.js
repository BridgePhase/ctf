function DeviceController(DeviceService) {
	var that = this;
	
	that.adverseEventMetadata = null;
	that.adverseEvents = [];
	
	that.loadLatestAdverseEvents = function() {
		DeviceService.latestAdverseEvents().then(function(result) {
			that.adverseEventMetadata = result.meta;
			that.adverseEvents = result.results;
		});
	};
	
	that.loadLatestAdverseEvents();
}

DeviceController.$inject = ['DeviceService'];

angular.module('ctf').controller('DeviceController', DeviceController);