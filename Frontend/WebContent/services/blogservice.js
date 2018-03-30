/**
 * BlogService
 */
app.factory('BlogService',function($http){
	var BASE_URL="http://localhost:9090/Middleware"
		var blogService={}
	blogService.addBlog=function(blog){
		return $http.post(BASE_URL+"/addblogpost", blog)
	}
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL+"/getblogs/"+0)
		
	}
	blogService.getBlogsApproved=function(){
		return $http.get(BASE_URL+"/getblogs/"+1)
		
	}
	blogService.getBlog=function(id){
		return $http.get(BASE_URL+"/getblog/"+id)
		
	}
	blogService.hasUserLikedBlog=function(id){
		return $http.get(BASE_URL+"/hasuserlikedblog/"+id)
	}
	blogService.approve=function(blog){
		return $http.put(BASE_URL+"/approve", blog)
	}
	blogService.reject=function(blog,rejectionReason){
		return $http.put(BASE_URL+"/reject/"+rejectionReason,blog)
	}	
	blogService.updateLikes=function(id){
		return $http.put(BASE_URL+"/updatelikes/"+id)
	}
	blogService.addComment=function(blogComment){
		return $http.post(BASE_URL+"/addcomment", blogComment)
	}
	blogService.getBlogComments=function(id){
		return $http.get(BASE_URL+"/blogcomments/"+id)
	}
	
	return blogService;
	
})
