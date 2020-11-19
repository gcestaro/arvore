package com.github.gcestaro.services;

import com.github.gcestaro.models.Arvore;

public interface ArvoreAddStrategy {

	boolean isSatisfiedBy(Arvore arvore);

	void add(Arvore arvore);
}
