#language: es
@regresion @publicarEntrada @wip
Característica: Publicar entradas

	Escenario: Usuario publica una entrada suya en Draft
		Dado que el usuario "userx@gmail.com" creo la entrada "Entrada nueva" en Draft 
		Cuando hace una publicacion
		Entonces se muestra el mensaje "Entry Entrada nueva has been saved."
		Y la entrada se suma a la lista de entradas del blog.