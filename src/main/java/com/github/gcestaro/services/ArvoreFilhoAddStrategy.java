package com.github.gcestaro.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	private final MongoTemplate mongoTemplate;

	@Override
	public void add(Arvore arvore) {

		Optional<Arvore> raiz = buscarRaiz(arvore);

		raiz.ifPresent(r -> {
			log.info("raiz - {}", r);

			addItem(arvore, r);

			log.info("raiz alterada - {}", r);

			repository.save(r);
		});
	}

	private Optional<Arvore> buscarRaiz(Arvore arvore) {
		Query query = new Query();

		StringBuilder fieldBuilder = new StringBuilder("itens");

		for (int i = 1; i < arvore.getLevel() - 2; i++) {
			fieldBuilder.append(".itens");
		}

		fieldBuilder.append("._id");

		query.addCriteria(Criteria.where(fieldBuilder.toString()).is(arvore.getPai()));

		return Optional.ofNullable(mongoTemplate.findOne(query, Arvore.class));
	}

	private void addItem(Arvore item, Arvore raiz) {
		List<Arvore> iterador = descerAteNivelPai(item, raiz);

		log.info("iterador {}", iterador);

		iterador.stream()
				.filter(a -> a.getId() == item.getPai())
				.findFirst()
				.ifPresent(a -> a.addItem(item));
	}

	private List<Arvore> descerAteNivelPai(Arvore item, Arvore raiz) {
		List<Arvore> iterador = new ArrayList<>();
		iterador.addAll(raiz.getItens());

		for (int i = 1; i < item.getLevel() - 2; i++) {
			iterador = iterador.stream()
					.map(Arvore::getItens)
					.filter(Objects::nonNull)
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
		}
		return iterador;
	}

	@Override
	public boolean isSatisfiedBy(Arvore arvore) {
		return arvore != null && arvore.getPai() != null && arvore.getLevel() > 1;
	}
}
