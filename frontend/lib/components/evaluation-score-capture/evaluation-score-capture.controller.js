app.controller('evaluationScoreCaptureCtrl', ["$scope", "APP_CONFIG","mainService", "$location", "$routeParams", function($scope, APP_CONFIG, mainService, $location, $routeParams) {
  $scope.userLoged = undefined;
  $scope.evaluationResultId = undefined;
  $scope.evaluationResult = undefined;
  $scope.evaluableElement = undefined;
  $scope.loading = true;
  $scope.evaluationModelSections = [];
  $scope.init = () => {
    console.log("comenzando aqui");
    $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME));
    console.log($scope.userLoged);
    let evaluationResultId = parseInt($routeParams.evaluationResultId);
    console.log(evaluationResultId);
    $scope.evaluationResultId = evaluationResultId;

    if (!$scope.evaluationResultId) {
      Swal.fire({
        icon: 'error',
        text: "Estas en el lugar incorrecto"
      });
      $location.path("/evaluation-model");
      if(!$scope.$$phase) {
        $scope.$apply();
      }
      return;
    }
    $scope.getEvaluationResult();
  }

  $scope.getEvaluationResult = async () => {
    let resultEvaluationResult = await mainService.findEvaluationResultById($scope.evaluationResultId);
    $scope.evaluationResult = resultEvaluationResult.data;
    console.log($scope.evaluationResult)
    if (!$scope.evaluationResult) {
      Swal.fire({
        icon: 'error',
        text: "Código de Evaluación es Incorrecto."
      });
      $location.path("/evaluation-model");
      if(!$scope.$$phase) {
        $scope.$apply();
      }
      return;
    }
    
    let resultEvaluationSections = await mainService.findEvaluationSectionsByEvaluationModelId($scope.evaluationResult.evaluationModel.id);
    console.log(resultEvaluationSections);
    $scope.evaluationModelSections = resultEvaluationSections.data;
    $scope.loading = false;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.toggleDetail = (detail) => {
    detail.showDetail = !detail.showDetail;
  }

  $scope.toggleComment = (item) => {
    item.addComment = !item.addComment;
    item.obs = undefined;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }
}]);