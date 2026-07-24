app.controller("entityCtrl", ["$scope", "mainService", "$timeout", function($scope, mainService, $timeout) {

  $scope.isEntityOpen = false;
  $scope.isBusinessUserOpen = false;
  $scope.newEntity = undefined;
  $scope.newElement = undefined;
  $scope.newTechnology = undefined;
  $scope.entities = [];
  $scope.businessUsers = [];

  $scope.isUserOpen = false;
  $scope.personFounds = [];
  $scope.businessSelected = undefined;
  $scope.search = {};
  $scope.genders = [
    {id: true, name: 'Masculino', icon: 'fa fa-male'},
    {id: false, name: 'Femenino', icon: 'fa fa-female'},
  ];
  $scope.userTypes = [];
  $scope.newRecord = {
    entityUser: {},
    user: {}
  };

  $scope.personSelected = undefined;

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
        $scope.get();
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


  $scope.toggleBusinessUser = async (business, reOpen = false) => {
    if ($scope.isBusinessUserOpen && !reOpen) {
      $scope.isBusinessUserOpen = undefined;
    } else {
      $scope.isBusinessUserOpen = true;
      $scope.businessUsers = [];
      $scope.businessSelected = business;
      let resultBusinessUsers = await mainService.findAllBusinessUsersByBusinessId(business.id);
      console.log(resultBusinessUsers);
      $scope.businessUsers = resultBusinessUsers.data;
      $scope.getUserTypes();
      
      if(!$scope.$$phase) {
        $scope.$apply();
      }
    }
  }

  $scope.getBusinessUsers = async () => {
    let resultBusinessUsers = await mainService.findAllBusinessUsersByBusinessId(business.id);
    console.log(resultBusinessUsers);
    $scope.businessUsers = resultBusinessUsers.data;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }


  $scope.getUserTypes = async () => {
    let resultTypes = await mainService.findAllTypesByCategory("TIPO_USUARIO");
    console.log(resultTypes);
    $scope.userTypes = resultTypes.data;
  }


  $scope.toggleUser = async (item = undefined) => {
    if ($scope.isUserOpen) {
      $scope.isUserOpen = false;
    } else {
      $scope.isUserOpen = true;
      $scope.newRecord = {
        entityUser: {},
        user: {}
      };
      if (item) {
        $scope.newRecord.user = structuredClone(item);
        $scope.newRecord.editing = true;
      }

      $scope.newRecord.entityUser.businessId = $scope.businessSelected.id;
    }
  }

  $scope.togglePerson = async () => {
    if ($scope.isPersonOpen) {
      $scope.isPersonOpen = false;
    } else {
      $scope.isPersonOpen = true;
      $scope.newPerson = {};
    }
  }

  $scope.searchPerson = async () => {
    if ($scope.search.done) {
      $scope.search.done = false;
      $scope.personSelected = undefined;
      $scope.personFounds = [];
    } else {
      let filter = $scope.search;
      $scope.search.proccesing = true;
      $scope.search.done = false;
      let resultPerson = await mainService.findAllPersonsBy(filter);
      $scope.search.proccesing = false;
      $scope.personFounds = resultPerson.data;
      $scope.search.done = true; 
    }
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.selectPerson = async (p) => {
    $scope.personSelected = structuredClone(p);
    $scope.newRecord.user.personId = p.id;
  }

  $scope.toggleGender = async (gender) => {
    $scope.newPerson.gender = gender.id;
  }
  
  $scope.validatePassword = async () => {
    if ($scope.newRecord.user.password && $scope.newRecord.confirmPassword) {
      $scope.newRecord.passwordEquals = $scope.newRecord.user.password === $scope.newRecord.confirmPassword;
    }
    console.log($scope.newRecord);
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.saveUser = async () => {
    console.log($scope.newRecord);
    let newUser = structuredClone($scope.newRecord.user);
    newUser.personId = $scope.personSelected.id;
    let resultUser = await mainService.saveUsers(newUser);
    console.log(resultUser);
    let newBusinessUser = structuredClone($scope.newRecord.entityUser);
    newBusinessUser.userId = resultUser.data.id;
    newBusinessUser.status = $scope.newRecord.user.status;
    let resultEntityUser = await mainService.saveBusinessUsers(newBusinessUser);
    console.log(resultEntityUser);
    Swal.fire({
      icon: 'success',
      text: 'Se grabó correctamente el usuario'
    });
    $scope.toggleUser();
    $scope.toggleBusinessUser($scope.businessSelected, true);
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.savePerson = async () => {
    console.log($scope.newPerson);
    let newPerson = structuredClone($scope.newPerson);
    let resultPerson = await mainService.savePeople(newPerson);
    console.log(resultPerson);
    Swal.fire({
      icon: 'success',
      text: 'Persona creada correctamente'
    });
    $scope.search.done = true;
    $scope.search.proccesing = false;
    $scope.personFounds = [resultPerson.data];
    $scope.personSelected = resultPerson.data;
    $scope.togglePerson();
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.toggleShowPassword = () => {
    $scope.newRecord.user.showPassword = !$scope.newRecord.user.showPassword;
  }

}])