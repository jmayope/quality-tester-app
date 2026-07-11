app.controller('evaluationModelCtrl', ["$scope", "mainService", "$timeout", function($scope, mainService, $timeout)  {
  
  $scope.init = () => {
    console.log("Iniciando ");
    $scope.getEvaluationModels();
  }
  
  $scope.getEvaluationModels = () => {
    mainService.findAllEvaluations().then((result) => {
      console.log(result.data);
      $scope.evaluationModels = result.data;
      console.log(result);
      $scope.evaluationModels.sort((a, b) => a.id - b.id);
      $scope.getMetrics();
    })
    .catch((err) => {
      console.log(err);
    });
  }

  $scope.getMetrics = () => {
    mainService.findAllMetrics()
    .then((metrics) => {
      $scope.metrics = metrics.data;
      $scope.getMetricScales();
    })
    .catch((err) => {
      console.log(err);
    })
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
  $scope.evaluationMetrics = [];


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
        $scope.newEvaluationModel = item;
        $scope.newEvaluationModel.sections = $scope.newEvaluationModel.sections || [];
        $scope.newEvaluationModel.metrics = $scope.newEvaluationModel.metrics || [];
        $scope.newEvaluationModel.editing = true;
        $scope.sectionsToShow = [...$scope.sectionsToShow, ...$scope.additionSections];
        let result = await mainService.findAllEvaluationMetricsByEvaluationModelId($scope.newEvaluationModel.id);
        console.log(result);
        console.log($scope.newEvaluationModel);
        $scope.evaluationMetrics = result.data;
        $scope.newEvaluationModel.sections.map(s => {
          s.metric = ($scope.evaluationMetrics.find(e => e.evaluationSection.id === s.id) || {}).metric;
        })
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
    $scope.sectionToAddSubSection = undefined;
  }

  $scope.saveSection = () => {
    $scope.newEvaluationModel.sections.push($scope.newSection);
    $scope.newSection = undefined;
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
}]);