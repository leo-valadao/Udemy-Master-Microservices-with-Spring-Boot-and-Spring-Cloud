package com.leonardovaladao.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello-world")
public class HelloWorldController {
	
	private MessageSource messages;
	
	public HelloWorldController(MessageSource messages) {
		this.messages = messages;
	}

	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Hello " + name);
	}
	
	@GetMapping(path = "/i18n")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		
		return this.messages.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
