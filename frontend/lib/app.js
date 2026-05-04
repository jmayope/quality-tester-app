let app = angular.module('qualityApp', ["ngRoute"]);

app.constant("APP_CONFIG", {
  VERSION: "1.0.0",
  API_URL: "http://localhost:8080/api"
})