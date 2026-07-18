angular.module('qualityApp')
.config(function ($routeProvider, $locationProvider, $httpProvider) {
  $locationProvider.html5Mode(false);

  $routeProvider
    .when('/login', {
      templateUrl: './lib/components/login/login.template.html',
      controller: 'loginCtrl'
    })
    .when('/evaluation-model', {
      templateUrl: './lib/components/evaluation-model/evaluation-model.template.html',
      controller: 'evaluationModelCtrl',
    })
    .when('/entity', {
      templateUrl: './lib/components/entity/entity.template.html',
      controller: 'entityCtrl',
    })
    .when('/global-vars', {
      templateUrl: './lib/components/global-vars/global-vars.template.html',
      controller: 'globalVarsCtrl',
    })
    .when('/users', {
      templateUrl: './lib/components/users/users.template.html',
      controller: 'usersCtrl',
    })
    .when('/evaluation-score-capture', {
      templateUrl: './lib/components/evaluation-score-capture/evaluation-score-capture.template.html',
      controller: 'evaluationScoreCaptureCtrl',
    })
    .when('/report', {
      templateUrl: './lib/components/report/report.template.html',
      controller: 'reportCtrl',
    })
    .otherwise({
      redirectTo: '/login'
    });
});