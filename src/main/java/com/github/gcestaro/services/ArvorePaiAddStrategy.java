package com.github.gcestaro.services;

import org.springframework.stereotype.Service;

import com.github.gcestaro.models.Arvore;
import com.github.gcestaro.repositories.ArvoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArvorePaiAddStrategy implements ArvoreAddStrategy {

	private final ArvoreRepository repository;

	@Override
	public void add(Arvore arvore) {
		repository.save(arvore);
	}

	@Override
	public boolean isSatisfiedBy(Arvore arvore) {
		return arvore != null && arvore.getPai() == null;
	}
}
