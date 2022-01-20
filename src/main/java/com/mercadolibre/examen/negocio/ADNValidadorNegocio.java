package com.mercadolibre.examen.negocio;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import com.mercadolibre.examen.model.Stats;
import com.mercadolibre.examen.model.dnaDTO;
import com.mercadolibre.examen.repositorio.ADNRepositorio;



@Service
public class ADNValidadorNegocio {
	private static DecimalFormat df = new DecimalFormat("#.##");
	@Autowired
	ADNRepositorio aDNRepositorio;
	public Boolean isMutant(dnaDTO objdna) {
		
		Boolean boolresp=false;
		int rest=0;
		char[][] matriz =convertirVector(objdna);
		rest+=buscarDiagonalDerechaI(objdna,matriz);
		rest+=buscarDiagonalizdaD(objdna,matriz);
		rest+=buscarFila(matriz);
		rest+=buscarColumna(matriz);
		if(rest>1)
		{
			
			boolresp=true;
		}
		else
			boolresp=false;
		
		aDNRepositorio.insert(com.mercadolibre.examen.model.HumanDna.builder()
				.dna(objdna.getDna())
				.isMutant(boolresp)
				.build());
		return boolresp;
		
	}
	public Stats getStats() {
		List<com.mercadolibre.examen.model.HumanDna> humanDnaList = aDNRepositorio.findAll();
		Long countHuman = humanDnaList.stream().filter(x -> !x.isMutant()).count();	
		Long countMutants = humanDnaList.stream().filter(x -> x.isMutant()).count();	
		int total = humanDnaList.size();

		return Stats.builder()
				.count_human_dna(countHuman)
				.count_mutant_dna(countMutants)
				.ratio(df.format(Float.valueOf(countMutants) / Float.valueOf(total)))
				.build();
	}
	private char[][] convertirVector(dnaDTO objdna) {
		int tamano = objdna.getDna().size();
		char[][] dna = new char[tamano][tamano];

		for (int i = 0; i < tamano; i++) {
			String dnaRow = objdna.getDna().get(i);
			dna[i] = dnaRow.toUpperCase().toCharArray();
		}
		return dna;
	}
	private int buscarDiagonalDerechaI(dnaDTO objdna,char[][] matriz)
	{
		int rest=0;
		int w=objdna.getDna().size();
		int h=objdna.getDna().size();
	    for( int k = 0 ; k < w * 2 ; k++ ) {
	    	String datos="";
	        for( int j = 0 ; j <= k ; j++ ) {
	            int i = k - j;
	            if( i < w && j < w ) {
	            	datos+= matriz[i][j];
	                //System.out.print( matriz[i][j] + " " );
	            }
	        }
	        if(datos.length()>3)
		    {
	        	  System.out.println(datos);
	        	  rest+=comparar(datos);
		    }
	       // System.out.println();
	    }
		return rest;
		
	}
	private int buscarDiagonalizdaD(dnaDTO objdna,char[][] matriz )
	{
		int w=objdna.getDna().size();
		int h=objdna.getDna().size();
		
		int rest=0;
		for (int i = 1 - w; i < h; i++){
			String datos="";
		    for (int x = -min(0, i), y = max(0, i); x < w && y < h; x++, y++)
		    {
		    	//System.out.println(y+""+x);
		    
		        //System.out.println(matriz[y][x]);
		    	datos+=matriz[y][x];
		    }
		    if(datos.length()>3)
		    {
		    System.out.println(datos);
		    rest+=comparar(datos);
		    }
	}
		return rest;
		
	}
	private int buscarFila(char[][] matriz)
	{
		int mutante=0;
		  //recorrer por fila
	     for (int i = 0; i < matriz.length; i++) {
	    	    
	    	    for (int j = 0; j < matriz[0].length-3; j++) {

	    	        if (matriz[i][j]==matriz[i][j+1] 
	    	            && matriz[i][j]==matriz[i][j+2]
	    	            && matriz[i][j]==matriz[i][j+3])
	    	            {
	    	        	mutante=mutante+1;
	    	            }
	    	    }
	    	}
	     return mutante;
	}
	private int comparar(String datos)
	{
		int cont=0;
		int mut=0;
		for(int i=0;i<datos.length();i++)
		{
			for(int j=0;j<datos.length();j++)
			{
				if(datos.charAt(i)==datos.charAt(j))
				{
					if(i!=0)
					cont++;
				}else
				{
					cont=0;
					break;
				}
				
					if(cont==4)
					{
						mut++;
						break;
					}else
					{
						mut=0;
					}
				}
			}
		
		return mut;
	}
	private int buscarColumna(char[][] matriz)
	{
		int mutante=0;
		//recorrer por column
	     for (int i = 0; i < matriz.length; i++) {
	    	    
	    	    for (int j = 0; j < matriz[0].length-3; j++) {

	    	        if (matriz[j][i]==matriz[j+1][i] 
	    	            && matriz[j][i]==matriz[j+2][i]
	    	            && matriz[j][i]==matriz[j+3][i])
	    	            {
	    	        	mutante=mutante+1;
	    	            }
	    	    }
	    	}
	     return mutante;
	}
}
