package com.mercadolibre.examen.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.examen.model.ADN;
import com.mercadolibre.examen.model.HumanDna;

public interface ADNRepositorio extends MongoRepository<HumanDna, Integer> {

	
}
