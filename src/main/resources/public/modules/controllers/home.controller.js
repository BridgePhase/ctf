function HomeController() {
	var that = this;
	
	that.notificationIndex = 0;
	
	that.notifications = [
		{
			text: "In February 2015, there were 190 reports of recalls on ice cream"
		},
		{
			text: "notification II"	
		},
		{
			text: "notification tres"	
		},
		{
			text: "notification 4"	
		},
	];
	document.title = 'Consider the following';
}

HomeController.$inject = [];

angular.module('ctf').controller('HomeController', HomeController);