package com.revature.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.revature.models.Hero;

@Repository
public class HeroRepository {
	Map<Integer, Hero> heroMap = new HashMap<>();
	int counter = 1;
	
	public void saveHero(Hero hero) {
		hero.setId(counter++);
		heroMap.put(hero.getId(), hero);
	}
	
	public Hero getHeroById(int id) {
		return heroMap.get(id);
	}
}
