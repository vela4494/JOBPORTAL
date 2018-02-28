/**
 * UserService
 */
app.factory('UserService',function($http)
{
	var BASE_URL="http://localhost:9090/Middleware"
	var userService={}
	userService.registerUser=function(user)
	{
		console.log(user)
		return $http.post(BASE_URL+ "/registeruser", user)
	}
	
	userService.login=function(user)
	{   console.log('userservice-->login') 
		console.log(user)
		return $http.post(BASE_URL +"/login", user)
	}
	
	userService.logout=function()
	{
		console.log("b4 logged out...")

		return $http.put(BASE_URL +"/logout")
		console.log("logged out...")
	}
	userService.getUser=function(){
		return $http.get(BASE_URL +"/getuser")
	}
	userService.updateUser=function(user){
		return $http.put(BASE_URL +"/updateUser",user)
	}
	
	return userService;
	})