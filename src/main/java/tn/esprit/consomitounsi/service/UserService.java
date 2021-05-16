package tn.esprit.consomitounsi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.consomitounsi.modal.User;
import tn.esprit.consomitounsi.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	public ResponseEntity<?> addUser(User user) {
		if (userRepository.existsByEmail(user.getEmail()) == null) {
			return new ResponseEntity<>("Email already taken !", HttpStatus.FOUND);
		} else {
			if (userRepository.findByUsername(user.getUsername()) == null) {
				return new ResponseEntity<>("UserName already taken !", HttpStatus.FOUND);
			}
		}
		return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
	}

	public ResponseEntity<?> getUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<?> getUser(Long id) {
		return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
	}

	public ResponseEntity<?> updateUser(User user) {
		User oldUser=userRepository.findById(user.getId()).get();
		oldUser.setUsername(user.getUsername());
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setEmail(user.getEmail());
		oldUser.setAddresse(user.getAddresse());
		oldUser.setPhone(user.getPhone());
		oldUser.setUpdated(new Date());
		if(!user.getPassword().equals(oldUser.getPassword())) {
			oldUser.setPassword(encoder.encode(user.getPassword()));
		}
		return new ResponseEntity<>(userRepository.save(oldUser), HttpStatus.OK);
	}

	public ResponseEntity<?> deleteUser(Long id) {
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	public User findUserById(long id) {
		return this.userRepository.findById(id).get();
	}


}