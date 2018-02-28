/**
 * JOBCONTROLLER
 */
app.controller('JobController',function($scope,$rootScope,$location,$routeParams,JobService){
	var id=$routeParams.id;
	$scope.addJob=function(){
		JobService.addJob($scope.job).then(
		function(response){
			alert('Job details posted successfully....')
			$location.path('/home');
		},function(response){
			$rootScope.error=response.data
			if(response.status==401)
				$location.path('/login');
		})
	}
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login');
	})
	if(id!=undefined){
	JobService.getJob(id).then(function(response){
		$scope.job=response.data
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login');
	})
	}
})