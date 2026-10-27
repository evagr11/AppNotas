<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A713F12%2C50%3AEAB308%2C100%3AFEF9C3&height=200&section=header&text=App+Notas&fontSize=60&fontColor=fff&fontAlignY=38&desc=App+Android+de+notas+de+colores+en+Java&descAlignY=62&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/AppNotas" target="_blank"><img src="https://img.shields.io/badge/Ver_Código-713F12?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código"></a>
</div>

<div align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com/?font=JetBrains+Mono&size=18&duration=3500&pause=800&color=CA8A04&center=true&vCenter=true&width=600&lines=Android+nativo+con+Java%3BBienvenida+por+pasos+con+Fragments%3BNotas+de+colores+con+filtro+por+Chips%3BGuardado+en+el+almacenamiento+interno" alt="Typing SVG" /></a>
</div>

# 🚀 Sobre el Proyecto

**App Notas** es una aplicación Android nativa para tomar notas rápidas y organizarlas por colores. La primera vez que se abre, una bienvenida por pasos pide el nombre, una contraseña y la fecha de nacimiento; a partir de ahí la app saluda por tu nombre y te lleva directamente a tus notas, que puedes crear, editar y filtrar por color.

Todo se guarda en el **almacenamiento interno** del móvil, sin internet ni base de datos: cada nota es un pequeño fichero de texto.

✨ **Características principales:**

*   👋 **Bienvenida por pasos:** Tres `Fragments` (nombre, contraseña y fecha de nacimiento) que comparten los datos a través de un mismo objeto y validan que ningún campo quede vacío. Solo aparece la primera vez gracias a `SharedPreferences`.
*   📝 **Crear y editar notas:** Título, descripción de varias líneas y un color a elegir. Al pulsar una nota de la lista se abre con sus datos para editarla.
*   🎨 **7 colores y filtro por Chips:** Cada nota tiene su color de fondo y la lista se puede filtrar marcando uno o varios colores a la vez.
*   💾 **Persistencia en ficheros:** Cada nota se guarda como `notaN.not` y los datos del usuario como `datos.cas` dentro de la carpeta privada de la app (`getFilesDir()`), así que sobreviven al cerrar la aplicación.
*   🔐 **Datos del usuario ocultos:** El nombre, la contraseña y la fecha se guardan aplicando un desplazamiento de caracteres (cada letra pasa a la siguiente), para que no queden legibles a simple vista en el fichero.
*   🌙 **Modo oscuro:** Tema propio para el modo noche.

### 🎨 Colores de las notas

<div align="center">

| Chip | Color |
| :--: | :---: |
| 1 | <img src="https://img.shields.io/badge/-Blanco-FFFFFF?style=for-the-badge" alt="Blanco #FFFFFF"/> |
| 2 | <img src="https://img.shields.io/badge/-Rojo-E53935?style=for-the-badge" alt="Rojo #E53935"/> |
| 3 | <img src="https://img.shields.io/badge/-Morado-8E24AA?style=for-the-badge" alt="Morado #8E24AA"/> |
| 4 | <img src="https://img.shields.io/badge/-Índigo-3949AB?style=for-the-badge" alt="Índigo #3949AB"/> |
| 5 | <img src="https://img.shields.io/badge/-Azul-1E88E5?style=for-the-badge" alt="Azul #1E88E5"/> |
| 6 | <img src="https://img.shields.io/badge/-Verde-43A047?style=for-the-badge" alt="Verde #43A047"/> |
| 7 | <img src="https://img.shields.io/badge/-Amarillo-FDD835?style=for-the-badge" alt="Amarillo #FDD835"/> |

</div>

# 🧠 Contexto del Proyecto

Este proyecto se desarrolló durante el segundo curso del ciclo formativo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**, en las asignaturas de **Desarrollo de Interfaces** y **Programación Multimedia y Dispositivos Móviles**. El objetivo era practicar el desarrollo nativo en Android con Java: varias pantallas con `Activities` y `Fragments`, listas con adaptadores propios, componentes de Material Design y guardado de datos en ficheros.

# 🛠️ Stack Tecnológico

<div align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=materialdesign&logoColor=white" alt="Material Design">
  <img src="https://img.shields.io/badge/Status-Estable-CA8A04?style=for-the-badge" alt="Status Estable">
</div>

<br>

<div align="center">

| Componente | Tecnología |
| :--------- | :--------- |
| **Plataforma** | `Android Nativo` (minSdk 24 · targetSdk 36) |
| **Lenguaje** | `Java 11` |
| **Interfaz** | `XML Layouts`, `Activities`, `Fragments`, `ListView` con `ArrayAdapter` propio, `Chips` de Material |
| **Persistencia** | Ficheros en el almacenamiento interno (`FileWriter` / `BufferedReader`) y `SharedPreferences` |
| **Build** | `Gradle` (Kotlin DSL) |

</div>

# 📂 Estructura del Proyecto

El código está separado en modelo, vista y una capa intermedia con la lógica, siguiendo una organización parecida a MVVM.

```bash
AppNotas/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── java/com/example/appnotas/
│       │   ├── Model/
│       │   │   ├── Nota.java              # Título, descripción, color e id
│       │   │   ├── ColorNota.java         # Colores disponibles
│       │   │   └── Usuario.java           # Nombre, contraseña y fecha de nacimiento
│       │   ├── ViewModel/
│       │   │   ├── LandingViewModel.java  # Guarda y lee el usuario
│       │   │   └── NotaManager.java       # Crea y actualiza los ficheros de las notas
│       │   └── View/
│       │       ├── Landing/               # Bienvenida: Activity y 4 Fragments
│       │       ├── GeneralActivity.java   # Lista de notas y filtro por color
│       │       ├── CrearNotasActivity.java# Crear y editar una nota
│       │       └── NotaAdapter.java       # Pinta cada nota con su color
│       └── res/
│           ├── layout/                    # Pantallas y fila de la lista
│           ├── drawable/                  # Fondos redondeados de cada color
│           ├── values/                    # Colores, textos y tema
│           └── values-night/              # Tema oscuro
├── build.gradle.kts
└── gradlew
```

# 🚀 Cómo Utilizar el Proyecto

Para ejecutarlo necesitas **Android Studio** y el **JDK 11** o superior.

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/evagr11/AppNotas.git
    ```
2.  **Abre el proyecto en Android Studio:** `File` > `Open` y selecciona la carpeta `AppNotas`.
3.  **Sincroniza Gradle** y espera a que se descarguen las dependencias.
4.  **Ejecuta la aplicación** en un emulador o en un móvil con el botón `Run 'app'`.

La primera vez verás la bienvenida; para volver a verla, borra los datos de la app desde los ajustes del móvil.

# 🎯 Mejoras futuras

- 🗑️ Borrar notas (por ejemplo, deslizando sobre ellas).
- 🔐 Guardar la contraseña con un hash real (como SHA-256 con sal) en lugar del desplazamiento de caracteres.
- 🗄️ Pasar el guardado de ficheros a **Room** y la lista a `RecyclerView`.
- 🔄 Usar el `ViewModel` de Android Jetpack para que los datos sobrevivan al girar la pantalla.

<br>

<div align="center">
  <p>Creado por <strong>Eva Gallardo</strong></p>
  <a href="https://github.com/evagr11" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
  <a href="https://www.linkedin.com/in/eva-gallardo-romero-830362349/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</div>

<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A713F12%2C50%3AEAB308%2C100%3AFEF9C3&height=120&section=footer" width="100%"/>
</div>
