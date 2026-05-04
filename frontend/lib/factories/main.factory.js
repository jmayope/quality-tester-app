app.factory("mainService", ["$http", "APP_CONFIG", function($http, APP_CONFIG) {

  let apiUrl = `${APP_CONFIG.API_URL}`;

  return {
    findAllEvaluations: () => {
      return $http.get(`${apiUrl}/evaluation-models`);
    },

    updateEvaluation: (body) => {
      return $http.put(`${apiUrl}/evaluation-models/update`, body);
    },

    saveEvaluation: (body) => {
      return $http.post(`${apiUrl}/evaluation-models`, body);
    }
  }

}])