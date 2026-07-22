app.factory("authInterceptor", ["$q", "$window", function($q, $window) {
  return {
    request: function(config) {
      const token = $window.localStorage.getItem("token") || 'ApxEQ+svJ/BvQ8JtSJdDsnwv/gOXedkcox0XU8+snS3w6b4nSgDoyEms+0jGopE4SIRVvlqJmzPHqZYbkP0sxwgAAAB1eyJvcmlnaW4iOiJodHRwczovL3d3dy5mYWNlYm9vay5jb206NDQzIiwiZmVhdHVyZSI6IkNyYXNoUmVwb3J0aW5nU3RvcmFnZUFQSSIsImV4cGlyeSI6MTc3NjcyOTYwMCwiaXNTdWJkb21haW4iOnRydWV9';

      if (token) {
        config.headers = config.headers || {};
        config.headers.Authorization = `Bearer ${token}`;
      }

      return config;
    },
    requestError: function(rejection) {
      return $q.reject(rejection);
    },
    responseError: function(rejection) {
      // Opcional: manejar 401 globalmente (token vencido, etc.)
      if (rejection.status === 401) {
        // ej: redirigir a login
        // $window.location.href = "/login";
      }
      return $q.reject(rejection);
    }
  };
}])
.factory("mainService", ["$http", "APP_CONFIG", function($http, APP_CONFIG) {

  let apiUrl = `${APP_CONFIG.API_URL}`;

  return {
    auth: (body) => {
      return $http.post(`${apiUrl}/auth/login`, body).then(response => response.data);
    },
    findAllTypes: () => {
      return $http.get(`${apiUrl}/types`).then(response => response.data);
    },
    findAllTypesByCategory: (category) => {
      return $http.get(`${apiUrl}/types/by-category/${category}`).then(response => response.data);
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
    findAllPersonsBy: (filter) => {
      return $http.post(`${apiUrl}/people/filter`, filter).then(response => response.data);
    },
    saveEvaluation: (body) => {
      return $http.post(`${apiUrl}/evaluation-models`, body).then(response => response.data);
    },
    findAllEvaluableElements: () => {
      return $http.get(`${apiUrl}/evaluable-elements`).then(response => response.data);
    },
    findEvaluableElementById: (id) => {
      return $http.get(`${apiUrl}/evaluable-elements/${id}`).then(response => response.data);
    },
    saveEvaluableElements: (body) => {
      return $http.post(`${apiUrl}/evaluable-elements`, body).then(response => response.data);
    },
    saveManyEvaluableElements: (body) => {
      return $http.post(`${apiUrl}/evaluable-elements/list`, body).then(response => response.data);
    },
    saveEvaluationMetrics: (body) => {
      return $http.post(`${apiUrl}/evaluation-metrics/list`, body).then(response => response.data);
    },
    findAllEvaluationMetricsByEvaluationModelId: (evaluationModelId) => {
      return $http.get(`${apiUrl}/evaluation-metrics/by-evaluation-model/${evaluationModelId}`).then(response => response.data);
    },
    findAllEntities: () => {
      return $http.get(`${apiUrl}/business`).then(response => response.data);
    },
    saveEntities: (body) => {
      return $http.post(`${apiUrl}/business`, body).then(response => response.data);
    },
    updateEntities: (body) => {
      return $http.put(`${apiUrl}/business`, body).then(response => response.data);
    },
    savePeople: (body) => {
      return $http.post(`${apiUrl}/people`, body).then(response => response.data);
    },
    saveUsers: (body) => {
      return $http.post(`${apiUrl}/users`, body).then(response => response.data);
    },
    findEvaluationModelById: (id) => {
      return $http.get(`${apiUrl}/evaluation-models/${id}`).then(response => response.data);
    },
    findAllBusinessUsers: () => {
      return $http.get(`${apiUrl}/business-users`).then(response => response.data);
    },
    saveBusinessUsers: (body) => {
      return $http.post(`${apiUrl}/business-users`, body).then(response => response.data);
    },
    saveEvaluationResults: (body) => {
      return $http.post(`${apiUrl}/evaluation-results`, body).then(response => response.data)
    },
    saveEvaluationSections: (body) => {
      return $http.post(`${apiUrl}/evaluation-sections`, body).then(response => response.data)
    },
    findEvaluationSectionsByEvaluationModelId: (id) => {
      return $http.get(`${apiUrl}/evaluation-sections/by-evaluation-model/${id}`).then(response => response.data);
    },
    findEvaluationResultById: (id) => {
      return $http.get(`${apiUrl}/evaluation-results/${id}`).then(response => response.data);
    },
    findAllEvaluationResults: () => {
      return $http.get(`${apiUrl}/evaluation-results`).then(response => response.data);
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
    getReportOfEvaluationModels: () => {
      return $http.get(`${apiUrl}/evaluation-models/report`, {responseType: "blob"}).then(response => response.data);
    }
  }

}]);

app.config(["$httpProvider", function($httpProvider) {
  $httpProvider.interceptors.push("authInterceptor");
}]);