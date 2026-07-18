app.controller('reportCtrl', ["$scope", "APP_CONFIG","mainService", function($scope, APP_CONFIG, mainService) {
  $scope.exportTypes = [
    {id: 1, name: 'Excel .xlsx'},
    {id: 2, name: 'PDF .pdf'},
  ];
  
  $scope.filter = {};
  $scope.evaluationModels = [];
  $scope.users = [];

  $scope.init = () => {
    console.log("comenzando aqui");
    $scope.getUsers();
  }
  
  $scope.getUsers = async () => {
    let resultUsers = await mainService.findAllUsers();
    $scope.users = resultUsers.data;
    $scope.getEvaluationModels();
  }
  $scope.getEvaluationModels = async () => {
    let resultEvaluationModels = await mainService.findAllEvaluations();
    $scope.evaluationModels = resultEvaluationModels.data;
    console.log($scope.evaluationModels);

    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }



}]);