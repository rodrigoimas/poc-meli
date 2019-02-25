# poc-meli
Ejercicio: # VULCANOS

En una galaxia lejana, existen tres civilizaciones. Vulcanos Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta.

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

## Instalación
Como librería

```shell
go get github.com/eduacostam/vulcanos
```

O si se quiere hacer uso del programa de línea de comandos
```shell
go get github.com/eduacostam/vulcanos/cmd/vulcan
```

## Uso
Para usar el programa de línea de comandos, una vez instalado, ejecutar el binario `vulcan`.

Para usar el programa en modo servidor web, hace falta ejecutarlo por lo menos 1 vez con los parámetros `-init` y `-seed` los cuales crean las tablas correspondientes en la base de datos y ejecutan el proceso de carga de datos en las mismas.

El servidor web hace uso de PostgreSQL y requiere las siguientes variables de entorno (puede usarse un archivo `.env`):
```
PORT=3000

DB_USERNAME=postgres
DB_PASSWORD=secret
DB_NAME=vulcanos
DB_PORT=5432
```


# VULCANOS

En una galaxia lejana, existen tres civilizaciones. Vulcanos Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta.

## Suposiciones
- Este programa comienza en el día 0. En este día todos los planetas están situados en un ángulo de 0 grados respecto del eje X.
- Un año en este sistema solar es de 360 días (el tiempo que tarda el planeta más lento en hacer un giro completo al sol).
- Un "período" es cualquier periodo de tiempo que mantenga el mismo clima durante 1 o más días.
- Los climas "Lluvia" y "Lluvia Fuerte" se consideran están en un mismo período.

## Clima
- Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el sistema solar experimenta un período de sequía
- Cuando los tres planetas no están alineados, forman entre sí un triángulo.
- En el momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en su máximo.
- Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.

## Instalación
Como librería

```shell
go get github.com/eduacostam/vulcanos
```

O si se quiere hacer uso del programa de línea de comandos
```shell
go get github.com/eduacostam/vulcanos/cmd/vulcan
```

## Uso
Para usar el programa de línea de comandos, una vez instalado, ejecutar el binario `vulcan`.

Para usar el programa en modo servidor web, hace falta ejecutarlo por lo menos 1 vez con los parámetros `-init` y `-seed` los cuales crean las tablas correspondientes en la base de datos y ejecutan el proceso de carga de datos en las mismas.

El servidor web hace uso de PostgreSQL y requiere las siguientes variables de entorno (puede usarse un archivo `.env`):
```
PORT=3000

DB_USERNAME=postgres
DB_PASSWORD=secret
DB_NAME=vulcanos
DB_PORT=5432
```