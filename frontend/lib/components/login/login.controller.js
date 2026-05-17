app.controller("loginCtrl", ["$scope","mainService","$location","APP_CONFIG", "$rootScope", "$timeout", function($scope, mainService, $location, APP_CONFIG, $rootScope, $timeout)  {
  $scope.credentials = {};
  $scope.newRegister = {
    person: {},
    user: {}
  };

  $scope.genders = [
    {id: 1, name: 'Masculino'},
    {id: 2, name: 'Femenino'}
  ];
  $scope.showPassword = false;
  $scope.isRegister = false;
  $scope.init = () => {
    console.log("Iniciando");
  }

  $scope.login = () => {
    console.log($scope.credentials);
    mainService.auth($scope.credentials).then((response) => {
      console.log(response);
      if (response.data.error.error) {
        Swal.fire({
          icon: 'error',
          text: response.data.message
        });
        return;
      }
      let userLoged = response.data.data;
      localStorage.setItem(APP_CONFIG.TOKEN_NAME, JSON.stringify(userLoged));
      // Disparar evento global
      $rootScope.$broadcast("userLogged");
      $location.path("/entity");
    })
    .catch((err) => {
      console.log(err);
    })
  }

  $scope.toggleRegister = () => {
    $scope.isRegister = !$scope.isRegister;
  }

  $scope.togglePassword = () => {
    $scope.showPassword = !$scope.showPassword;
  }

  $scope.register = () => {
    console.log($scope.newRegister);
  }
}])