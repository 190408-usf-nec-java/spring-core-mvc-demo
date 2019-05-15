package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.Hero;
import com.revature.services.HeroService;

@RestController // Allows controller to assume all methods have @ResponseBody
@RequestMapping("/hello")
@CrossOrigin
public class HelloWorldController {

	private HeroService heroService;
	
	@Autowired // Constructor injection
	public HelloWorldController(HeroService heroService) {
		super();
		this.heroService = heroService;
	}

	@GetMapping("")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping("hero")
	public Hero getHeroParam(@RequestParam int id) {
		return this.heroService.getHeroById(id);
	}
	
	@GetMapping(path = "hero/{id}", produces = "application/json")
	public Hero getHero(@PathVariable int id) {
		return this.heroService.getHeroById(id);
	}
	
	
	@GetMapping(path = "hero/{id}", produces = "text/html")
	public String getHeroHtml(@PathVariable int id) {
		Hero hero = this.heroService.getHeroById(id);
		String html = "<!DOCTYPE><html><head><title>Hero</title></head><body><h1>"
					+ hero.getName() + "</h1><p>Health: " + hero.getHealth() + "</p></body></html>";
		return html;
	}
	
	@GetMapping("/{name}")
	public String customSayHello(@PathVariable String name) {
		return "Hello world " + name + "!";
	}
	
	@PostMapping("hero")
	@ResponseStatus(HttpStatus.CREATED)
	public Hero hero(@RequestBody Hero hero) {
//		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		this.heroService.saveHero(hero);
		return hero;
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> exceptionHandler(HttpClientErrorException e) {
		return ResponseEntity
				.status(e.getStatusCode())
				.body(e.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler() {
		return "We don't know how to use null :(";
	}
	
}
