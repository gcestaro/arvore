package com.github.gcestaro.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Document("arvores")
@EqualsAndHashCode(exclude = { "itens" })
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Arvore {

	@Id
	private int id;

	private Integer pai;

	private int level;

	@Default
	private List<Arvore> itens = new ArrayList();

	public List<Arvore> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public void addItem(Arvore arvore) {
		itens.add(arvore);
	}
}
