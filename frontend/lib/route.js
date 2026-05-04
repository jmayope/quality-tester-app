angular.module('qualityApp')
.config(function ($routeProvider, $locationProvider, $httpProvider) {
  $locationProvider.html5Mode(false);

  $routeProvider
    .when('/login', {
      templateUrl: 'components/login/login.template.html',
      controller: 'loginCtrl'
    })
    .when('/evaluation-model', {
      templateUrl: './lib/components/evaluation-model/evaluation-model.template.html',
      controller: 'evaluationModelCtrl',
    })
    // .when('/offer', {
    //   templateUrl: 'components/offer/offer.template.html',
    //   controller: 'offerCtrl',
    // })
    // .when('/residue-management', {
    //   templateUrl: 'components/residue-management/residue-management.template.html',
    //   controller: 'residueManagementCtrl',
    // })
    // .when('/select-role', {
    //   templateUrl: 'components/select-role/select-role.template.html',
    //   controller: 'selectRoleCtrl',
    // })
    // .when('/dashboard', {
    //   templateUrl: 'components/dashboard/dashboard.template.html',
    //   controller: 'dashboardCtrl',
    // })
    // .when('/report', {
    //   templateUrl: 'components/report/report.template.html',
    //   controller: 'reportCtrl',
    // })
    // .when('/select-entity', {
    //   templateUrl: 'components/select-entity/select-entity.template.html',
    //   controller: 'selectEntityCtrl',
    // })
    // .when('/waste-manifest', {
    //   templateUrl: 'components/waste-manifest/waste-manifest.template.html',
    //   controller: 'wasteManifestCtrl',
    // })
    .otherwise({
      redirectTo: '/evaluation-model'
    });
});