#language: es
@regresion @buscarEntrada
Característica: Buscar entradas

	Escenario: Usuario busca una entrada por tag
		Dado que el usuario "userx@gmail.com" publico la entrada "Nuevo tutorial" con tag "tutoriales" 
		Cuando un usuario selecciona el tag "tutoriales" de la lista de tags del blog
		Entonces se muestra la entrada "Nuevo tutorial" en la lista de entradas publicadas.