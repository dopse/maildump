/* Imports */

import angular from 'angular';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

import './style.less';

/* Application creation */

var app = angular.module('MainApp', []);

/* Controllers */

app.controller('MainController', function($rootScope, $scope, $window, $timeout, $sce) {

	/* Attributes */

	$scope.socket = false;
	$scope.stompClient = false;

	$scope.emails = [];
	$scope.email = false;

	/* Methods */

	$scope.processEmail = function(email) {
		email.trustedContent = 	$sce.trustAsHtml(email.content);

		return email;
	};

	$scope.openAttachment = function(attachment) {
		$window.open('/email/attachment/' + attachment.id)
	};

	$scope.selectEmail = function(email) {
		console.log(email);
		if($scope.isEmailSelected(email))
		{
			$scope.email = false;
		}
		else
		{
			$scope.email = email;
		}
	};

	$scope.isEmailSelected = function(email) {
		return ($scope.email === email);
	};

	$scope.initialize = function() {

		var parseStompReturn = function(data) {
			return JSON.parse(data.body);
		};

		/* Open WebSocket connection */

		$scope.socket = new SockJS('/maildump');
		$scope.stompClient = Stomp.over($scope.socket);

		$scope.stompClient.connect({}, function() {

			$scope.stompClient.subscribe('/email/add', function(data) {
				$timeout(function() {
					$scope.emails.push($scope.processEmail(parseStompReturn(data)));
				});
			});

			$scope.stompClient.subscribe('/email/delete', function(data) {
				//TODO
			});

			$scope.stompClient.subscribe('/email/list', function(data) {
				$timeout(function() {
					$scope.emails = [];

					angular.forEach(parseStompReturn(data), function(email) {
						$scope.emails.push($scope.processEmail(email));
					});
				});
			});
		});

	};

	/* Bootstrap */

	$scope.initialize();

});