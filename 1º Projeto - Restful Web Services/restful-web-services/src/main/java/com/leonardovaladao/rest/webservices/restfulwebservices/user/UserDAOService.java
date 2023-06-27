package com.leonardovaladao.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> users = new ArrayList<User>();
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Leonardo", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Maria", LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount, "João", LocalDate.now().minusYears(10)));
	}

	public List<User> findAll() {
		return users;
	}
	
	public User saveUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public User findUserById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteUserById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		users.removeIf(predicate);
	}
}
