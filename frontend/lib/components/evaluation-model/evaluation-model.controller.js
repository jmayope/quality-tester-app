app.controller('evaluationModelCtrl', ["$scope", "mainService", "$timeout", "APP_CONFIG", "$location", function($scope, mainService, $timeout, APP_CONFIG, $location)  {
  
  $scope.userLoged = undefined;
  $scope.evaluationModels = [];
  $scope.metrics = [];
  $scope.newEvaluationModel = {};
  $scope.filter = {};
  $scope.isEvaluationOpen = false;
  $scope.isMetricOpen = false;
  $scope.newSection = undefined;
  $scope.newSubSection = undefined;
  $scope.sectionToAssign = undefined;
  $scope.sectionToAddSubSection = undefined;
  $scope.sectionsToShow = [];
  $scope.allEvaluationMetrics = [];
  $scope.evaluationMetrics = [];
  $scope.isUsageModelOpen = false;
  $scope.newEvaluationResult = undefined;
  $scope.evaluationModelSelected = undefined;
  $scope.allEvaluableElements = [];
  $scope.evaluableElements = [];

  $scope.init = () => {
    console.log("Iniciando ");
    $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME));
    $scope.getEvaluationModels();
  }
  
  $scope.getEvaluationModels = async () => {
    let resultEvaluations = await mainService.findAllEvaluations();
    console.log(resultEvaluations.data);
    $scope.evaluationModels = resultEvaluations.data;
    $scope.evaluationModels.map(e => {
      e.sections.map(s => {
        s.evaluationMetric = e.metrics.find(x => x.evaluationSectionId === s.id);
      })
    })
    console.log($scope.evaluationModels);
    $scope.evaluationModels.sort((a, b) => a.id - b.id);
    if (!$scope.userLoged.isAdmin) {
      $scope.getEvaluableElements();
    }
    $scope.getMetrics();
  }

  $scope.getEvaluableElements = async () => {
    let resultEvaluableElements = await mainService.findAllEvaluableElements();
    $scope.allEvaluableElements = resultEvaluableElements.data;
    console.log($scope.allEvaluableElements);
    $scope.evaluableElements = $scope.allEvaluableElements.filter(e => e.business.id === $scope.userLoged.businessUser.business.id);
    console.log($scope.evaluableElements);
  }

  $scope.getMetrics = async () => {
    let resultMetrics = await mainService.findAllMetrics();
    $scope.metrics = resultMetrics.data;
    $scope.evaluationModels.map(e => {
      e.sections.map(s => {
        if (s.evaluationMetric) {
          s.metric = $scope.metrics.find(m => m.id === s.evaluationMetric.metricId);
        }
      });
    });
    console.log($scope.evaluationModels);
    $scope.getMetricScales();
  }

  $scope.getMetricScales = () => {
    mainService.findAllMetricScales()
    .then((metricScales) => {
      $scope.metrics.map(m => {
        m.scales = metricScales.data.filter(ms => ms.metric.id === m.id);
      });
      console.log($scope.metrics);
    })
    .catch((err) => {
      console.log(err);
    })
  }
  


  $scope.sections = [
    {id: 1, name: "General", icon: 'fa fa-cog'},
    {id: 2, name: "Secciones", icon: 'fa fa-table-columns'},
  ];

  $scope.additionSections = [
    {id: 3, name: "Métricas", icon: 'fa fa-ruler'},
  ];
  $scope.sectionSelected = undefined;

  $scope.toggleEvaluationModal = async (item = undefined) => {
    if ($scope.isEvaluationOpen) {
      $scope.isEvaluationOpen = false;
    } else { 
      $scope.isEvaluationOpen = true;
      $scope.newEvaluationModel = {
        sections: [],
        metrics: []
      };
      $scope.sectionsToShow = $scope.sections;
      $scope.selectSection($scope.sections[0]);
      if (item) {
        $scope.newEvaluationModel = structuredClone(item);
        $scope.newEvaluationModel.sections = $scope.newEvaluationModel.sections || [];
        $scope.newEvaluationModel.metrics = $scope.newEvaluationModel.metrics || [];
        $scope.newEvaluationModel.editing = true;
        $scope.sectionsToShow = [...$scope.sectionsToShow, ...$scope.additionSections];
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
      body = structuredClone($scope.newEvaluationModel);
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
        $scope.newSection.evaluationModelId = $scope.newEvaluationModel.id;
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
        $scope.sectionToAddSubSection = section;
        $scope.newSubSection = {};
        if (subsection) {
          $scope.newSubSection = JSON.parse(JSON.stringify(subsection));
          $scope.newSubSection.editing = true;
        }
        $scope.newSubSection = {
          evaluationModelId: $scope.newEvaluationModel.id,
          parent: section.id
        };
      }
    }
  }

  $scope.saveSubSection = async (section) => {
    if ($scope.newSubSection.editing) {
      console.log("Actualizar");
    } else {
      if ($scope.newSubSection.parent) {
        let newSubSection = $scope.newSubSection;
        let resultSubSection = await mainService.saveEvaluationSections(newSubSection);
        console.log(resultSubSection);
        $scope.newSubSection.id = resultSubSection.data.id;
        $scope.getEvaluationModels();
      }
      section.sections.push($scope.newSubSection);
    }
    $scope.newSubSection = undefined;
    $scope.sectionToAddSubSection = undefined;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.saveSection = async () => {
    if ($scope.newEvaluationModel.id) {
      // Ingresar a la BD
      let newSection = $scope.newSection;
      console.log(newSection);
      let resultSection = await mainService.saveEvaluationSections(newSection);
      console.log(resultSection);
      $scope.newSection.id = resultSection.data.id;
      $scope.getEvaluationModels();
    }
    $scope.newEvaluationModel.sections.push($scope.newSection);
    $scope.newSection = undefined;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.toggleMetric = async (section = undefined) => {
    if ($scope.isMetricOpen) {
      let newEvaluationMetric = {
        evaluationModelId: $scope.newEvaluationModel.id,
        metricId: $scope.sectionToAssign.metric.id,
        evaluationSectionId: $scope.sectionToAssign.id,
        status: true
      };
      
      let result = await mainService.saveEvaluationMetrics([newEvaluationMetric]);
      console.log(result);
      $scope.isMetricOpen = false;
      $scope.sectionToAssign = undefined;
      if(!$scope.$$phase) {
        $scope.$apply();
      }
    } else {
      $scope.sectionToAssign = section;
      $scope.isMetricOpen = true;
      $scope.newEvaluationModel.metrics.push({
        evaluationSectionId: section
      })
    }
  }

  $scope.export = async () => {
    let responseReport = await mainService.getReportOfEvaluationModels();
    
    const blob = new Blob([responseReport], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);

    const link = document.createElement('a');
    link.href = url;
    link.download = 'reporte_modelos.pdf';
    document.body.appendChild(link);
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
  }

  $scope.toggleUsageModel = (evaluation) => {
    if (!$scope.userLoged.isAdmin) {
      if ($scope.isUsageModelOpen) {
        $scope.isUsageModelOpen = false;
        $scope.evaluationModelSelected = undefined;
      } else {
        $scope.isUsageModelOpen = true;
        $scope.evaluationModelSelected = evaluation;
        $scope.newEvaluationResult = {
          state: "R",
          userId: $scope.userLoged.id,
          evaluationModelId: evaluation.id
        };
      }
    }
  }

  $scope.saveEvaluationResult = async () => {
    console.log($scope.newEvaluationResult);
    let resultEvaluationResult = await mainService.saveEvaluationResults($scope.newEvaluationResult);
    console.log(resultEvaluationResult);
    let evaluationResultId = resultEvaluationResult.data.id;
    $location.path(`/evaluation-score-capture/${evaluationResultId}`);
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }
}]);