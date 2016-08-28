<%--
  Created by IntelliJ IDEA.
  User: AlexJIANG
  Date: 12/18/15
  Time: 3:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<body>
  <a href="http://localhost:8080/elevator/rd">hello world</a>
  <div ng-app="myApp" ng-controller="customersCtrl">
    <ul>
      <li ng-repeat="x in names">
        {{ x.name + ', ' + x.age }}
      </li>
    </ul>
  time:${requestScope.time}
  </div>
  <script>
    var app = angular.module('myApp', []);
//    app.controller('customersCtrl', function($scope, $http) {
//      $http.get("http://localhost:8080/elevator/purejustjson")
//              .then(function (response) {$scope.names = response.data;});
//    });
    app.controller('customersCtrl', function($scope, $http) {
      $http.get("http://localhost:8080/elevator/testMAV").then(function (response) {$scope.names = response.data;});
    });
    alert(names);
  </script>

</body>
</html>
