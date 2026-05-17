app.controller("usersCtrl", ["$scope", "mainService", function($scope, mainService) {
  $scope.users = [];
  $scope.filter = {};
  $scope.init = () => {
    console.log("iniciando Usuarios");
    $scope.getUsers();
  }

  $scope.getUsers = () => {
    mainService.findAllUsers()
    .then((response) => {
      console.log(response);
      $scope.users = response.data;
    })
    .catch((err) => {
      console.log("Error => ", err);
    })
  }
}]);