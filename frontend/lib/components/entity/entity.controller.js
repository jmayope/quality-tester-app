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

  $scope.sectionSelected = undefined;

  $scope.init = () => {
    console.log("Iniciando controller de Entidad");
    $scope.getEntities();
  }

  $scope.getEntities = () => {
    mainService.findAllEntities().then((result) => {
      $scope.entities = result.data;
      $scope.entities.map(e => {
        e.technologies = e.technologies ? e.technologies.split(',') : []
      });
      // console.log(entities);
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
    delete newEntity.elements;
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
      let elements = JSON.parse(JSON.stringify($scope.newEntity.elements));
      elements.map(e => {
        e.businessId = entityCreated.id;
        e.technologies = e.technologies ? e.technologies.join(',') : e.technologies;
      });
      mainService.saveManyEvaluableElements(elements).then((result) => {
        console.log(result);
        if (result.error.error) {
          Swal.fire({
            icon: 'error',
            text: `Hubo un error al momento de grabar las evaluaciones. ER: ${result.error.message}`
          });
          return;
        }
        
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

    }) 
    .catch((err) => {
      console.log(err);
    })

    
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

  deleteItem = (entity) =>  {
    console.log("Borrar entidad");
  }

}])