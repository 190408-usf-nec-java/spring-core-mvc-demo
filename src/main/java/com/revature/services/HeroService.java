package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Hero;
import com.revature.repositories.HeroRepository;

@Service
public class HeroService {
	
	HeroRepository heroRepository;

	@Autowired // Setter injection
	public void setHeroRepository(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}
	
	public void saveHero(Hero hero) {
		this.heroRepository.saveHero(hero);
	}
	
	public Hero getHeroById(int id) {
		return this.heroRepository.getHeroById(id);
	}
	

}
