/* global angular */
/* global c3 */
function MedicineController($scope, $timeout, MedicineService) {
	var that = this;
	
	that.currentIndex = 1;
	that.adverseEventMetadata = null;
	that.adverseEvents = [];
	that.searchQuery = '';
	
	that.search = function() {
		MedicineService.drugCount(that.searchQuery).then(function(results) {
			displayChart(results);
		});
		MedicineService.search(that.searchQuery).then(function(results) {
			console.log(results);
		});
	};
	
	function getDataFor(data, category) {
		var val = data.data[category];
		if (val == null) {
			val = 0;
		}
		return val;
	};
	
	function buildUpData(originalData, friendlyName) {
		var data = [];
		data.push(friendlyName);
		data.push(getDataFor(originalData, '0-10'));
		data.push(getDataFor(originalData, '11-20'));
		data.push(getDataFor(originalData, '21-30'));
		data.push(getDataFor(originalData, '31-40'));
		data.push(getDataFor(originalData, '41-50'));
		data.push(getDataFor(originalData, '51-60'));
		data.push(getDataFor(originalData, '61-70'));
		data.push(getDataFor(originalData, '71-80'));
		data.push(getDataFor(originalData, '81-90'));
		data.push(getDataFor(originalData, '91-100'));
		data.push(getDataFor(originalData, '100+'));
		return data;
	};
	
	
	function displayChart(result) {
		$timeout(function() {
			window.chart = c3.generate({
			    data: {
			        columns: [
			            buildUpData(result.deaths, 'Death'),
			            buildUpData(result.hospitalization, 'Hospitalization'),
			            buildUpData(result.threatening, 'Life Threatening Event'),
			            buildUpData(result.congenital, 'Congenital Anomaly'),
			            buildUpData(result.disabling, 'Disability'),
			            buildUpData(result.other, 'Other Serious Condition')
			            
			        ],
			        type: 'bar',
			        groups: [
			                 ['Death', 'Hospitalization', 'Life Threatening Event', 'Congenital Anomaly', 'Disability', 'Other Serious Condition']
			        ]
			    },
		        axis: {
		        	x: {
		        		type: 'category',
						label: {
							text: 'Patient Age',
							position: 'outer-left' 
						},
						categories: ['0+', '10', '20', '30', '40', '50', '60', '70', '80', '90', '100+'],
						tick: {
							culling: {
								max: 5
							}
						}
		        	}
		        },
				legend: {
					inset: {
						anchor: 'top-left',
						x: 0,
						y: 20
					}
					
				},
				padding: {
					top: 10,
					left: 35,
					right: 10,
					bottom: 10
				},
			    bindto: '#chart',
			});
			
			gotoElement('chartSection');
		}, 0);
	};
}

MedicineController.$inject = ['$scope', '$timeout', 'MedicineService'];

angular.module('ctf').controller('MedicineController', MedicineController);