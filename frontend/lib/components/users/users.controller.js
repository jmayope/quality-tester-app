app.controller("usersCtrl", ["$scope", "mainService", function($scope, mainService) {
  $scope.users = [];
  $scope.filter = {};
  $scope.init = () => {
    console.log("iniciando Usuarios");
    $scope.getUsers();
  }

  $scope.getUsers = async () => {
    let resultUsers = await mainService.findAllUsers();
    console.log(resultUsers);
    $scope.users = resultUsers.data;

    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }
}]);