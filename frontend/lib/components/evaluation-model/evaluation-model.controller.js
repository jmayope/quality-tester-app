app.controller('evaluationModelCtrl', ["$scope", "mainService", "$timeout", function($scope, mainService, $timeout)  {
  
  $scope.init = () => {
    console.log("Iniciando ");
    $scope.getEvaluationModels();
  }
  
  $scope.getEvaluationModels = () => {
    mainService.findAllEvaluations().then((result) => {
      console.log(result.data);
      $scope.evaluationModels = result.data.data;
      console.log(result);
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
  $scope.newSection = undefined;
  $scope.newSubSection = undefined;

  $scope.sections = [
    {id: 1, name: "General", icon: 'fa fa-cog'},
    {id: 2, name: "Secciones", icon: 'fa fa-table-columns'},
    {id: 3, name: "Métricas", icon: 'fa fa-ruler'},
  ];
  $scope.sectionSelected = undefined;

  $scope.toggleEvaluationModal = (item = undefined) => {
    if ($scope.isEvaluationOpen) {
      $scope.isEvaluationOpen = false;
    } else { 
      $scope.isEvaluationOpen = true;
      $scope.newEvaluationModel = {
        sections: [],
        evaluationMetrics: []
      };
      $scope.selectSection($scope.sections[0]);
      if (item) {
        $scope.newEvaluationModel = item;
        $scope.newEvaluationModel.sections = $scope.newEvaluationModel.sections || [];
        $scope.newEvaluationModel.evaluationMetrics = $scope.newEvaluationModel.evaluationMetrics || [];
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

  $scope.toggleDetail = (section) => {
    section.showDetail = !section.showDetail;
  }

  $scope.selectSection = (section) => {
    $scope.sectionSelected = section;
  }

  $scope.toggleSection = (section = undefined, drop = false) => {
    if ($scope.newSection) {
      $scope.newSection = undefined;
    } else {
      if (drop) {
        $scope.newEvaluationModel.sections.splice($scope.newEvaluationModel.sections.indexOf(section), 1);
      } else {
        $scope.newSection = {
          sections: []
        };
        if (section) {
          $scope.newSection = JSON.parse(JSON.stringify(section));
          $scope.newSection.sections = $scope.newSection.sections || [];
        }
      }
    }
  }

  $scope.toggleSubSection = (section = undefined, subsection = undefined, drop = false) => {
    if ($scope.newSubSection) {
      $scope.newSubSection = undefined;
    } else {
      if (drop) {
        section.sections.splice(section.sections.indexOf(subsection), 1);
      } else {
        $scope.newSubSection = {};
        if (subsection) {
          $scope.newSubSection = JSON.parse(JSON.stringify(subsection));
          $scope.newSubSection.editing = true;
        }
      }
    }
  }

  $scope.saveSubSection = (section) => {
    if ($scope.newSubSection.editing) {
      console.log("Actualizar");
    } else {
      section.sections.push($scope.newSubSection);
    }
    $scope.newSubSection = undefined;
  }

  $scope.saveSection = () => {
    $scope.newEvaluationModel.sections.push($scope.newSection);
    $scope.newSection = undefined;
  }
}]);