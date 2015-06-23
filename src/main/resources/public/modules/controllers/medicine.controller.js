function MedicineController($scope, $timeout, MedicineService) {
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
	
	that.search = function() {
		MedicineService.search(that.medications).then(function(results) {
			console.log(results);
		});
	}
	
	$timeout(function() {
		$scope.$broadcast('update-datatable-medicineAdverseEvents');	
	}, 0);
	
	
	
}

MedicineController.$inject = ['$scope', '$timeout', 'MedicineService'];

angular.module('ctf').controller('MedicineController', MedicineController);