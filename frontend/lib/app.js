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
    {id: 2, name: 'Módelos', route: '/evaluation-model'},
    {id: 3, name: 'Variables Globales', route: '/global-vars'},
    {id: 4, name: 'Usuarios', route: '/users'},
  ]
})

app.controller("indexCtrl", ["$scope", "$location", "APP_CONFIG", "$timeout", ($scope, $location, APP_CONFIG, $timeout) => {
  $scope.menu = APP_CONFIG.MENU;
  $scope.initView = undefined;
  $scope.$on("userLogged", function () {

    console.log("Usuario logueado");
    // actualizar datos
    $scope.initView = "/entity";
    $scope.init();
  });

  $scope.userLoged = {};
  $scope.loadData = function () {
    $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME) || '{}');
  };

  $scope.menuSelected = undefined;
  $scope.selectMenuItem = (menuItem) => {
    $scope.menuSelected = menuItem;
    console.log($scope.menuSelected);
  }

  $scope.init = () => {
    console.log("Verificando ruta");
    $scope.loadData();
    console.log($location.$$url);
    $scope.selectMenuItem($scope.menu.find(m => m.route === $scope.initView || $location.$$url));
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
          $scope.userLoged = {};
          $location.path("/login");
        }
      }, 100)
    })
  }
}])