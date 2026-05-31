# ESPE-Tech Inventory

## Informe

El proyecto implementa un modulo de inventario para equipos tecnologicos de laboratorio. La solucion fue construida con Spring Boot y organizada por capas: persistencia, negocio, web y servicio AI. El sistema carga 10000 registros de hardware, filtra los equipos activos comprados en los ultimos cinco anos, agrupa los resultados por categoria y calcula valor total, precio promedio y equipo mas caro.

El desarrollo tambien se separo visualmente en carpetas por fase dentro de `Fases`, para observar la evolucion del proyecto desde la base inicial hasta la version final con interfaz web.

## Comparacion de enfoques

La logica principal se encuentra en:

`src/main/java/ec/edu/espe/tech/inventory/service/HardwareInventoryService.java`

### Paradigma Imperativo

El metodo `generarReporteImperativo` usa estructuras tradicionales como `for`, `if`, mapas acumuladores y actualizacion manual de valores.

En terminos de lineas de codigo, este enfoque ocupa aproximadamente 44 lineas. Es mas extenso porque necesita declarar acumuladores, validar valores nulos, sumar manualmente, contar registros y comparar el equipo mas caro paso a paso.

Su ventaja principal es que muestra claramente cada operacion realizada por el algoritmo. Sin embargo, al crecer la logica, puede volverse mas dificil de leer porque mezcla filtrado, agrupacion, conteo, suma y seleccion del maximo en un mismo bloque.

### Paradigma Funcional/Declarativo

El metodo `generarReporteFuncional` usa `Streams`, `Optional`, `Collectors`, `filter`, `map`, `reduce` y `max`.

En lineas de codigo, este enfoque ocupa aproximadamente 28 lineas. Es mas corto porque delega varias operaciones repetitivas a la API de Streams, especialmente el filtrado, agrupacion y calculos sobre colecciones.

Su ventaja principal es la legibilidad cuando se entiende la sintaxis funcional de Java. El codigo expresa mejor la intencion de cada paso: filtrar, agrupar, transformar y ordenar. Tambien facilita cambios futuros, porque cada operacion esta separada como parte de una cadena declarativa.

## Legibilidad y mantenimiento

El enfoque imperativo es util para explicar el algoritmo desde cero, ya que cada instruccion se ejecuta de forma explicita. Para estudiantes o revisiones detalladas, permite ver como se construyen los resultados internamente.

El enfoque funcional/declarativo resulta mas compacto y mantenible para este caso, porque reduce codigo repetitivo y separa mejor las transformaciones de datos. Si se requiere agregar otro filtro o cambiar el ordenamiento, se puede hacer de forma mas directa dentro del flujo de Streams.

En conclusion, ambos enfoques resuelven el mismo problema. El imperativo prioriza el control detallado del proceso, mientras que el funcional/declarativo prioriza claridad, menor cantidad de codigo y facilidad de mantenimiento.

## Ejecucion del proyecto final

Desde la raiz del proyecto:

```bash
mvn spring-boot:run
```

Abrir:

```text
http://localhost:8080/
```

Endpoints principales:

```text
GET /api/inventory/imperative
GET /api/inventory/functional
GET /api/health
```
