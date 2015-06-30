/* global angular */
/* global c3 */
function MedicineController($scope, $timeout, MedicineService) {
	var that = this;
	
	that.currentIndex = 1;
	that.searchQuery = '';
	that.resultsAvailable = null;
	that.finalQuery = '';
	that.searchFinished = false;
	
	that.search = function() {
		that.resultsAvailable = false;
		that.searchFinished = false;
		MedicineService.drugCount(that.searchQuery).then(function(results) {
			that.finalQuery = that.searchQuery;
			that.searchFinished = true;
			if (hasData(results.deaths) || hasData(results.hospitalization) || hasData(results.threatening)
				|| hasData(results.congenital) || hasData(results.disabling) || hasData(results.other)) {
				that.resultsAvailable = true;
				var deaths = buildUpData(results.deaths, 'Death');
				var hospitalization = buildUpData(results.hospitalization, 'Hospitalization');
				var lifeThreatening = buildUpData(results.threatening, 'Life Threatening Event');
				var congenital = buildUpData(results.congenital, 'Congenital Anomaly');
				var disability = buildUpData(results.disabling, 'Disability');
				var other = buildUpData(results.other, 'Other Serious Condition');
				displayChart(deaths, hospitalization, lifeThreatening, congenital, disability, other);
			} else {
				that.resultsAvailable = false;
			}
		});
	};
	
	function hasData(category) {
		for (var k in category.data) {
			return true;	
		}
		return false;
	}
	
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
	
	
	function displayChart(deaths, hospitalization, lifeThreatening, congenital, disability, other) {
		$timeout(function() {
			if (that.chart) {
				that.chart.destroy();
			}
			that.chart = c3.generate({
			    data: {
			        columns: [
			            deaths,
			            hospitalization,
			            lifeThreatening,
			            congenital,
			            disability,
			            other
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
				bindTo: '#chart'
			});
			gotoElement('chartSection');
		}, 0);
	};
}

MedicineController.$inject = ['$scope', '$timeout', 'MedicineService'];

angular.module('ctf').controller('MedicineController', MedicineController);