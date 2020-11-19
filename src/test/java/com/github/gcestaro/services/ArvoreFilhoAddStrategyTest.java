package com.github.gcestaro.services;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gcestaro.models.Arvore;
import com.github.gcestaro.repositories.ArvoreRepository;

@SpringBootTest
class ArvoreFilhoAddStrategyTest {

	@InjectMocks
	private ArvoreFilhoAddStrategy strategy;

	@Mock
	private ArvoreRepository repository;

	@Test
	@DisplayName("Testando inserção nivel 4")
	void testLevel4() {

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

		List<Arvore> arvores = List.of(a);

		when(repository.findAll()).thenReturn(arvores);

		Arvore f = new Arvore();
		f.setId(1236);
		f.setLevel(5);
		f.setPai(1234);

		strategy.add(f);

		d.setItens(List.of(f));
	}

}
