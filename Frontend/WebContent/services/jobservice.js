/**
 * JOB SERVICE
 */
app.factory('JobService',function($http)
{
	var BASE_URL="http://localhost:9090/Middleware"
	var JobService={}
	JobService.addJob=function(job){
		console.log(job)
		return $http.post(BASE_URL+ "/addjob", job);
		}
	JobService.getAllJobs=function(){
		
		return $http.get(BASE_URL+ "/alljobs");
		}
    JobService.getJob=function(id){
		
		return $http.get(BASE_URL+ "/getjob/"+id);
		}
	
	 return JobService;
	})