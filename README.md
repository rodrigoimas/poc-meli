# poc-meli
Ejercicio: En una galaxia lejana, existen tres civilizaciones. Vulcanos Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta.

## Suposiciones
- En el día 0, los tres planetas se encuentran alineados a 90 grados..
- Los 10 años que requiere el ejercicio se suponen de duración de 360 días, lo que corresponde con la duración de un año (1 giro de órbita) del planeta con menor velocidad angular (1 grado/día), Ferengi.
- Para la determinación de la posición de los planetas, se estima una precisión de 1 (cero) decimales.
- Para la determinación del clima óptimo, se utiliza el cálculo de la pendiente entre los planetas y el sol ((y2-y1)/(x2-x1)) con una precisión de 1 (un) decimal con el fin de que haya al menos un resultado. De otra manera, debería haberse considerado el volúmen de cada planeta, ya que es prácticamente imposible que se alíneen los centros gravitacionales de cada planeta por fuera de los ejes x e y.
- Los climas "Lluvia" y "Lluvia Fuerte" se consideran están en un mismo período.

## Condiciones
- Cuando los tres planetas están alineados entre sí y con el sol, el sistema solar experimenta un período de sequía.
- Cuando los tres planetas están alineados entre sí, aunque no están alineados con respecto al sol, el sistema solar experimenta un clima óptimo de presión y temperatura.
- Cuando los tres planetas no están alineados, forman entre sí un triángulo. Si el triángulo contiene al sol, el sistema solar experimenta un período de lluvia. El pico de intensidad de la lluvia se da cuando el perímetro llega a su máximo.
- Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.

## Stack
- Lenguaje: Java 8.
- Framework: Apache Spark.
- ORM: Hibernate 5.
- Db: Postgres 10.

## Hosting
- App web: Heroku app, processType web
- Postgres Db: Heroku Addon --> heroku-postgresql
- Cron: Heroku Addon --> Scheduler

## Uso
Para usar el programa de línea de comandos, una vez instalado, se ejecuta como cualquier jar (java -jar <filename>.jar). Queda levantado un servlet que responde en el puerto 5432 (puerto por defecto de Spark).

A continuación, se detallan las urls válidas de la aplicación web.

### Cálculo del pronóstico
Se invoca la siguiente url para calcular el pronóstico donde se debe reemplazar <cantidad_dias> por la cantidad de dias de pronostico que se desea obtener.

URL local: http://localhost:4567/pronostico?dias=<cantidad_dias>
URL pública: https://solar-meli-web.herokuapp.com/pronostico?dias=<cantidad_dias>

### Búsqueda de clima para un día
Se invoca la siguiente url para consultar el clima en un día en particular, donde se debe reemplazar <dia> por el número de día del que se desea conocer el clima. Si el pronóstico no se calculo para el día consultado, devuelve un error (http code 400 Bad Request) con el detalle de este error.

URL local: http://localhost:4567/clima?dia=<dia>
URL pública: https://solar-meli-web.herokuapp.com/clima?dia=<dia>

##Persistencia
Cada vez que se calcula el pronóstico, se persisten los resultados en la base de datos. Si se ejecuta el pronóstico más de una vez, se eliminan los resultados anteriores de la base de datos y quedan los resultados del último pronóstico calculado.

El servidor web hace uso de PostgreSQL, por lo que tiene configuradas las siguientes propiedades en el profile por defecto del pom.xml:
```
db.url=jdbc:postgresql://localhost:5432/postgres
db.username=postgres
db.password=meli20190225
```

Se incluye el script de la creación del modelo de dominio en /main/resource/dbscript.

##Job

Para ejecutar el job que pronostica los próximos 10 años, existe un Heroku Scheduler que se ejecuta diariamente a las 03.00 UTC con una invocación a la misma url que invoca para generar el pronóstico con el parámetro <cantidad_dias> seteado en 3600 (360 días x 10 años).

##Otros detalles

Tanto para la obtención del string de conexión a Postgres on Heroku como del puerto http del servidor web de Heroku, se hizo uso de variables de entorno disponibilizadas por Heroku.