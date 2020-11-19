package com.github.gcestaro.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gcestaro.models.Arvore;
import com.github.gcestaro.repositories.ArvoreRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ArvoreFilhoAddStrategy implements ArvoreAddStrategy {

	private final ArvoreRepository repository;

	@Override
	public void add(Arvore arvore) {

		List<Arvore> arvores = repository.findAll();

		log.info("arvores - {}", arvores);

		List<Arvore> iterador = new ArrayList<>();
		iterador.addAll(arvores);

		addItem(arvore, iterador);

		log.info("arvores - {}", arvores);

		salvarRaiz(arvore, arvores);
	}

	private void salvarRaiz(Arvore arvore, List<Arvore> arvores) {

		
		
		repository.saveAll(arvores);
	}

	private void addItem(Arvore arvore, List<Arvore> iterador) {
		for (int i = 1; i < arvore.getLevel() - 1; i++) {
			iterador = iterador.stream()
					.map(Arvore::getItens)
					.filter(Objects::nonNull)
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
		}

		log.info("iterador {}", iterador);

		iterador.stream()
				.filter(a -> a.getId() == arvore.getPai())
				.findFirst()
				.ifPresent(a -> a.addItem(arvore));
	}

	@Override
	public boolean isSatisfiedBy(Arvore arvore) {
		return arvore != null && arvore.getPai() != null && arvore.getLevel() > 1;
	}
}
