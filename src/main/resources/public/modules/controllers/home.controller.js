function HomeController($http) {
	var that = this;
	
	that.notificationIndex = 0;
	
	that.notifications = [];
	
	$http.get("/ctf/app/notifications?_"+(new Date().getTime())).then(function(response) {
		notifications = response.data;
		for (var i = 0; i < notifications.length; i++) {
			that.notifications.push({ text: notifications[i].headline });
		}
	});
	
	document.title = 'Consider the following';
}

HomeController.$inject = ['$http'];

angular.module('ctf').controller('HomeController', HomeController);