package chenaurj.budgetManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chenaurj.budgetManager.model.User;
import chenaurj.budgetManager.service.UserService;
import chenaurj.budgetManager.util.ServiceError;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/generate", method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@RequestMapping(value = "/authenticate/{username}/{password}", method = RequestMethod.POST)
	public @ResponseBody User getUser(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
		return userService.getUser(username, password);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ServiceError> handleEmpty(RuntimeException ex) {
		ServiceError error = new ServiceError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ServiceError> handleDuplicates(RuntimeException ex) {
		ServiceError error = new ServiceError(HttpStatus.BAD_REQUEST.value(), "This username already exists");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/* Didnt end up using these ones, perhaps in the future
	@RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(@PathVariable(value = "id") String id) {
		userService.deleteUser(id);
		return null;
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable(value = "id") String id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public @ResponseBody User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	*/
}
