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

  $scope.login = async () => {
    Swal.fire({
      html: APP_CONFIG.SPINNER_LOADING,
      allowEnterKey: false,
      allowOutsideClick: false,
      showCancelButton: false,
      showConfirmButton: false
    });
    console.log($scope.credentials);
    let response = await mainService.auth($scope.credentials);
    console.log(response);
    if (response.error.error) {
      Swal.fire({
        icon: 'error',
        text: response.data.message
      });
      return;
    }

    let userLoged = response.data;
    
    let resultBusinessUsers = await mainService.findAllBusinessUsers();
    userLoged.businessUser = resultBusinessUsers.data.find(bu => bu.user.id == userLoged.id);
    localStorage.setItem(APP_CONFIG.TOKEN_NAME, JSON.stringify(userLoged));
    Swal.close();
    // Disparar evento global
    $rootScope.$broadcast("userLogged");
    $scope.menu = APP_CONFIG.MENU;
    $scope.menu = $scope.menu.filter(m => m.roleAlloweds && m.roleAlloweds.length).filter(m => m.roleAlloweds.includes(userLoged.userType));
    localStorage.setItem('menu', JSON.stringify($scope.menu[0]))
    $location.path($scope.menu[0].route);
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.toggleRegister = () => {
    $scope.isRegister = !$scope.isRegister;
  }

  $scope.togglePassword = () => {
    $scope.showPassword = !$scope.showPassword;
  }

  $scope.register = async () => {
    console.log($scope.newRegister);
    let newPerson = structuredClone($scope.newRegister.person);
    let resultPerson = await mainService.savePeople(newPerson);
    console.log(resultPerson);

  }
}])