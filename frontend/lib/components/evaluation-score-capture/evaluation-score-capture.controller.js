app.controller('evaluationScoreCaptureCtrl', ["$scope", "APP_CONFIG","mainService", "$location", "$routeParams", "$timeout", function($scope, APP_CONFIG, mainService, $location, $routeParams, $timeout) {
  $scope.userLoged = undefined;
  $scope.evaluationResultId = undefined;
  $scope.evaluationResult = undefined;
  $scope.evaluableElement = undefined;
  $scope.loading = true;
  $scope.evaluationModelSections = [];
  $scope.evaluationMetrics = [];
  $scope.metrics = [];
  $scope.metricScales = [];
  $scope.viewList = true;
  $scope.evaluationResults = [];
  $scope.evaluationStates = [];
  $scope.filter = {};
  $scope.itemEvaluates = [];
  $scope.evaluatesPercent = 0;
  $scope.optionsBinary = [
    {id: 1, value: 0, name: 'NO' },
    {id: 2, value: 1, name: 'SI' },
  ]
  $scope.init = () => {
    console.log("comenzando aqui");
    $scope.userLoged = JSON.parse(localStorage.getItem(APP_CONFIG.TOKEN_NAME));
    console.log($scope.userLoged);
    let evaluationResultId = parseInt($routeParams.evaluationResultId);
    console.log(evaluationResultId);
    $scope.evaluationResultId = evaluationResultId;

    if (!$scope.evaluationResultId) {
      $scope.viewList = true;
      $scope.getEvaluationResults();
    } else {
      $scope.viewList = false;
      $scope.getEvaluationResult();
    }
  }

  $scope.getEvaluationResults = async () => {
    let resultEvaluationResults = await mainService.findAllEvaluationResults();
    $scope.evaluationResults = resultEvaluationResults.data;
    $scope.evaluationResults.map(r => {
      r.evaluableElement.technologies = r.evaluableElement.technologies ? r.evaluableElement.technologies.split(',') : [];
    })
    console.log($scope.evaluationResults);
    $scope.getEvaluationStates();
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.getEvaluationStates = async () => {
    let resultEvaluationStates = await mainService.findAllTypesByCategory("ESTADO_EVALUACION");
    $scope.evaluationStates = resultEvaluationStates.data;
    $scope.evaluationResults.map(e => {
      let stateFound = $scope.evaluationStates.find(s => s.code === e.state);
      if (stateFound) {
        e.stateName = stateFound.name;
      }
    });
    if ($scope.evaluationResult) {
      let stateFound = $scope.evaluationStates.find(s => s.code === $scope.evaluationResult.state);
      if (stateFound) {
        $scope.evaluationResult.stateName = stateFound.name;
      }
    }
    $scope.getMetricScales();
  }
  
  $scope.getMetricScales = async () => {
    let resultMetricScales = await mainService.findAllMetricScales();
    $scope.metricScales = resultMetricScales.data;
    $scope.getMetrics();
  }
  
  $scope.getMetrics = async () => {
    let resultMetrics = await mainService.findAllMetrics();
    $scope.metrics = resultMetrics.data;
    $scope.evaluationModelSections.map(s => {
      let evaluationMetricFound = $scope.evaluationMetrics.find(em => em.evaluationSectionId === s.id);
      if (evaluationMetricFound) {
        
        s.metric = $scope.metrics.find(m => m.id === evaluationMetricFound.metricId);
        // ESCALAS
        switch (true) {
          case s.metric && s.metric.meditionType.code === '2':
            s.scales = $scope.metricScales.filter(ms => ms.metric.id === s.metric.id);
            break;
          case s.metric && s.metric.meditionType.code === '3':
            s.scales = structuredClone($scope.optionsBinary);
            break;
          case s.metric && s.metric.meditionType.code === '1':
            s.scales = [];
            for (let index = s.metric.minValue; index <= s.metric.maxValue; index++) {
              s.scales.push({
                id: index, 
                name: index,
                value: index
              });
            }
            break;
          default:
            break;
        }
        
        if (s.sections.length) {
          s.sections.map(i => {
            i.scales = structuredClone(s.scales);
          })
        }
      }
    })
    console.log($scope.evaluationModelSections);
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

    let resultEvaluationMetrics = await mainService.findAllEvaluationMetricsByEvaluationModelId($scope.evaluationResult.evaluationModel.id);
    $scope.evaluationMetrics = resultEvaluationMetrics.data;
    console.log($scope.evaluationMetrics);
    
    

    console.log($scope.evaluationModelSections);

    $scope.loading = false;
    $scope.getEvaluationStates();
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.goToEvaluation = (evaluation) => {
    $location.path(`/evaluation-score-capture/${evaluation.id}`);
  }

  $scope.back = () => {
    $location.path(`/evaluation-score-capture`);
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

  $scope.toggleMetricScaleSelected = async (mt, parent, child = undefined) => {
    console.log(mt);
    if ($scope.evaluationResult.state !== "C") {
      let item = undefined;
      if (!child) {
        parent.metricScaleSelected = mt;
        parent.wasEvaluated = true;
        parent.evaluated = 100;
        item = parent;
      } else {
        console.log("#aqui");
        child.metricScaleSelected = mt;
        child.wasEvaluated = true;
        child.evaluated = 100;
        item = child;
      }
      let newEvaluationResultDetail = {
        evaluationResultId: $scope.evaluationResult.id,
        evaluationSectionId: item.id,
        score: mt.value == 0 ? mt.until : mt.value,
        obs: 'INGRESO AUTOMATICO',
        status: true
      };
      let resultEvaluationResultDetail = await mainService.saveEvaluationResultDetails(newEvaluationResultDetail);
      console.log(resultEvaluationResultDetail);
      $scope.recalculateEvaluates()
    } else {
      Swal.fire({
        icon: 'warning',
        text: 'Ya se completó la evaluación; no puedes modificarla'
      });
      return;
    }
  }

  $scope.recalculateEvaluates = async () => {
    $scope.evaluationModelSections.map(s => {
      if (s.sections && s.sections.length) {
        let sectionsEvaluateds = s.sections.filter(i => i.wasEvaluated).length;
        s.evaluated = (sectionsEvaluateds * 100) / s.sections.length;
        if (sectionsEvaluateds === s.sections.length) {
          s.wasEvaluated = true;
        }
      }
    })
    $scope.itemEvaluates = $scope.evaluationModelSections.filter(s => s.wasEvaluated);
    $scope.evaluatesPercent = ($scope.itemEvaluates.length * 100) / $scope.evaluationModelSections.length;
    if(!$scope.$$phase) {
      $scope.$apply();
    }
  }

  $scope.clearSelection = async (s) => {
    s.metricScaleSelected = undefined;
    s.wasEvaluated = false;
    s.evaluated = undefined;
    $scope.recalculateEvaluates();
  }

  $scope.finishEvaluation = async () => {
    Swal.fire({
      icon: 'question',
      text: '¿Estás seguro de finalizar la Evaluación?',
      showCancelButton: true,
      showConfirmButton: true,
      allowOutsideClick: false,
      allowEscapeKey: false,
      allowEnterKey: false
    }).then(async (choice) => {
      $timeout(async () => {
        if (choice.isConfirmed) {
          let resultEvaluationResult = await mainService.updateEvaluationResult($scope.evaluationResult.id, {state: "C"});
          $location.path(`/evaluation-score-capture`);
        }
      }, 100);
    })
  }



}]);