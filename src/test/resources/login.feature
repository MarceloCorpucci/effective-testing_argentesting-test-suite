#language: es
Característica: Login

	Escenario: Usuario registrado realiza login con éxito
		Dado el usuario "Juan" con email "userx@gmail.com" y password "userx"
		Cuando se loguea en el blog
		Entonces obtiene el mensaje "Successfully logged in as userx@gmail.com." en pantalla