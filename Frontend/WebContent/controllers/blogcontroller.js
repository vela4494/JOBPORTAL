/**
 * BlogCtrl
 */
app.controller('BlogCtrl',function($scope,$rootScope,$location,BlogService){
	$scope.addBlog=function(){
		
		BlogService.addBlog($scope.blog).then(
				function(response){
					alert('BlogPost is added successfully and it is waiting for approval..');
					$location.path('/home');
				},function(response){
					
					$rootScope.error=response.data
					if(response.status==401)
						$location.path('/login');
					
				})			
	}
	if($rootScope.loggedInUser.role=='ADMIN')
	BlogService.getBlogsWaitingForApproval().then(
				function(response){
		$scope.BlogsWaitingForApproval=response.data
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login');
		}) 
	
		BlogService.getBlogsApproved().then(
		function(response){
			$scope.BlogsApproved=response.data
		},function(response){
			$rootScope.error=response.data
			if(response.status==401)
				$location.path('/login');
			}		
		
		
		
		)
	
	
	
})		
