package com.sneha.lnr.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sneha.lnr.models.PLike;
import com.sneha.lnr.models.Post;
import com.sneha.lnr.models.User;
import com.sneha.lnr.models.UserPost;
import com.sneha.lnr.repositories.LikeRepository;
import com.sneha.lnr.repositories.PostRepository;
import com.sneha.lnr.repositories.UserPostRepository;
import com.sneha.lnr.repositories.UserRepository;

@Service
public class UserService {
	
	final UserRepository userRepository;
	final PostRepository postRepository;
	final LikeRepository likeRepository;
	final UserPostRepository userPostRepository;
	

	public UserService(UserRepository userRepository,PostRepository postRepository,LikeRepository likeRepository,UserPostRepository userPostRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.likeRepository = likeRepository;
		this.userPostRepository = userPostRepository;
	}
	
	
	public String saveUser(User user, String c_password, HttpSession session) {
		User UserEmail = userRepository.findByEmail(user.getEmail());
		if(!user.getPassword().equals(c_password) ) {
			System.out.println("password dont match");
			return "password dont match";
		}
		if( UserEmail != null ){
			System.out.println("Email Exist");
			return "Email Exist";			
		}
		else {
			System.out.println("UserEmail : " + UserEmail);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(user);
			System.out.println("User id : " +  user.getId());
			System.out.println("User name : " +  user.getAlias());
			session.setAttribute("userid", user.getId());
			session.setAttribute("useralias", user.getAlias());		
			return "User Created";			
		}	
		
	}
	
	
	public boolean login(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		
		if( user != null) {
			if( BCrypt.checkpw(password, user.getPassword()) ) {
				session.setAttribute("userid", user.getId());
				session.setAttribute("useralias", user.getAlias());	
				return true;
			}
			else {
				System.out.println("Password not correct");
				return false;
			}
		}
		System.out.println("Email & password not correct");
		return false;
	}
	
	
	public List<Post> findAllPosts(){
		return postRepository.findAll();
	}
	
	public void savePost(Post post , HttpSession session) {
		User user = userRepository.findOne((Long) session.getAttribute("userid"));
		System.out.println(user.getAlias());
		postRepository.save(post);
		System.out.println("pos saved in post table");
		System.out.println(post.getId());
		UserPost u1 = new UserPost(user , post);
		System.out.println("object created ");
		userPostRepository.save(u1);	
		System.out.println("object saves");
	}
	
	public void addLike(long postid , HttpSession session) {
		Post post = postRepository.findOne(postid);
		User user = userRepository.findOne((Long) session.getAttribute("userid"));
		System.out.println("got post and user name" + post.getText() + user.getAlias());
		PLike plike =  likeRepository.findByUserAndPost(user, post);
		if ( plike == null )
		{		PLike l = new PLike(user,post);				
				likeRepository.save(l);	
				System.out.println("like added");
			
		}else {
			System.out.println("already present");
			System.out.println(plike);
		}
		
	}
	
	public Post getPostdetails(long id) {
		return postRepository.findOne(id);
	}
	
	public User getUserdetails(long id) {
		return userRepository.findOne(id);
	}
	
	public void deletePosts(long id) {
		postRepository.delete(id);
	}
	
}//End of service
