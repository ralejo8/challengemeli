package com.mercadolibre.examen.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ADN")
public class ADN {
    @Id
    private String id;
    

}
