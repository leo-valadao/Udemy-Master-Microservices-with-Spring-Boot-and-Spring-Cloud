package com.leonardovaladao.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@RequestMapping(path = "/users-jpa")
public class UserJPAResource {

//	private UserDAOService service;
	
	private UserRepository repository;
	
	private PostRepository postRepository;

	public UserJPAResource(UserDAOService service, UserRepository repository, PostRepository postRepository) {
//		this.service = service;
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping
	public List<User> retriveAllUsers() {
		return this.repository.findAll();
	}

	@GetMapping(path = "/{id}")
	public User retriveUserById(@PathVariable int id) {
		Optional<User> userFound = this.repository.findById(id);

		if (userFound.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		return userFound.get();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteUserById(@PathVariable int id) {
		this.repository.deleteById(id);
	}
	
	@GetMapping(path = "/hateoas/{id}")
	public EntityModel<User> retriveUserByIdWithHateoas(@PathVariable int id) {
		Optional<User> userFound = this.repository.findById(id);

		if (userFound.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(userFound.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@GetMapping(path = "/{id}/posts")
	public List<Post> retrievePostsFromUser(@PathVariable int id) {
		Optional<User> userFound = this.repository.findById(id);

		if (userFound.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		return userFound.get().getPosts();
	}
	
	@PostMapping(path = "/{id}/posts")
	public ResponseEntity<User> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/{id}/posts/{idPost}")
	public Post retrievePostByIdFromUser(@PathVariable int id, @PathVariable int idPost) {
		Optional<User> userFound = this.repository.findById(id);

		if (userFound.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}		
		
		Predicate<? super Post> predicate = post -> post.getId().equals(id); 
		Post postFound = userFound.get().getPosts().stream().filter(predicate).findFirst().orElse(null);
		
		if (postFound == null) {
			throw new PostNotFoundException("id: "+idPost);
		}
		
		return postFound;
	}
}
