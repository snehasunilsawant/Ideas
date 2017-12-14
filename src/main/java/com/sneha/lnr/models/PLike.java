package com.sneha.lnr.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PLike {
	
	@Id
	@GeneratedValue
	private long id;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Post getPost() {
		return post;
	}



	public void setPost(Post post) {
		this.post = post;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	


	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public PLike() {
		
	}
	
	public PLike(User user,Post post) {
		this.user=user;
		this.post=post;
		this.count=1;
	}
	
}
