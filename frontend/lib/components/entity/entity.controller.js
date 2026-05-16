app.controller("entityCtrl", ["$scope", function($scope) {

  $scope.isEntityOpen = false;
  $scope.newEntity = undefined;
  $scope.newElement = undefined;
  $scope.newTechnology = undefined;
  $scope.entities = [];
  $scope.sections = [
    {id: 1, name: "Datos Generales"},
    {id: 2, name: "Elementos Evaluables"}
  ];

  $scope.sectionSelected = undefined;

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
      $scope.selectSection($scope.sections[0]);
      $scope.isEntityOpen = true;
      $scope.newEntity = {
        elements: []
      };
      if (entity) {
        $scope.newEntity = JSON.parse(JSON.stringify(entity));
        $scope.newEntity.elements = $scope.newEntity.elements || [];
        $scope.newEntity.editing = true;
      }
    }
  }

  $scope.selectSection = (section) => {
    $scope.sectionSelected = section;
  }

  $scope.toggleTechnology = (technology, drop) => {
    if (drop) {
      $scope.newElement.technologies.splice($scope.newElement.technologies.indexOf(technology), 1);
    } else {
      console.log(technology);
      if ($scope.newElement.technologies.includes(technology)) {
        Swal.fire({
          icon: 'warning',
          text: 'Esta tecnologia ya se encuentra agregada',
        });
        return;
      }
      $scope.newElement.technologies.push(technology);
      var el = angular.element(document.querySelector('#technology'));
      el.val("");
      technology = undefined;
      $scope.newTechnology = undefined;
    }
  }

  $scope.saveEntity = () => {

    Swal.fire({
      icon: 'success',
      text: 'Se grabo correctamente la entidad'
    });
    $scope.newEntity = undefined;
  }

  $scope.toggleElement = (element = undefined) => {
    if ($scope.newElement) {
      $scope.newElement = undefined;
    } else {
      // Editar Elemento
      if (element) {
        $scope.newElement = JSON.parse(JSON.stringify(element));
      } else {
        $scope.newElement = {
          technologies: []
        };
      }
    }
  }

  $scope.saveElement = () => {
    $scope.newEntity.elements.push($scope.newElement);
    $scope.newElement = undefined;
  }

  $scope.saveEntity = () => {
    console.log($scope.newEntity);
  }

}])