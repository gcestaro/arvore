package com.github.gcestaro.services;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gcestaro.models.Arvore;
import com.github.gcestaro.repositories.ArvoreRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ArvoreServiceTest {

	@Autowired
	private ArvoreService service;

	@Autowired
	private ArvoreRepository repository;

	@BeforeEach
	public void setUp() {
		repository.deleteAll();

		Arvore d = new Arvore();
		d.setId(1234);
		d.setLevel(4);
		d.setPai(1233);

		Arvore c = new Arvore();
		c.setId(1233);
		c.setItens(List.of(d));
		c.setLevel(3);
		c.setPai(1232);

		Arvore b = new Arvore();
		b.setId(1232);
		b.setItens(List.of(c));
		b.setLevel(2);
		b.setPai(1231);

		Arvore a = new Arvore();
		a.setId(1231);
		a.setItens(List.of(b));
		a.setLevel(1);

		Arvore y = new Arvore();
		y.setId(1221);
		y.setLevel(2);
		y.setPai(1321);

		Arvore z = new Arvore();
		z.setId(1321);
		z.setLevel(1);
		z.setItens(List.of(y));

		Arvore x = new Arvore();
		x.setId(2311);
		x.setLevel(1);

		service.criar(a);
		service.criar(x);
		service.criar(z);

//		repository.saveAll(List.of(a, x, z));
	}

	@AfterEach
	public void tearDown() {
		log.info("items: {}", repository.findAll());
	}

	@Test
	void test() {
		Arvore arvore = new Arvore();
		arvore.setId(1236);
		arvore.setLevel(5);
		arvore.setPai(1234);

		service.criar(arvore);
	}

}
