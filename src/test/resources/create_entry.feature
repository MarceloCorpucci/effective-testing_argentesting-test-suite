#language: es
@regresion @crearEntrada
Característica: Crear entradas

	Antecedentes: Datos para crear una entrada en el blog
		Dado el usuario "Juan" con email "userx@gmail.com" y password "userx"
		Cuando Crea la entrada "Entrada nueva" con el texto "Esta es una entrada de prueba" 
	
	Escenario: Usuario crea una entrada
		Entonces la entrada queda publicada en la lista de entradas del blog.
		
	Escenario: Usuario crea una entrada en modo draft
		Cuando le agrega el tag "Prueba"
		Y la asigna la entrada como Draft
		Entonces la entrada no es visible por otros usuarios