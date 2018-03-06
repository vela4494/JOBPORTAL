/**
 * #/getblog/id
 */
app.controller('BlogDetailsCtrl',function($scope,$rootScope,$location,$sce,BlogService,$routeParams)
		{ var id=$routeParams.id; 
		
		BlogService.getBlog(id).then(
				function(response){
					$scope.blog=response.data
					$scope.content=$sce.trustAsHtml($scope.blog.blogcontent)
				},function(response){
					$rootScope.error=response.data
					if(response.status==401)
						$location.path('/login');
				})
		$scope.approve=function(blog){
			
			BlogService.approve(blog).then(function(response){
				$location.path('/blogsnotapproved')
			},function(response){
				$rootScope.error=response.data
				if(response.status==401)
					$location.path('/login')
			})
		}
		
		$scope.reject=function(blog){
			
			BlogService.reject(blog,$scope.rejectionReason).then(function(response){
				$location.path('/blogsnotapproved')
			},function(response){
				$rootScope.error=response.data
				if(response.status==401)
					$location.path('/login')
			})
		}
		$scope.showRejectionTxt=function(){
			$scope.rejectionTxt=true;
		}

	})