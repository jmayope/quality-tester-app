let app = angular.module('qualityApp', ["ngRoute"]);

app.constant("APP_CONFIG", {
  VERSION: "1.0.0",
  API_URL: "http://localhost:8080/api",
  TOKEN_NAME: "quality-token",
  SPINNER_LOADING: `
    <div class="spinner-grow text-primary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-secondary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-success" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  `,
  CATEGORIES: [
    {id: 1, name: 'Calidad de Software'},
    {id: 2, name: 'Desarrollo de Software'},
    {id: 3, name: 'DevOps / Operación'},
  ],
  ATTRIBUTES: [
    {id: 1, name: 'fiabilidad'},
    {id: 2, name: 'rendimiento'},
    {id: 3, name: 'mantenibilidad'},
    {id: 4, name: 'seguridad'},
    {id: 5, name: 'usabilidad'},
  ],
  MEDITION_TYPES: [
    {id: 1, name: 'Mínimo / Máximo', type: 'range'},
    {id: 2, name: 'Escalas', type: 'scale'},
    {id: 3, name: 'Binario', type: 'boolean'},
  ],
  SCALE_TYPES: [
    {id: 1, name: 'Número'},
    {id: 2, name: 'Rango'},
  ],
  MENU: [
    {id: 1, name: 'Entidades', route: '/entity'},
    {id: 2, name: 'Módelos', route: '/evaluation-model', roleAlloweds: ['P', 'E']},
    {id: 3, name: 'Variables Globales', route: '/global-vars'},
    {id: 3, name: 'Reportes', route: '/report', roleAlloweds: ['R']},
    {id: 4, name: 'Usuarios', route: '/users', roleAlloweds: ['P']},
  ]
})

app.controller("indexCtrl", ["$scope", "$location", "APP_CONFIG", "$timeout", ($scope, $location, APP_CONFIG, $timeout) => {
  $scope.menu = APP_CONFIG.MENU;
  $scope.initView = undefined;

  $scope.init = () => {
    console.log("Verificando ruta");
    $scope.loadData();
    console.log($location.$$url);
    let menuSelected = undefined;
    console.log(localStorage.getItem("menu"));
    if (localStorage.getItem("menu") == "undefined") {
      menuSelected = $scope.menu.find(m => m.route == $location.$$url);
    } else {
      menuSelected = JSON.parse(localStorage.getItem("menu") || 'null');

    }
    console.log(menuSelected);
    if (menuSelected) {
      $scope.initView = menuSelected.route;
    }
    

    // Valiadación de Menu
    // $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME) || '{}');
    console.log($scope.userLoged);
    if ($scope.userLoged && $scope.userLoged.userType) {
      $scope.menu = $scope.menu.filter(m => m.roleAlloweds && m.roleAlloweds.length).filter(m => m.roleAlloweds.includes($scope.userLoged.userType));
      $scope.selectMenuItem($scope.menu[0]);
      if(!$scope.$$phase) {
        $scope.$apply();
      }
      return;
    }

    $scope.selectMenuItem($scope.menu.find(m => m.route === $location.$$url));
    // if ($scope.userLoged && $scope.userLoged) {
    //   $scope.menu = $scope.menu.filter()
    // }
  }

  $scope.$on("userLogged", function () {
    console.log("Usuario logueado");
    // actualizar datos
    $scope.initView = $scope.initView || "/entity";
    $scope.init();
  });

  $scope.userLoged = {};
  $scope.loadData = function () {
    $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME) || 'null');
  };

  $scope.menuSelected = undefined;
  $scope.selectMenuItem = (menuItem) => {
    $scope.menuSelected = menuItem;
    console.log($scope.menuSelected);
    localStorage.setItem('menu', JSON.stringify($scope.menuSelected));
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  

  $scope.logout = () => {
    Swal.fire({
      icon: 'question',
      text: '¿Estás seguro de salir del Sistema?',
      allowEscapeKey: false,
      allowEnterKey: false,
      showCancelButton: true,
      showConfirmButton: true
    }).then((choice) => {
      $timeout(() => {
        if (choice.isConfirmed) {
          localStorage.removeItem(APP_CONFIG.TOKEN_NAME);
          localStorage.removeItem('menu');
          $scope.userLoged = {};
          $location.path("/login");
        }
      }, 100)
    })
  }
}])