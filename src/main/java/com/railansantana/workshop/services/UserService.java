package com.railansantana.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railansantana.workshop.DTO.UserDTO;
import com.railansantana.workshop.domain.User;
import com.railansantana.workshop.repository.UserRepository;
import com.railansantana.workshop.services.exceptions.ObjectNotFound;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		System.err.println(obj);
		if (obj.isEmpty()) {
			throw new ObjectNotFound("Objeto não encontrado.");
		}
		return obj.get();
	}

	public void insert(User obj) {
		repository.insert(obj);
	}
	
	public User fromDto(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

}
