/**
 * Angular JS module and config SPA
 */
var app=angular.module('app',['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/register',{
		templateUrl:'views/registrationform.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
	} )
	.when('/edituserprofile',{
		templateUrl:'views/edituserprofile.html',
		controller:'UserController'
	} )
	.when('/addjob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	} )
	.when('/alljobs',{
		templateUrl:'views/jobslist.html',
		controller:'JobController'
	} )
	.when('/getjob/:id',{
		templateUrl:'views/jobdetail.html',
		controller:'JobController'
	} )
	.when('/addblog',{
		templateUrl:'views/blogform.html',
		controller:'BlogCtrl'
	} )
	.when('/blogsnotapproved',{
		templateUrl:'views/blogsnotapproved.html',
		controller:'BlogCtrl'
	} )
	.when('/blogsapproved',{
		templateUrl:'views/blogsapproved.html',
		controller:'BlogCtrl'
	} )
	.when('/getblog/:id',{
		templateUrl:'views/blogdetails.html',
		controller:'BlogDetailsCtrl'
	} )
	.when('/getblognotapproved/:id',{
		templateUrl:'views/blogapprovalform.html',
		controller:'BlogDetailsCtrl'
	} )
	.otherwise({
		templateUrl:'views/home.html'
	})
} )
app.run(function($location,$rootScope,$cookieStore,UserService){
	
	if($rootScope.loggedInUser==undefined)
	    $rootScope.loggedInUser=$cookieStore.get('currentuser')
	    
	 $rootScope.logout=function()
	{
		console.log("logout function")
		delete $rootScope.loggedInUser;
		$cookieStore.remove('currentuser')
		UserService.logout().then(
				function(response){
					console.log("Loggout successfully..")
			
			$rootScope.message="Successfully Loggedout.."
				$location.path('/login');
		},function(response){
			console.log(response.status)
			
		})

	}
})