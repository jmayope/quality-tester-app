app.controller('evaluationScoreCaptureCtrl', ["$scope", "APP_CONFIG","mainService", "$location", "$routeParams", function($scope, APP_CONFIG, mainService, $location, $routeParams) {
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
            s.optionsBinary = structuredClone($scope.optionsBinary);
            break;
          case s.metric && s.metric.meditionType.code === '1':
            s.list = [];
            for (let index = s.metric.minValue; index <= s.metric.maxValue; index++) {
              s.list.push({
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
            i.optionsBinary = structuredClone(s.optionsBinary);
            i.list = structuredClone(s.list);
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

  $scope.toggleMetricScaleSelected = (mt, parent, child = undefined) => {
    if (!child) {
      parent.metricScaleSelected = mt;
      parent.wasEvaluated = true;
      parent.evaluated = 100;
      $scope.recalculateEvaluates()
    }
  }

  $scope.recalculateEvaluates = async () => {
    $scope.itemEvaluates = $scope.evaluationModelSections.filter(s => s.wasEvaluated);
    $scope.evaluatesPercent = ($scope.itemEvaluates.length * 100) / $scope.evaluationModelSections.length;
  }

  $scope.clearSelection = async (s) => {
    s.metricScaleSelected = undefined;
    s.wasEvaluated = false;
    s.evaluated = undefined;
    $scope.recalculateEvaluates();
  }
}]);