app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:9090/Middleware"
		friendService.getAllSuggestedUsers=function()
		{
				return $http.get(BASE_URL +"/suggestedusers");
	}
	friendService.addFriend=function(toId)
	{ console.log(toId)
		return $http.post(BASE_URL +"/addfriend",toId );
	}
	friendService.getPendingRequests=function()
	{  
		return $http.get(BASE_URL +"/pendingrequests");
	}
	friendService.acceptRequest=function(request)
	{  
		return $http.put(BASE_URL +"/acceptrequest",request);
	}
	friendService.deleteRequest=function(request)
	{  
		return $http.put(BASE_URL +"/deleterequest",request);
	}
	friendService.getAllFriends=function()
	{  
		return $http.get(BASE_URL +"/friends");
	}
	return friendService;
})