app.controller('evaluationModelCtrl', ["$scope", "mainService", "$timeout", function($scope, mainService, $timeout)  {
  
  $scope.init = () => {
    console.log("Iniciando ");
    mainService.findAllEvaluations().then((result) => {
      console.log(result.data);
      $scope.evaluationModels = result.data;
    })
    .catch((err) => {
      console.log(err);
    })
  }

  $scope.evaluationModels = [];

  $scope.newEvaluationModel = {};
  $scope.filter = {};
  $scope.isEvaluationOpen = false;

  $scope.toggleEvaluationModal = (item = undefined) => {
    if ($scope.isEvaluationOpen) {
      $scope.isEvaluationOpen = false;
    } else { 
      $scope.isEvaluationOpen = true;
      $scope.newEvaluationModel = {};
      if (item) {
        $scope.newEvaluationModel = item;
        $scope.newEvaluationModel.editing = true;
      }
    }
  }

  $scope.deleteEvaluationModel = (item) => {
    Swal.fire({
      icon: 'question',
      text: '¿Estás segura de eliminar la Evaluación?',
      showCancelButton: true,
      showConfirmButton: true,
      allowEscapeKey: false,
      allowOutsideClick: false
    }).then((result) => {
      if (result.isConfirmed) {
        $timeout(() => {
          $scope.evaluationModels.splice($scope.evaluationModels.indexOf(item), 1);
        }, 100);
      }
    })
  }
}]);