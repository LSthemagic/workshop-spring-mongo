package com.railansantana.workshop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railansantana.workshop.domain.Post;
import com.railansantana.workshop.repository.PostRepository;
import com.railansantana.workshop.services.exceptions.ObjectNotFound;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		System.err.println(obj);
		if (obj.isEmpty()) {
			throw new ObjectNotFound("Objeto não encontrado.");
		}
		return obj.get();
	}

	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}

	public List<Post> findByAuthorComments(String text) {
		return repository.searchAuthorComments(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}

	public void insert(Post obj) {
		repository.insert(obj);
	}

	public Post update(Post obj, String id) {
		Post newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setBody(obj.getBody());
		newObj.setTitle(obj.getBody());
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

}
