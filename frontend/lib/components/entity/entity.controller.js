app.controller("entityCtrl", ["$scope", function($scope) {

  $scope.isEntityOpen = false;
  $scope.newEntity = undefined;
  $scope.entities = [];
  $scope.init = () => {
    console.log("Iniciando controller de Entidad");
    $scope.getEntities();
  }

  $scope.getEntities = () => {
    
  }

  $scope.toggleEntity = (entity = undefined) => {
    if ($scope.isEntityOpen) {
      $scope.isEntityOpen = false;
    } else {
      $scope.isEntityOpen = true;
      $scope.newEntity = {};
      if (entity) {
        $scope.newEntity = JSON.parse(JSON.stringify(entity));
        $scope.newEntity.editing = true;
      }
    }
  }

  $scope.saveEntity = () => {

    Swal.fire({
      icon: 'success',
      text: 'Se grabo correctamente la entidad'
    });
    $scope.newEntity = undefined;
  }

}])