package com.sneha.lnr.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String text;
	
	@OneToMany(mappedBy="post", fetch = FetchType.LAZY)
    private List<PLike> plikes;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_posts", 
        joinColumns = @JoinColumn(name = "post_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> users;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public List<PLike> getPlikes() {
		return plikes;
	}


	public void setPlikes(List<PLike> plikes) {
		this.plikes = plikes;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public Post() {
		
	}
	
	
}
