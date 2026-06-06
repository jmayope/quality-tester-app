app.controller('globalVarsCtrl', ["$scope", "APP_CONFIG","mainService", function($scope, APP_CONFIG, mainService) {
  $scope.filter = {};
  $scope.categories = [];
  $scope.attributes = [];
  $scope.meditionTypes = [];
  $scope.scaleTypes = [];
  $scope.allMetricScales = [];
  $scope.types = [];
  $scope.metrics = [];
  $scope.isNewItemOpen = false;
  
  $scope.newMetric = undefined;
  $scope.newItem = undefined;

  $scope.init = () => {
    console.log("Inciaidndo");
    $scope.getTypes();
  }

  $scope.getTypes = () => {
    mainService.findAllTypes()
    .then((response) => {
      console.log(response);
      $scope.types = response.data;
      $scope.categories = $scope.types.filter(t => t.category === 'CATEGORIA_DE_METRICA');
      $scope.attributes = $scope.types.filter(t => t.category === 'ATRIBUTO_DE_METRICA');
      $scope.meditionTypes = $scope.types.filter(t => t.category === 'TIPO_DE_MEDICION');
      $scope.scaleTypes = $scope.types.filter(t => t.category === 'TIPO_DE_ESCALA');
      $scope.getMetrics();
    })
    .catch((err) => {
      console.log(err);
    })
  }

  $scope.getMetrics = () => {
    mainService.findAllMetrics()
    .then((metrics) => {
      $scope.metrics = metrics.data;
      mainService.findAllMetricScales()
      .then((metricScales) => {
        $scope.allMetricScales = metricScales.data;
        $scope.metrics.map(m => {
          m.scales = $scope.allMetricScales.filter(ms => ms.metric && ms.metric.id === m.id);
        });
        console.log($scope.metrics);
      })
      .catch((err) => {

      })
    })
    .catch((err) => {
      console.log(err);
    })
  }

  $scope.newItem = (type) => {
    switch (type) {
      case 'metric':
        $scope.newMetric = {};   
        break;
      case 'type':
        $scope.newType = {};
        break;
      default:
        break;
    }
  }

  $scope.toggleItem = (type = undefined, item = undefined) => {
    if ($scope.isNewItemOpen) {
      $scope.isNewItemOpen = false;
    } else {
      $scope.isNewItemOpen = true;
      $scope.newItem = {
        type: type
      };
      switch (type) {
        case 'metric':
          $scope.newMetric = {};
          if (item) {
            $scope.newMetric = structuredClone(item);
            $scope.newItem.editing = true;
          }
          break;
        case 'type':
          $scope.newType = {};
          if (item) {
            $scope.newType = structuredClone(item);
            $scope.newType.editing = true;
          }
          break;
        default:
          break;
      }
    }
  }

  $scope.selectMeditionType = () => {
    if ($scope.newItem.meditionType) {
      $scope.newMetric.meditionType = $scope.newItem.meditionType;
      $scope.newScale = undefined;
      console.log($scope.newMetric);
      switch ($scope.newItem.meditionType.code) {
        case '2':
          $scope.newMetric.meditionType.scales = [];
          break;
        default:
          break;
      }
    }
  }

  $scope.toggleScale = (scale = undefined, drop = false) => {
    if ($scope.newScale) {
      $scope.newScale = undefined;
    } else {
      if (drop) {
        $scope.newMetric.meditionType.scales.splice($scope.newMetric.meditionType.scales.indexOf(scale), 1);
      } else {
        $scope.newScale = {};
        if (scale) {
          $scope.newScale = JSON.parse(JSON.stringify(scale));
          $scope.newScale.editing = true;
        }
      }
    }
  }

  $scope.saveScale = () => {
    switch ($scope.newScale.type) {
      case '2':
        console.log($scope.newMetric.meditionType.scales.find(s => $scope.newScale.to >= s.to || $scope.newScale.of <= s.of));
        if ($scope.newMetric.meditionType.scales.find(s => $scope.newScale.to <= s.to || $scope.newScale.to <= s.of || $scope.newScale.of <= s.to || $scope.newScale.of <= s.of)) {
          Swal.fire({
            icon: 'warning',
            text: 'Verifica los datos ingresados estas causando conflicto con los rangos'
          });
          return;
        }
        break;
      case '1':
        console.log($scope.newMetric.meditionType.scales.find(s => $scope.newScale.to >= s.to || $scope.newScale.of <= s.of));
        if ($scope.newMetric.meditionType.scales.find(s => $scope.newScale.to <= s.value || $scope.newScale.of <= s.value)) {
          Swal.fire({
            icon: 'warning',
            text: 'Verifica los datos ingresados estas causando conflicto con los rangos'
          });
          return;
        }
        break;
      default:
        break;
    }
    $scope.newMetric.meditionType.scales.push($scope.newScale);
    $scope.newScale = undefined;
  }

  $scope.saveItem = (type) => {
    Swal.fire({
      html: APP_CONFIG.SPINNER_LOADING,
      text: 'Guardando información',
      alloallowEnterKey: false,
      allowOutsideClick: false,
      showCancelButton: false,
      showConfirmButton: false
    });
    switch (type) {
      case 'metric':
        $scope.newMetric.minValue =$scope.newMetric.meditionType.minValue;
        $scope.newMetric.maxValue =$scope.newMetric.meditionType.maxValue;
        console.log($scope.newItem);
        console.log($scope.newMetric);
        let newMetric = structuredClone($scope.newMetric);
        // newMetric.meditionType = newMetric.meditionType.id;
        newMetric.status = newMetric.status || true;
        let scales = newMetric.meditionType.scales || [];
        delete newMetric.meditionType.scales;
        delete newMetric.meditionType.minValue;
        delete newMetric.meditionType.maxValue;
        // Necesitas crear Tipos
        mainService.saveMetrics(newMetric)
        .then((data) => {

          if (!data.data) {
            Swal.fire({
              icon: 'error',
              text: 'Hubo un error al momento de crear la Metrica'
            });
            return;
          }
          if (scales.length) {
            scales.map(s => {
              s.metric = data.data;
              s.status = true;
            });
            mainService.saveListMetricScales(scales)
            .then((metricScalesCreateds) => {
              Swal.close();
              $scope.toggleItem();
              
              Swal.fire({
                icon: 'success',
                text: 'Se guardo la metrica correctamente'
              });
              $scope.getMetrics();
              console.log(data);
              console.log(metricScalesCreateds);
            })
            .catch((err) => {
              console.log(err);
            })
          } else {
            Swal.close();
            $scope.toggleItem();
            
            Swal.fire({
              icon: 'success',
              text: 'Se guardo la metrica correctamente'
            });
            $scope.getMetrics();
            console.log(data);
          }
        })
        .catch((err) => {
          Swal.close();
          Swal.fire({
            icon: 'error',
            text: err.message
          });
          console.log(err);
        })
        break;
      case 'type':
        mainService.saveTypes($scope.newType)
        .then((result) => {
          console.log(result);
          Swal.close();
          $scope.getTypes();
          $scope.toggleItem();
          Swal.fire({
            icon: 'success',
            text: result.message
          });
        })
        .catch((err) => {
          console.log(err);
          Swal.close();
          Swal.fire({
            icon: 'error',
            text: err.message
          });
        })
        break;
      default:
        break;
    }
  }

}])