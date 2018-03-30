package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDao;
import com.model.ErrorClazz;
import com.model.User;

@RestController
public class UserController {
	@Autowired
private UserDao userDao;
	public UserController()
	{
		System.out.println("UserController is Created");
	}
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		System.out.println("registeruser in usercontroller" +user);
		if(!userDao.isEmailUnique(user.getEmail()))
		{
			ErrorClazz error= new ErrorClazz(1,"Email already exist");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
		}
		try {
			userDao.registerUser(user);
			}
			catch(Exception e) {
				ErrorClazz error =new ErrorClazz(2,"Some required field are empty" +e.getMessage());
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?>login(@RequestBody User user,HttpSession session){
		User ValidUser=userDao.login(user);
		if(ValidUser==null){
			ErrorClazz error=new ErrorClazz(5,"Login Failed Invalid Email Id or Password" );
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		else
			{ 
			System.out.println("enter to login");
			ValidUser.setOnline(true);
		    userDao.update(ValidUser);
			session.setAttribute("currentuser", user.getEmail());
			return new ResponseEntity<User>(ValidUser,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		String email=(String)session.getAttribute("currentuser");
		System.out.println(email);
		if(email==null){
			ErrorClazz error=new ErrorClazz(4,"Please login..");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		user.setOnline(false);
		userDao.update(user);
		session.removeAttribute("currentuser");
		session.invalidate();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?>getUser(HttpSession session){
		String email=(String)session.getAttribute("currentuser");
		if(email==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorised access...");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/updateUser",method=RequestMethod.PUT)
	public ResponseEntity<?>updateUser(@RequestBody User user,HttpSession session){
		String email=(String)session.getAttribute("currentuser");
		if(email==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorised access...");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			userDao.update(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
					}
		catch(Exception e){
			ErrorClazz error = new ErrorClazz(5,"Unable to update userdetails..." +e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

