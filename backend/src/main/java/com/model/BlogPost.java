package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="blogpost_s180250")
public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
 private int id;
 private String blogTitle;
 @Lob
 private String blogcontent;
 private Date postedon;
 @ManyToOne
 private User postedBy;
 private int likes;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getBlogcontent() {
	return blogcontent;
}
public void setBlogcontent(String blogcontent) {
	this.blogcontent = blogcontent;
}
public Date getPostedon() {
	return postedon;
}
public void setPostedon(Date postedon) {
	this.postedon = postedon;
}
public User getPostedBy() {
	return postedBy;
}
public void setPostedBy(User postedBy) {
	this.postedBy = postedBy;
}
public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
 
 
}
