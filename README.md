# challengemeli
Create Determinar si un humano es mutante
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.
proyecto construido en el leguaje de programación java, esta con puesto de un servicio 
post que analiza una secuencia ADN de un humano, valida si es un humano o no. Recibe 
como parametro un json con el siguiente formato { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
el servio responde un 200 y true si el ADN analizado es mutante en caso contrario retorna un 403
url:  Post : http://3.230.72.2:1426/challenge/mutant/

tambien contiene otro servicio para validar el estado de los datos analizados. este retorna un json 
de la siguiente forma {
    "count_mutant_dna": 1,
    "count_human_dna": 0,
    "ratio": "1"
}
la url para consumir el servicio es la siguiente
GET: http://3.230.72.2:1426/challenge/stats/

para ver la documentación del proyecto puede ingresar al siguiente url
http://3.230.72.2:1426/challenge/swagger-ui.html
