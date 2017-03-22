function forwardCtrl($http, $cookies) {
    var exceptedLogValue = $cookies.get('excepted-log-value')

    $http({
        method: 'GET',
        url: 'https://dev.sz21c.com:8443/chk-auth/' + exceptedLogValue,
        headers: {'Content-Type': 'application/json; charset=utf-8'}
    })
    .success(function(data, status, headers, config) {
        location.href = 'dashboard.html';
    })
    .error(function(data, status, headers, config) {
        location.href = 'login.html';
    });
}