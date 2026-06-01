app.controller("loginCtrl", ["$scope","mainService","$location","APP_CONFIG", "$rootScope", "$timeout", function($scope, mainService, $location, APP_CONFIG, $rootScope, $timeout)  {
  $scope.credentials = {};
  $scope.newRegister = {
    person: {},
    user: {}
  };

  $scope.genders = [
    {id: true, name: 'Masculino'},
    {id: false, name: 'Femenino'}
  ];
  $scope.showPassword = false;
  $scope.isRegister = false;
  $scope.userLoged = undefined;
  $scope.init = () => {
    console.log("Iniciando");
    $scope.userLoged = structuredClone(localStorage.getItem(APP_CONFIG.TOKEN_NAME));
    if ($scope.userLoged) {
      $location.path("/entity");
    }
  }

  $scope.login = () => {
    Swal.fire({
      html: APP_CONFIG.SPINNER_LOADING,
      allowEnterKey: false,
      allowOutsideClick: false,
      showCancelButton: false,
      showConfirmButton: false
    });
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
      Swal.close();
      // Disparar evento global
      $rootScope.$broadcast("userLogged");
      $location.path("/entity");
    })
    .catch((err) => {
      Swal.close();
      Swal.fire({
        icon: 'error',
        title: err.data.message
      });
      return;
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