package com.leonardovaladao.microservicos.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardovaladao.microservicos.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from, String to);
}
