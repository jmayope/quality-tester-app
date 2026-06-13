app.factory("mainService", ["$http", "APP_CONFIG", function($http, APP_CONFIG) {

  let apiUrl = `${APP_CONFIG.API_URL}`;

  return {
    auth: (body) => {
      return $http.post(`${apiUrl}/auth/login`, body).then(response => response.data);
    },
    findAllTypes: () => {
      return $http.get(`${apiUrl}/types`).then(response => response.data);
    },
    findAllMetrics: () => {
      return $http.get(`${apiUrl}/metrics`).then(response => response.data);
    },
    findAllUsers: () => {
      return $http.get(`${apiUrl}/users`).then(response => response.data);
    },
    findAllEvaluations: () => {
      return $http.get(`${apiUrl}/evaluation-models`).then(response => response.data);
    },
    findAllMetricScales: () => {
      return $http.get(`${apiUrl}/metric-scales`).then(response => response.data);
    },
    updateEvaluation: (body) => {
      return $http.put(`${apiUrl}/evaluation-models/update`, body).then(response => response.data);
    },

    saveEvaluation: (body) => {
      return $http.post(`${apiUrl}/evaluation-models`, body).then(response => response.data);
    },
    findAllEvaluableElements: () => {
      return $http.get(`${apiUrl}/evaluable-elements`).then(response => response.data);
    },
    saveEvaluableElements: (body) => {
      return $http.post(`${apiUrl}/evaluable-elements`, body).then(response => response.data);
    },

    saveManyEvaluableElements: (body) => {
      return $http.post(`${apiUrl}/evaluable-elements/list`, body).then(response => response.data);
    },

    findAllEntities: () => {
      return $http.get(`${apiUrl}/business`).then(response => response.data);
    },
    saveEntities: (body) => {
      return $http.post(`${apiUrl}/business`, body).then(response => response.data);
    },
    saveMetrics: (body) => {
      return $http.post(`${apiUrl}/metrics`, body).then(response => response.data);
    },
    saveTypes: (body) => {
      return $http.post(`${apiUrl}/types`, body).then(response => response.data);
    },
    saveMetricScale: (body) => {
      return $http.post(`${apiUrl}/metric-scales`, body).then(response => response.data);
    },
    saveListMetricScales: (body) => {
      return $http.post(`${apiUrl}/metric-scales/list`, body).then(response => response.data);
    },
  }

}])