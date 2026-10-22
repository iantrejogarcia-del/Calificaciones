# Calificaciones

Aplicación Android para **registrar las calificaciones por semestre** y calcular automáticamente el promedio de cada uno y el **promedio general**. Incluye una sección de ayuda con preguntas frecuentes, términos y condiciones y enlaces a las redes del proyecto.

## Funcionalidades

- **Alta** de un semestre con seis calificaciones; el promedio del semestre se calcula solo (suma de las seis notas entre 6, como número entero).
- **Consulta** de un semestre para ver sus calificaciones y su promedio.
- **Modificación** de las calificaciones de un semestre ya registrado.
- **Baja** (eliminación) de un semestre.
- Lista de todos los registros en pantalla y **promedio general** actualizado después de cada operación.
- Sección de **ayuda**: preguntas frecuentes, términos y condiciones y enlaces a Instagram, Facebook y X del proyecto.

## Tecnologías

- Java
- Android SDK (AppCompat, ConstraintLayout, Material Components)
- SQLite (`SQLiteOpenHelper`), base de datos local `instituto`
- Gradle (Kotlin DSL)

## Requisitos

- Android Studio (versión reciente)
- Android SDK compatible con el proyecto (ver `app/build.gradle.kts`)

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone https://github.com/iantrejogarcia-del/Calificaciones.git
   ```
2. Abre el proyecto en Android Studio y espera a que termine la sincronización de Gradle.
3. Conecta un dispositivo Android o inicia un emulador.
4. Presiona **Run**.

## Cómo se usa

1. Escribe el número de semestre y las seis calificaciones.
2. Presiona el botón de alta para guardar; el promedio se calcula automáticamente.
3. Para consultar, modificar o eliminar, escribe el semestre y usa el botón correspondiente.
4. El botón de ayuda abre las preguntas frecuentes y los términos y condiciones.

## Estructura del proyecto

```
app/src/main/java/com/example/prfinal/
├── MainActivity.java      # Alta, consulta, modificación y baja de semestres
├── BD/BDHelper.java       # Base de datos SQLite y cálculo del promedio general
├── ayuda.java             # Pantalla de ayuda y enlaces a redes
├── Pfrecuentes.java       # Preguntas frecuentes
└── T_condicion.java       # Términos y condiciones
```

## Posibles mejoras

- Usar consultas parametrizadas en lugar de concatenar texto en las sentencias SQL.
- Validar que las calificaciones sean números en el rango permitido (hoy una casilla vacía puede cerrar la app).
- Mostrar el promedio con decimales en lugar de redondearlo hacia abajo.
- Mostrar un mensaje de confirmación antes de eliminar un semestre.

## Autor

**Ian Trejo García** — Estudiante de Ingeniería en Sistemas Computacionales
GitHub: [iantrejogarcia-del](https://github.com/iantrejogarcia-del)
