function MedicineController($scope, $timeout, MedicineService) {
	var that = this;
	
	that.currentIndex = 1;
	that.adverseEventMetadata = null;
	that.adverseEvents = [];
	
	that.medications = [{
		input: ''
	}];

	that.addMedication = function() {
		that.medications.push({
			input: ''
		});
	}
	
	that.removeMedication = function(index) {
		that.medications.splice(index, 1);
	}
	
	that.search = function() {
		MedicineService.search(that.medications).then(function(results) {
			that.adverseEventMetadata = results.meta;
			that.adverseEvents = results.results;
		});
	}
}

MedicineController.$inject = ['$scope', '$timeout', 'MedicineService'];

angular.module('ctf').controller('MedicineController', MedicineController);