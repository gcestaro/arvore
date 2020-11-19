package com.github.gcestaro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.gcestaro.models.Arvore;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArvoreService {

	private List<ArvoreAddStrategy> strategies;

	public void criar(Arvore arvore) {
		strategies.stream()
				.filter(strategy -> strategy.isSatisfiedBy(arvore))
				.findFirst()
				.ifPresent(strategy -> strategy.add(arvore));
	}

}
