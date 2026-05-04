app.factory("mainService", ["$http", "APP_CONFIG", function($http, APP_CONFIG) {

  let apiUrl = `${APP_CONFIG.API_URL}`;

  return {
    findAllEvaluations: () => {
      return $http.get(`${apiUrl}/evaluation-models`);
    }
  }

}])