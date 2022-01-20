package com.mercadolibre.examen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
	private Long count_mutant_dna;
	private Long count_human_dna;
	private String  ratio;
}
