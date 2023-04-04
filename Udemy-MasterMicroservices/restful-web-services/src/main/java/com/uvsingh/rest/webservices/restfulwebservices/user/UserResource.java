package com.uvsingh.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> usrs = service.findAll();
		if(usrs.isEmpty()) {
			throw new UsersNotFoundException("[-] Users not found!!");
		}
		return usrs;
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User usr =  service.findOne(id);
		if(usr == null) {
			throw new UserNotFoundException("id - "+id);
		}
		
		// HATEOAS
		EntityModel<User> resource = EntityModel.of(usr);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getAllUsers()); // Enable us to build link from methods
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource; // data + link
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User usr =  service.deleteById(id);
		if(usr == null) {
			throw new UserNotFoundException("id - "+id);
		}
	}
	
	//HATEOAS
	// Hypermedia As The Engine Of Application State
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		// Location of resource that was created
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}") // allows to append something to the path
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
