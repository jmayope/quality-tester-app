app.controller("entityCtrl", ["$scope", "mainService", function($scope, mainService) {

  $scope.isEntityOpen = false;
  $scope.newEntity = undefined;
  $scope.newElement = undefined;
  $scope.newTechnology = undefined;
  $scope.entities = [];
  $scope.sections = [
    {id: 1, name: "Datos Generales"},
    {id: 2, name: "Elementos Evaluables"}
  ];

  $scope.filter = {};

  $scope.sectionSelected = undefined;

  $scope.init = () => {
    console.log("Iniciando controller de Entidad");
    $scope.getEntities();
  }

  $scope.getEntities = () => {
    mainService.findAllEntities().then((result) => {
      $scope.entities = result.data;
      $scope.entities.map(e => {
        e.elements.map(v => {
          v.technologies = v.technologies ? v.technologies.split(',') : [];
        });
      });
    })
    .catch((err) => {
      console.log(err);
      Swal.fire({
        icon: 'error',
        text: `${err.toString()}`
      });
      return;
    });
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
    let newEntity = JSON.parse(JSON.stringify($scope.newEntity));
    if (!$scope.newEntity.editing) {
      delete newEntity.editing;
      newEntity.elements.map( e => {
        e.technologies = e.technologies.join(",");
      });
      mainService.saveEntities(newEntity).then((result) => {
        console.log(result);
        if (result.error.error) {
          Swal.fire({
            icon: 'error',
            text: result.error.message
          });
          return;
        }
        
        let entityCreated = result.data;
        Swal.fire({
          icon: 'success',
          text: 'Se grabo correctamente la entidad'
        });
        $scope.toggleEntity();
        $scope.getEntities();
        $scope.newEntity = undefined;
  
      }) 
      .catch((err) => {
        console.log(err);
      })
    } else {
      delete newEntity.editing;
      newEntity.elements.map( e => {
        e.technologies = e.technologies.join(",");
        e.businessId = newEntity.id;
        delete e.business;
      });
      mainService.updateEntities(newEntity).then((result) => {
        console.log(result);
        if (result.error.error) {
          Swal.fire({
            icon: 'error',
            text: result.error.message
          });
          return;
        }
        
        let entityCreated = result.data;
        Swal.fire({
          icon: 'success',
          text: 'Se grabó correctamente la entidad'
        });
        $scope.toggleEntity();
        $scope.getEntities();
        $scope.newEntity = undefined;
      }) 
      .catch((err) => {
        console.log(err);
      })
    }

    
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
          businessId: $scope.newEntity.id,
          technologies: []
        };
      }
    }
  }

  $scope.saveElement = () => {
    $scope.newEntity.elements.push($scope.newElement);
    $scope.newElement = undefined;
  }

  deleteItem = (entity) =>  {
    console.log("Borrar entidad");

  }

}])