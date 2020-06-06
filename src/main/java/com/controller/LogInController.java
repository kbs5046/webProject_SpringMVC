package com.controller;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.controller.dto.UserDto;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import com.service.UserService;

@Controller
public class LogInController {
	@Autowired
	private UserService userService;
	
	@GetMapping({"/","/login"})
	public String loginPage() {
 		return "login";
 	}
	
	@PostMapping("/auth")
	public String loginPost(@RequestParam String username,@RequestParam String password,Model model,HttpSession session) {
		UserDto userEntity = userService.authUser(username, password);
		if(userEntity !=null) {
			model.addAttribute("name", userEntity.getName());
			model.addAttribute("email", userEntity.getEmail());
			model.addAttribute("salutation", userEntity.getSalutation());
			
			session.setAttribute("uid", userEntity.getuID());
			session.setAttribute("username", userEntity.getUserName());
			session.setAttribute("name", userEntity.getName());
			session.setAttribute("email", userEntity.getEmail());
			session.setAttribute("salutation", userEntity.getSalutation());
			session.setAttribute("role", userEntity.getRole());
			return "success";
		}
		else {
			model.addAttribute("message", "Sorry username or password is not correct!");
 			return "login";
		}
	}
	
	@GetMapping("/signup")
	public String signUpPage() {
		return "signup";
	}
	
	@PostMapping("/signUp")
	public String signupPost(@ModelAttribute UserDto userDto) throws IOException {
		//MultipartFile into byte[]
		byte[] bphoto = userDto.getPhoto().getBytes();
		userDto.setBphoto(bphoto);
		userDto.setRole("customer");
		userDto.setDateCreated(new Timestamp(new Date().getTime()));
		userService.signup(userDto);
		return "login";
	}
	
	@GetMapping("/users")
	public String displayUsers(Model model) {
		List<UserDto> userList = userService.displayAll();
		model.addAttribute("signupList", userList);
		return "users";
	}
	
	@GetMapping("/deleteSignup")
	public String deleteUser(@RequestParam int sid,HttpSession session) {
		int uid = (int)session.getAttribute("uid");
		String role = (String)session.getAttribute("role");
		if(uid==sid || role.equalsIgnoreCase("admin")) {
			userService.deleteById(sid);
			return "redirect:/paginateUsers";
		}
		return "redirect:/paginateUsers?message= Hey! you can not delete others info";
	}
	
	@GetMapping("/selectSignup")
	public String  selectUser(@RequestParam int sid, Model model,HttpSession session) {
		UserDto userEntity = userService.selectById(sid);
		int uid = (int)session.getAttribute("uid");
		String role = (String)session.getAttribute("role");
		model.addAttribute("entity", userEntity);
		if(uid==sid || role.equalsIgnoreCase("admin")) {
			return "edituser";
		}
		model.addAttribute("message", "Hey! you can not edit others info");
		return "redirect:/paginateUsers";
	}
	
	@PostMapping("/editSignUp")
	public String editUser(@ModelAttribute UserDto userDto) {
		byte[] bphoto = null;
		try {
			bphoto = userDto.getPhoto().getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		userDto.setBphoto(bphoto);
		userService.updateUser(userDto);		
		return "redirect:/paginateUsers";
	}
	
	@GetMapping("/logOut")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "login";
	}
	
	@GetMapping("/paginateUsers")
 	public String showPaginatedData( @RequestParam(required=false,defaultValue="3") String plimit,@RequestParam(required=false,defaultValue="1") String  pageid, Model model) {
		int ppageid = Integer.parseInt(pageid);
		int pageLimit = Integer.parseInt(plimit);
		int totalRecords=userService.findTotalSignup();
		if(ppageid<=0) {
 			ppageid=1;
 		}
		if(ppageid>1) {
 			ppageid=(ppageid-1)*pageLimit+1;  
 		}
		if(ppageid > totalRecords) {
			ppageid = 1;
			pageid = "1";
		}
		List<UserDto> signupList=userService.getSignups(ppageid, pageLimit);
 		model.addAttribute("signupList", signupList);
 		model.addAttribute("recordStarts", ppageid);
 		model.addAttribute("pageSize", pageLimit);
 		model.addAttribute("pageid", pageid);
 		model.addAttribute("totalRecords", totalRecords);	
 		return "pusers";
	}
	
	@GetMapping("/search")
	public String searchPage() {
		return "search";
	}
	
	@GetMapping("/searchByName")
	public String searchUserByName(@RequestParam String searchName,Model model) {
		List<UserDto> searchList=userService.searchUser(searchName);
		if(searchList.size() >0) {
			model.addAttribute("resultList", searchList);
			return "sresult";
		}
		else {
			model.addAttribute("message", "No user exist with that Name");
			return "search";
		}
	}
	
	@GetMapping("/displayImage")
	public void showImage(@RequestParam  int uid,HttpServletResponse httpServletResponse) throws IOException {
		 byte[]  photo=userService.findImageById(uid);
		 ServletOutputStream outputStream =httpServletResponse.getOutputStream();
		 if(photo!=null) {
			 httpServletResponse.setContentType("image/png");
			 outputStream.write(photo);
		 }else {
			 outputStream.write(new byte[] {});
		 }
		 outputStream.flush();
	
	}
}



