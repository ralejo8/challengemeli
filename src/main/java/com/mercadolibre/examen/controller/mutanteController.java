package com.mercadolibre.examen.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.examen.model.Stats;
import com.mercadolibre.examen.model.dnaDTO;
import com.mercadolibre.examen.negocio.ADNValidadorNegocio;



@Controller
@RequestMapping("/")
public class mutanteController {
	@Autowired
	private ADNValidadorNegocio aDNValidadorNegocio;
	@PostMapping("/mutant")
	public ResponseEntity<Boolean> isMutant(@RequestBody  dnaDTO objdna) {
		
		
		Boolean boolresp=aDNValidadorNegocio.isMutant(objdna);
		if(boolresp==true)
			return new ResponseEntity<Boolean>(boolresp,HttpStatus.OK);
		else
			return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		

	
	}
	@GetMapping("/stats")
	public ResponseEntity<Stats> getStats() {
		return new ResponseEntity<>(aDNValidadorNegocio.getStats(), HttpStatus.OK);
	}
}
