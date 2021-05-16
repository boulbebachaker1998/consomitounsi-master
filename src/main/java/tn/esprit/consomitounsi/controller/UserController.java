package tn.esprit.consomitounsi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.modal.User;
import tn.esprit.consomitounsi.service.UserService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder encoder;
	@PostMapping(value = "/adduser")
	private ResponseEntity<?> addUser (@RequestBody User user){
		
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.addUser(user);
	}
	@GetMapping(value="/getusers")
	private ResponseEntity<?> getUsers (){
		return userService.getUsers();
	}
	@GetMapping(value = "/getuser/{id}")
	private ResponseEntity<?> getUser (@PathVariable Long id ){
		return userService.getUser(id);

	}
	@PutMapping(value="/updateuser")
	private ResponseEntity<?> updateUser (@RequestBody User user ){
		System.out.println(user.toString());
		return userService.updateUser(user);

	}
	@DeleteMapping(value="/deleteuser/{id}")
	private ResponseEntity<?> deleteUser (@PathVariable Long id){
		return userService.deleteUser(id);

	}


}


