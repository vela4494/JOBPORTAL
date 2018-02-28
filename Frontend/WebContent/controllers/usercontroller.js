/**
 * 
 */
app.controller('UserController',function($scope,$rootScope,$location,UserService,$cookieStore){
	$scope.registerUser=function(){
		alert('Entering Usercontroller in frontend ' + $scope.user)
		console.log('entering usercontroller in frontend' +$scope.user)
		
		UserService.registerUser($scope.user).then(
				function(response){
					alert('Registered Successfully....please login again..')
					$location.path('/home');
								},function(response){
									$scope.error=response.data
								})
	}
	$scope.login=function(user){
		console.log('Usercontroller-->login')
		console.log($scope.user)
		UserService.login($scope.user).then(
				function(response){
			$rootScope.loggedInUser=response.data
			$cookieStore.put('currentuser',response.data)
			$location.path('/home'); 
			},function(response){
				console.log('error')
			$scope.error=response.data
			$location.path('/login');
		})
	}
	if($rootScope.loggedInUser!=undefined){
		UserService.getUser().then(
		function(response){
			$scope.user=response.data
		},
		function(response){
			if(response.status==401)
				$location.path('/login');
		})
	}
	$scope.updateUser=function(user){
		UserService.updateUser(user).then(
		function(response){
			alert('updated user profile successfully....')
			$rootScope.loggedInUser=response.data
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home');
		},function(response){
			if(response.status==401)
				$location.path('/login');
			else
				$scope.error=response.data
		})
	}
})