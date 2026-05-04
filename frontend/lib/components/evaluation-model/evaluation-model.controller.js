app.controller('evaluationModelCtrl', ["$scope", "mainService", "$timeout", function($scope, mainService, $timeout)  {
  
  $scope.init = () => {
    console.log("Iniciando ");
    $scope.getEvaluationModels();
  }
  
  $scope.getEvaluationModels = () => {
    mainService.findAllEvaluations().then((result) => {
      console.log(result.data);
      $scope.evaluationModels = result.data;
      $scope.evaluationModels.sort((a, b) => a.id - b.id);
    })
    .catch((err) => {
      console.log(err);
    });
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
          mainService.updateEvaluation($scope.newEvaluationModel)
          .then((result) => {
            console.log(result);
            $scope.evaluationModels.splice($scope.evaluationModels.indexOf(item), 1);
          })
          .catch((err) => {
            console.log(err);
          })
        }, 100);
      }
    })
  }

  $scope.saveEvaluationModel = () => {
    console.log($scope.newEvaluationModel);
    let body = undefined;
    if ($scope.newEvaluationModel.editing) {
      $scope.newEvaluationModel.code = $scope.newEvaluationModel.abbr;
      body = JSON.parse(JSON.stringify($scope.newEvaluationModel));
      mainService.updateEvaluation(body)
      .then((result) => {
        console.log(result);
        Swal.fire({
          icon: 'success',
          text: 'Se actualizó el Modelo correctamente'
        });
        $scope.toggleEvaluationModal();
        $scope.getEvaluationModels();
      })
      .catch((err) => {
        console.log(err);
      })
    } else {
      $scope.newEvaluationModel.status = true;
      $scope.newEvaluationModel.code = $scope.newEvaluationModel.abbr;
      body = JSON.parse(JSON.stringify($scope.newEvaluationModel));
      mainService.saveEvaluation(body)
      .then((result) => {
        console.log(result);
        Swal.fire({
          icon: 'success',
          text: 'Se registro el Modelo correctamente'
        });
        $scope.toggleEvaluationModal();
        $scope.getEvaluationModels();
      })
      .catch((err) => {
        console.log(err);
      })
    }
  }
}]);