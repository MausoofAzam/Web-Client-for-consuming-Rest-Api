package com.snort.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.snort.app.entity.Posts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("posts/api/v1")
public class PostConsumerController {

	// call your Producer API
	// MONO: single object
	// Flux : list of object or array of object

	@Autowired
	private WebClient webClient;

	@GetMapping("/findAll") // getting String response
	public Flux<Posts> FindAll() {

		Flux<Posts> flux = webClient.get().uri("/posts").retrieve().bodyToFlux(Posts.class);
		return flux;
	}

	@PostMapping("/new") // getting json response
	public Mono<Posts> newPosts(@RequestBody Posts postBody) {

		return webClient.post().uri("/posts").bodyValue(postBody).retrieve().bodyToMono(Posts.class);
	}

	@PutMapping("/updates")
	public Mono<Posts> updates(@RequestBody Posts postBody) {

		return webClient.put().uri("posts/"+postBody.getId()).bodyValue(postBody).retrieve().bodyToMono(Posts.class);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<String> deletePost(@PathVariable int id) {

		return webClient.delete().uri("posts/" + id).retrieve().bodyToMono(String.class);

	}

	@GetMapping("/find/{id}") // get json object
	public Mono<Posts> findPostsById(@PathVariable int id) {

		return webClient.get().uri("posts/" + id).retrieve().bodyToMono(Posts.class);

	}
}