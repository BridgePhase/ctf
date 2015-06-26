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
	
	
	var chart = c3.generate({
	    data: {
	        columns: [
//	            ['data1', -30, 200, 200, 400, -150, 250],
	            ['data2', -230, 200, 200, -300, 250, 250]
	        ],
	        type: 'bar',
//	        groups: [
//	            ['data1', 'data2']
//	        ]
	    },
//	    grid: {
//	        y: {
//	            lines: [{value:0}]
//	        }
//	    },
        axis: {
        	x: {
        		type: 'category',
        		categories: ['cat1', 'cat2', 'cat3', '']
        	}
        },
	    bindto: '#chart',
	});
//	var chart = c3.generate({
//	    data: {
//	      columns: [
//	        ['data1', 30, 200, 100, 400, 150, 250],
//	        ['data2', 50, 20, 10, 40, 15, 25]
//	      ]
//	    }
//	});
}

MedicineController.$inject = ['$scope', '$timeout', 'MedicineService'];

angular.module('ctf').controller('MedicineController', MedicineController);