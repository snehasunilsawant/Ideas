package com.sneha.lnr.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sneha.lnr.models.Post;
import com.sneha.lnr.models.User;
import com.sneha.lnr.services.UserService;



@Controller
public class DashboardController {

	public UserService userService;		
	
	public DashboardController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/dashboard")
	public String index(HttpSession session,Model model,@ModelAttribute("post") Post post) {
		if ( session.getAttribute("userid") != null) {
			System.out.println(post.getText());
			model.addAttribute("alias", session.getAttribute("useralias"));
			model.addAttribute("posts", userService.findAllPosts());
			return "dashboard";
		}else {
			return "redirect:/";
		}
		
	}
	
	@PostMapping("/addPost")
	public String addEvent(@Valid @ModelAttribute("post") Post post, BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			System.out.println("Inside if " + result);
			return "redirect:/";
		}else {
			System.out.println(session.getAttribute("userid"));
			System.out.println("inside addPost");
			userService.savePost(post,session);
			System.out.println("post added");
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/like/{id}")
	public String eventDetails(@PathVariable("id") long id,Model model,HttpSession session) {
		System.out.println("inside like");
		userService.addLike(id , session);
		System.out.println("like added");
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/post/{id}")
	public String postDetails(@PathVariable("id") long id,Model model,HttpSession session) {
		Post post = userService.getPostdetails(id);
		model.addAttribute("post", post);		
		return "postdetails";
	}
	
	@RequestMapping("/user/{id}")
	public String userDetails(@PathVariable("id") long id,Model model,HttpSession session) {
		User user = userService.getUserdetails(id);
		model.addAttribute("user", user);
		return "userdetails";
	}
	
	@RequestMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") long id,Model model,HttpSession session) {
		userService.deletePosts(id);
		return "redirect:/dashboard";
	}
	
}//End of Controller
	