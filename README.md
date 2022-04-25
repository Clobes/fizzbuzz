# FizzBuzz - Guia de Usuario

Este proyecto consiste en una API REST la cual devuelve en formato JSON una lista con las características propias de cada número incluido en el rango consultado.
Si el número es multiplo de 3, debe imprimir __Fizz__, a su vez si es multiplo de 5 debera imprimir __Buzz__, y si cumple que es multiplo de 3 y de 5 dede de imprimir __FizzBuzz__.


- [FizzBuzz  Guia de Usuario](#fizzbuzz-user-guide)
  - [Puesta en Marcha](#primer-paso)
  - [Versiones Utilizadas](#versiones-utilizadas)
  - [Conexiones del Proyecto](#conexiones-del-proyecto)
  - [Estructura del Proyecto](#project-structure)
  - [Consideraciones del Proyecto](#consideraciones-del-proyecto)


## Puesta en Marcha

> __Note__
>
> Para que el proyecto pueda levantar es necesario tener un motor de Base de Datos MySQL instalado y un schema creado con el nombre 'fizzbuzz'...
> En el caso de querer usar la dockerización es necesario tener instalado Docker Desktop en Windows o Mac, en Linux es necesario tener docker-compose ...
> 
>
>
>__En el caso de elegir Docker:__
>
>   1) Situarce en la raiz del proyecto FizzBuzz y ejecutar: `docker-compose up -d`																							
>   2) Ejecutar la aplicación desde el Ide.
>   
---

>

## Versiones Utilizadas

| Name               | Versión       
| ------------------ | ------------- | 
| __Java__           | __1.8.0_271__ | 
| __Spring Boot__    | __2.6.7__     | 
| __MySQL__          | __8.0.28__    | 
| __Apache Maven__   | __3.6.3__     |
| __Docker Desktop__ | __4.4.4__     | 


## Conexiones del Proyecto

|              |                                                         |                                                                                                                      |
| ------------ | ------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| __URL__      | <http://localhost:8080>                                 | El puerto puede ser cambiado                                                                                         |
| __URL__      | <http://localhost:8080/fizzbuzz/{min}/{max}>            | Lista todos los números del rango con sus caracteristicas                                                            |
| __URL__      | <http://localhost:8080/fizzbuzz/list>                   | Lista todas las operaciones consultadas                                                                              |
| __URL__      | <http://localhost:8080/fizzbuzz/{id_operacion}}>        | Lista operación consultada                                                                                           |


---

## Estructura del Proyecto

| Carpeta                                                                              | Descripción                                                                                    |
| -----------------------------------------------------------------------------------  | ---------------------------------------------------------------------------------------------- |
| `fizzbuzz/config/`                                                                   | Contiene las configuraciones del proyecto                                                      |
| `intraway/config/`                                                                   | Contiene las configuraciones de los Beans                                                      |
| `intraway/controler/`                                                                | Contiene los endpoints expuestos                                                               |
| `intraway/converter/`                                                                | Contiene los Converters de DTO a ENTITY                                                        |
| `intraway/dto/`                                                                      | Contiene los DTO's del proyecto                                                                |
| `intraway/entity/`                                                                   | Contiene las entidades a persistir del proyecto                                                |
| `intraway/exceptions/`                                                               | Contiene los excepciones customizadas                                                          |
| `intraway/handler/`                                                                  | Contiene logica de negocio                                                                     |
| `intraway/service/`                                                                  | Contiene logica de negocio                                                                     |
| `intraway/repository/`                                                               | Contiene acceso a los datos                                                                    |
| `intraway/util/`                                                                     | Contiene clases utiles comun para todo el proyecto                                             |


## Consideraciones del Proyecto

> __Note__
>
> Se asumio en crear códigos de respuestas para las operaciones realizadas, considerando las sugeridas en el documento.
>

| Código             | Descripción       
| ------------------ | ------------- | 
| __001__            | Se encontraron números que cumplen ser multiplos de 3, 5 y ambos | 
| __002__            | Se encontraron multiplos de 3                                    | 
| __003__            | Se encontraron multiplos de 5                                    | 
| __004__            | Se encontraron números que cumplen ser multiplos de 3, 5         |
| __1000__           | No se encontraron multiplos de 3 ni de 5                         | 

