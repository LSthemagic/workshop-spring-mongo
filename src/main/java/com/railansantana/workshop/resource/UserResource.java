package com.railansantana.workshop.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railansantana.workshop.DTO.UserDTO;
import com.railansantana.workshop.domain.User;
import com.railansantana.workshop.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> obj = service.findAll();
		List<UserDTO> list = obj.stream().map(x -> new UserDTO(x)).toList();
		return ResponseEntity.ok().body(list);
	}


}
