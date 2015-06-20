/**
 * Region Service 
 * 
 * Provides information related to the location of the current client by using the 
 * functionalities provided by
 * http://ipinfo.io/developers.
 */
function RegionService($http, $q) {
	// this list came from Adam Kinney @ http://adamkinney.com/blog/2012/04/25/list-of-us-states-in-javascript-object-notation/
	// thanks for the help!!!
	var usStates = usStates = [
	                           { name: 'Alabama', nameStandard: 'ALABAMA', abbreviation: 'AL'},
	                           { name: 'Alaska', nameStandard: 'ALASKA', abbreviation: 'AK'},
	                           { name: 'American Samoa', nameStandard: 'AMERICAN SAMOA', abbreviation: 'AS'},
	                           { name: 'Arizona', nameStandard: 'ARIZONA', abbreviation: 'AZ'},
	                           { name: 'Arkansas', nameStandard: 'ARKANSAS', abbreviation: 'AR'},
	                           { name: 'California', nameStandard: 'CALIFORNIA', abbreviation: 'CA'},
	                           { name: 'Colorado', nameStandard: 'COLORADO', abbreviation: 'CO'},
	                           { name: 'Connecticut', nameStandard: 'CONNECTICUT', abbreviation: 'CT'},
	                           { name: 'Deleware', nameStandard: 'DELAWARE', abbreviation: 'DE'},
	                           { name: 'District of Columbia (DC)', 
	                        	   	nameStandard: 'DISTRICT OF COLUMBIA', abbreviation: 'DC'},
	                           { name: 'Federated States of Micronesia', 
	                        	   nameStandard: 'FEDERATED STATES OF MICRONESIA', abbreviation: 'FM'},
	                           { name: 'Florida', nameStandard: 'FLORIDA', abbreviation: 'FL'},
	                           { name: 'Georgia', nameStandard: 'GEORGIA', abbreviation: 'GA'},
	                           { name: 'Guam', nameStandard: 'GUAM', abbreviation: 'GU'},
	                           { name: 'Hawaii', nameStandard: 'HAWAII', abbreviation: 'HI'},
	                           { name: 'Idaho', nameStandard: 'IDAHO', abbreviation: 'ID'},
	                           { name: 'Illinois', nameStandard: 'ILLINOIS', abbreviation: 'IL'},
	                           { name: 'Indiana', nameStandard: 'INDIANA', abbreviation: 'IN'},
	                           { name: 'Iowa', nameStandard: 'IOWA', abbreviation: 'IA'},
	                           { name: 'Kansas', nameStandard: 'KANSAS', abbreviation: 'KS'},
	                           { name: 'Kentucky', nameStandard: 'KENTUCKY', abbreviation: 'KY'},
	                           { name: 'Lousiana', nameStandard: 'LOUISIANA', abbreviation: 'LA'},
	                           { name: 'Maine', nameStandard: 'MAINE', abbreviation: 'ME'},
	                           { name: 'Marshall Islands', nameStandard: 'MARSHALL ISLANDS', abbreviation: 'MH'},
	                           { name: 'Maryland', nameStandard: 'MARYLAND', abbreviation: 'MD'},
	                           { name: 'Massachussetts', nameStandard: 'MASSACHUSETTS', abbreviation: 'MA'},
	                           { name: 'Michigan', nameStandard: 'MICHIGAN', abbreviation: 'MI'},
	                           { name: 'Minnesota', nameStandard: 'MINNESOTA', abbreviation: 'MN'},
	                           { name: 'Mississippi', nameStandard: 'MISSISSIPPI', abbreviation: 'MS'},
	                           { name: 'Missouri', nameStandard: 'MISSOURI', abbreviation: 'MO'},
	                           { name: 'Montana', nameStandard: 'MONTANA', abbreviation: 'MT'},
	                           { name: 'Nebraska', nameStandard: 'NEBRASKA', abbreviation: 'NE'},
	                           { name: 'Nevada', nameStandard: 'NEVADA', abbreviation: 'NV'},
	                           { name: 'New Hampshire', nameStandard: 'NEW HAMPSHIRE', abbreviation: 'NH'},
	                           { name: 'New Jersey', nameStandard: 'NEW JERSEY', abbreviation: 'NJ'},
	                           { name: 'New Mexico', nameStandard: 'NEW MEXICO', abbreviation: 'NM'},
	                           { name: 'New York', nameStandard: 'NEW YORK', abbreviation: 'NY'},
	                           { name: 'North Carolina', nameStandard: 'NORTH CAROLINA', abbreviation: 'NC'},
	                           { name: 'North Dakota', nameStandard: 'NORTH DAKOTA', abbreviation: 'ND'},
	                           { name: 'Northern Mariana Islands', 
	                        	   	nameStandard: 'NORTHERN MARIANA ISLANDS', abbreviation: 'MP'},
	                           { name: 'Ohio', nameStandard: 'OHIO', abbreviation: 'OH'},
	                           { name: 'Oklahoma', nameStandard: 'OKLAHOMA', abbreviation: 'OK'},
	                           { name: 'Oregon', nameStandard: 'OREGON', abbreviation: 'OR'},
	                           { name: 'Palau', nameStandard: 'PALAU', abbreviation: 'PW'},
	                           { name: 'Pennsylvania', nameStandard: 'PENNSYLVANIA', abbreviation: 'PA'},
	                           { name: 'Puerto Rico', nameStandard: 'PUERTO RICO', abbreviation: 'PR'},
	                           { name: 'Rhode Island', nameStandard: 'RHODE ISLAND', abbreviation: 'RI'},
	                           { name: 'South Carolina', nameStandard: 'SOUTH CAROLINA', abbreviation: 'SC'},
	                           { name: 'South Dakota', nameStandard: 'SOUTH DAKOTA', abbreviation: 'SD'},
	                           { name: 'Tennessee', nameStandard: 'TENNESSEE', abbreviation: 'TN'},
	                           { name: 'Texas', nameStandard: 'TEXAS', abbreviation: 'TX'},
	                           { name: 'Utah', nameStandard: 'UTAH', abbreviation: 'UT'},
	                           { name: 'Vermont', nameStandard: 'VERMONT', abbreviation: 'VT'},
	                           { name: 'Virgin Islands', nameStandard: 'VIRGIN ISLANDS', abbreviation: 'VI'},
	                           { name: 'Virginia', nameStandard: 'VIRGINIA', abbreviation: 'VA'},
	                           { name: 'Washington', nameStandard: 'WASHINGTON', abbreviation: 'WA'},
	                           { name: 'West Virginia', nameStandard: 'WEST VIRGINIA', abbreviation: 'WV'},
	                           { name: 'Wisconsin', nameStandard: 'WISCONSIN', abbreviation: 'WI'},
	                           { name: 'Wyoming', nameStandard: 'WYOMING', abbreviation: 'WY' }
	                       ];
	
	var locationInfo = null;
	var locationInPromise = null;
	
	/*
	 * Retrieves full state information based on a user friendly name.
	 */
	function stateInformation(fullname) {
		fullname = fullname.toUpperCase();
		for (var i = 0; i < usStates.length; i++) {
			if (usStates[i].nameStandard === fullname) {
				return usStates[i];
			}
		}
		return null;
	}
	
	// I tried to use $http.jsonp but that causes 
	// the ipinfo service to return application/json which 
	// due to browser security fails to execute the response
	locationInfoPromise = $http.get("http://ipinfo.io/geo", {
		headers: {
			"Accept": "application/javascript"
		}
	}).then(function(response) {
		locationInfo = response.data;
		// adding more detailed state information for our use
		locationInfo.stateInformation = stateInformation(response.data.region);
		return locationInfo;
	});
	
	return {
		/**
		 * Returns a list of all the US states including:
		 * <pre><code>
		 * {
		 *   name: 'Virginia',         // user friendly state name
		 *   nameStandard: 'VIRGINIA', // name as it would appear on a standardized address
		 *   abbreviation: 'VA'        // abbreviation for the state
		 * }
		 * </code></pre>
		 */
		allUsStates: function() {
			return usStates;
		},
		
		/**
		 * Returns the current location information for a user
		 * <pre><code>
		 * {
		 *   city: "Chantilly",         // city
		 *   country: "US",             // country
		 *   ip: "8.8.8.8",             // IP address
		 *   loc: "38.9036,-77.4512",   // latitude and longitude
		 *   phone: "703",              // phone area code
		 *   postal: "20151",           // zip code
		 *   region: "Virginia"         // region, in the case of the US, it's the state
		 *   stateInformation: {}       // extra information about a state if it's in the US
		 *                              // see the return value of <code>allUsStates()</code> for the format
		 * }
		 * </code></pre>
		 */
		location: function() {
			if (locationInfo != null) {
				var deferred = $q.defer();
				deferred.resolve(locationInfo);
				return deferred.promise;
			} else return locationInfoPromise;
		},
		
		stateFromAbbreviation: function(abbreviation) {
			for (var i = 0; i < usStates.length; i++) {
				if (usStates[i].abbreviation === abbreviation) {
					return usStates[i];
				}
			}
			return null;
		}
	}
}

RegionService.$inject = ['$http', '$q'];

angular.module('ctf').factory('RegionService', RegionService);