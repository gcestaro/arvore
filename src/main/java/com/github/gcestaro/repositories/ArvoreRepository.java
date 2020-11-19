package com.github.gcestaro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.gcestaro.models.Arvore;

public interface ArvoreRepository extends MongoRepository<Arvore, Integer> {

}
