package com.userprofile.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userprofile.Entity.Admin;
import com.userprofile.Entity.Users;
import com.userprofile.Repository.AdminRepo;
import com.userprofile.Repository.UserRepo;
import com.userprofile.Service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	UserRepo userRepo;


//================For Admin=====================

	@PostMapping("/admin")
	public void addAdmin(@RequestBody Admin admin) {
		userService.saveAdmin(admin);
	}

	@GetMapping("/admin")
	public List<Admin> findAdmin() {
		return userService.findAllAdmin();
	}

	@PostMapping("/adminlogin")
	public Admin findAdmin(@RequestParam String email, String password) {
		Admin a = adminRepo.getByEmail(email);
		if (a != null && a.getPassword().equals(password)) {
			return a;
		} else {
			return null;
		}
	}
//    ====================For Upload photos=====================


	@PostMapping("/users")
    public UploadFileResponse addpost(
    		@RequestParam("file") MultipartFile file, @RequestParam(required = false)String description ) {

		try {
		String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		file.getContentType();
	    	Users user = new Users();

	    	user.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(filename).toUriString());
	    	user.setImageData(file.getBytes());

	    	user.setDescription(description);
	    	user.setFileType(file.getContentType());
	    	user.setImageData(filename);
	    	userRepo.save(user);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

        return new UploadFileResponse("", "",
                file.getContentType(), file.getSize());
    }

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		return fileName;

	}

	@GetMapping("/users")
	public List<Users> getAllDetals() {
		return userRepo.findAll();
	}

	}

