package com.dao;

import com.model.BlogPost;
import com.model.BlogPostLikes;

public interface BlogPostLikesDao {
	public BlogPostLikes hasUserLikedBlog(int blogId,String email);
	public BlogPost updateLikes(int id,String email);

}
