app.controller("usersCtrl", ["$scope", "mainService", function($scope, mainService) {
  $scope.users = [];
  $scope.filter = {};
  $scope.search = {};
  $scope.entities = [];
  $scope.isUserOpen = false;
  $scope.personFounds = [];
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
  $scope.init = () => {
    console.log("iniciando Usuarios");
    $scope.getUserTypes();
  }
  
  $scope.getUserTypes = async () => {
    let resultTypes = await mainService.findAllTypesByCategory("TIPO_USUARIO");
    console.log(resultTypes);
    $scope.userTypes = resultTypes.data;
    $scope.getEntities();
  }

  $scope.selectUserType = () => {
    $scope.newRecord.user.isAdmin = false;
    if (!$scope.newRecord.user.userType) {
      $scope.newRecord.user.business_id = undefined;
    }
  }
  
  $scope.getEntities = async () => {
    let resultEntities = await mainService.findAllEntities();
    console.log(resultEntities);
    $scope.entities = resultEntities.data;
    $scope.getUsers();
  }

  $scope.getUsers = async () => {
    let resultUsers = await mainService.findAllUsers();
    console.log(resultUsers);
    $scope.users = resultUsers.data;
    $scope.users.map(u => {
      u.type = $scope.userTypes.find(t => t.code === u.userType);
    })
    if(!$scope.$$phase) {
      $scope.$apply();
    }
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

  $scope.toggleIsAdmin = async () => {
    $scope.newRecord.user.isAdmin = !$scope.newRecord.user.isAdmin;
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
    $scope.getEntities();
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
}]);