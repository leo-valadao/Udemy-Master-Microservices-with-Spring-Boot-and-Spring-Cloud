package com.leonardovaladao.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Leonardo Valadão");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2("Leonardo", "Valadão");
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonByParameter() {
		return new PersonV1("Pessoa Versão 1");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonByParameter() {
		return new PersonV2("Pessoa", "Versão 2");
	}
	
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonByHeader() {
		return new PersonV1("Pessoa Versão 1");
	}
	
	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonByHeader() {
		return new PersonV2("Pessoa", "Versão 2");
	}
	
	@GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonByAcceptHeader() {
		return new PersonV1("Pessoa Versão 1");
	}
	
	@GetMapping(path = "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonByAcceptHeader() {
		return new PersonV2("Pessoa", "Versão 2");
	}
}
