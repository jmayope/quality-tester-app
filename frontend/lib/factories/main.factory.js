app.factory("mainService", ["$http", "APP_CONFIG", function($http, APP_CONFIG) {

  let apiUrl = `${APP_CONFIG.API_URL}`;

  return {
    auth: (body) => {
      return $http.post(`${apiUrl}/auth/login`, body);
    },
    findAllUsers: () => {
      return $http.get(`${apiUrl}/users`);
    },  
    findAllEvaluations: () => {
      return $http.get(`${apiUrl}/evaluation-models`);
    },

    updateEvaluation: (body) => {
      return $http.put(`${apiUrl}/evaluation-models/update`, body);
    },

    saveEvaluation: (body) => {
      return $http.post(`${apiUrl}/evaluation-models`, body);
    },

    findAllEntities: (body) => {
      return $http.post(`${apiUrl}/entities/by`, body);
    },

    saveMetrics: (body) => {
      return $http.post(`${apiUrl}/metrics`, body);
    }
  }

}])