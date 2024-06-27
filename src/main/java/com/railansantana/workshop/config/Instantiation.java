package com.railansantana.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.railansantana.workshop.DTO.AuthorDTO;
import com.railansantana.workshop.DTO.CommentDTO;
import com.railansantana.workshop.domain.Post;
import com.railansantana.workshop.domain.User;
import com.railansantana.workshop.repository.PostRepository;
import com.railansantana.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Titulo do post", "corpo post", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("21/03/2018"), "Titulo do post 2", "corpo post 2", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("texto1", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO c2 = new CommentDTO("texto2", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("texto3", sdf.parse("21/03/2018"), new AuthorDTO(bob));

		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(p1, p2));

		maria.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(maria);
	}

}
