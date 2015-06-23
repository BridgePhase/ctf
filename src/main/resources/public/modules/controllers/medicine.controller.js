function MedicineController($scope, $timeout) {
	var that = this;
	
	that.currentIndex = 1;
	
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
	
	$timeout(function() {
		$scope.$broadcast('update-datatable-medicineAdverseEvents');	
	}, 0);
	
	
	
}

MedicineController.$inject = ['$scope', '$timeout'];

angular.module('ctf').controller('MedicineController', MedicineController);