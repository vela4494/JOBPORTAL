package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BlogPostDao;
import com.dao.UserDao;
import com.model.BlogPost;
import com.model.ErrorClazz;
import com.model.User;

@Controller
public class BlogPostController {
@Autowired
private BlogPostDao blogPostDao;
@Autowired
private UserDao userDao;
@RequestMapping(value="/addblogpost", method=RequestMethod.POST)
public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
	String email=(String)session.getAttribute("currentuser");
	if(email==null){
		ErrorClazz error=new ErrorClazz(5,"Unauthorised access....");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	blogPost.setPostedon(new Date());
	User postedBy=userDao.getUser(email);
	blogPost.setPostedBy(postedBy);
	try
	{
		blogPostDao.addBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}catch(Exception e)
	{
		ErrorClazz error=new ErrorClazz(6,"Unable to post blog..." +e.getMessage());
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
}
@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
public ResponseEntity<?>getAllBlogs(@PathVariable int approved,HttpSession session){
	String email=(String)session.getAttribute("currentuser");
	if(email==null){
		ErrorClazz error=new ErrorClazz(5,"Unauthorised access....");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	if(approved==0){
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz error=new ErrorClazz(7,"Access Denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	List<BlogPost>blogs=blogPostDao.listofBlogs(approved);
	return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
}
@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
public ResponseEntity<?>getBlog(@PathVariable int id,HttpSession session){
	String email=(String)session.getAttribute("currentuser");
	if(email==null){
		ErrorClazz error=new ErrorClazz(5,"Unauthorised access....");
		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
	}
	BlogPost blogPost=blogPostDao.getBlog(id);
	return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
}

	@RequestMapping(value="/approve",method=RequestMethod.PUT)
	public ResponseEntity<?> approve(@RequestBody BlogPost blog,HttpSession session){
		String email=(String)session.getAttribute("currentuser");
		if(email==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorised access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz error=new ErrorClazz(7,"Access Denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.approve(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/reject/{rejectionReason}",method=RequestMethod.PUT)
	public ResponseEntity<?> reject(@RequestBody BlogPost blog,@PathVariable String rejectionReason,HttpSession session){
		String email=(String)session.getAttribute("currentuser");
		if(email==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorised access....");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz error=new ErrorClazz(7,"Access Denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.reject(blog,rejectionReason);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
