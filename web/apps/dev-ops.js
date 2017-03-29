function devopsStatisticsCtrl($scope, $http) {
    // $http({
    //     method: 'GET',
    //     url: 'https://dev.sz21c.com:8443/stat/current/connectivity',
    //     headers: {'Content-Type': 'application/json; charset=utf-8'}
    // })
    // .success(function(data, status, headers, config) {
    //     if(data) {
    //         $scope.dashboardStatistics = data;
    //     } else {
    //         console.log('result is null');
    //     }
    // })
    // .error(function(data, status, headers, config) {
    //     console.log(status)
    // });
    //
    // $http({
    //     method: 'GET',
    //     url: 'https://dev.sz21c.com:8443/stat/current/top-ten-posts',
    //     headers: {'Content-Type': 'application/json; charset=utf-8'}
    // })
    // .success(function(data, status, headers, config) {
    //     if(data) {
    //         $scope.yesterdayTopTenPostsList = data;
    //     } else {
    //         console.log('result is null');
    //     }
    // })
    // .error(function(data, status, headers, config) {
    //     console.log(status)
    // });

    $http({
        method: 'GET',
        url: 'https://dev.sz21c.com:8443/stat/current/visit-googlebot-count',
        headers: {'Content-Type': 'application/json; charset=utf-8'}
    })
    .success(function(data, status, headers, config) {
        if(data) {
            $scope.visitGoogleBotCount = data;
        } else {
            console.log('result is null');
        }
    })
    .error(function(data, status, headers, config) {
        console.log(status)
    });
}