package com.leonardovaladao.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserResource {

	private UserDAOService service;

	public UserResource(UserDAOService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> retriveAllUsers() {
		return this.service.findAll();
	}

	@GetMapping(path = "/{id}")
	public User retriveUserById(@PathVariable int id) {
		User userFound = this.service.findUserById(id);

		if (userFound == null) {
			throw new UserNotFoundException("id: " + id);
		}
		
		return userFound;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteUserById(@PathVariable int id) {
		this.service.deleteUserById(id);
	}
}
