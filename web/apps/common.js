angular.module('dev-admin', ['ngResource', 'ngCookies'])
.run(function ($resource, $cookies) {
	var exceptedLogValue = $cookies['excepted-log-value'];
	
	console.log("exceptedLogValue :: " + exceptedLogValue);
	var ChkAuthResource = $resource('https://dev.sz21c.com:8443/chk-auth/' + exceptedLogValue, {}, {
		'get' : {
			method: 'GET',
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}
	});
	
	var chkAuth = ChkAuthResource.get({}, function() {
		location.href = 'dashboard';
	});
	
});
