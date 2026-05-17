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
      redirectTo: '/login'
    });
});