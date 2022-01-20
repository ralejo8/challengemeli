package com.mercadolibre.examen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mercadolibre.examen.model.dnaDTO;
import com.mercadolibre.examen.negocio.ADNValidadorNegocio;
import com.mercadolibre.examen.repositorio.ADNRepositorio;

@RunWith(MockitoJUnitRunner.class)
public class ExamenApplicationTests {
	String[] dnaNotMutantTest = { "ATGCGT", "TAGTTC", "TTGCGG", "AGTAGG", "CACCTA", "TCACTG" };

	@Mock
	ADNRepositorio aDNRepositorio;
	@InjectMocks
	ADNValidadorNegocio aDNValidadorNegocio;
	
	@Test
	public void isMutant() {
		List<String> arrADN = Arrays.asList( "ATGCGT", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
		dnaDTO requestDto = dnaDTO.builder()
				.dna(arrADN).build();
		when(aDNRepositorio.insert(any(com.mercadolibre.examen.model.HumanDna.class))).thenReturn(com.mercadolibre.examen.model.HumanDna.builder().build());

		// execute
		boolean result = aDNValidadorNegocio.isMutant(requestDto);
		verify(aDNRepositorio).insert(any(com.mercadolibre.examen.model.HumanDna.class));

		assertTrue(result);

	}
	@Test
	public void isNotMutant() {
		List<String> arrADN = Arrays.asList( "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG");
		dnaDTO requestDto = dnaDTO.builder()
				.dna(arrADN).build();
		when(aDNRepositorio.insert(any(com.mercadolibre.examen.model.HumanDna.class))).thenReturn(com.mercadolibre.examen.model.HumanDna.builder().build());

		// execute
		boolean result = aDNValidadorNegocio.isMutant(requestDto);
		verify(aDNRepositorio).insert(any(com.mercadolibre.examen.model.HumanDna.class));

		assertFalse(result);
	}
	

}
