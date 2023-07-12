package com.leonardovaladao.microservicos.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leonardovaladao.microservicos.currencyexchangeservice.model.CurrencyExchange;
//import com.leonardovaladao.microservicos.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	//@Autowired
	//private CurrencyExchangeRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		//CurrencyExchange currency = repository.findByFromAndTo(from, to);
		CurrencyExchange currency = new CurrencyExchange(1001L, from, to, BigDecimal.valueOf(45L) , environment.getProperty("local.server.port"));  
		
		/*if (currency == null) {
			throw new RuntimeException("Unable to Find data for "+from+" to "+to);
		}*/
		
		//currency.setEnvironment(environment.getProperty("local.server.port"));
		
		return currency;
	}
}
