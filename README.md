
# TranslateApp

TranslateApp es una aplicación Android que te permite traducir texto de un idioma a otro de manera rápida y sencilla. Esta aplicación utiliza Jetpack Compose y Kotlin para ofrecer una experiencia de usuario intuitiva y eficiente en la traducción de texto.

## Funcionalidades

- Traducción de texto: Ingresa el texto que deseas traducir y selecciona el idioma de origen y destino.
- Idiomas admitidos: La aplicación admite múltiples idiomas, incluyendo inglés, español, italiano y francés.
- Almacenamiento de preferencias: La selección de idioma se guarda en DataStore para mejorar la experiencia del usuario.

## Arquitectura

La aplicación sigue una arquitectura de componentes MVVM (Model-View-ViewModel) para separar la lógica de presentación de la lógica de negocio. Aquí hay una breve descripción de los principales componentes:

- `LanguageStore`: Esta clase maneja el almacenamiento de la selección de idioma utilizando DataStore. Permite guardar y recuperar la configuración del idioma seleccionado por el usuario.

- `LanguagesView`: Esta vista muestra un menú desplegable que permite al usuario seleccionar el idioma de origen y destino. Utiliza Jetpack Compose para crear una interfaz de usuario interactiva.

- `TranslateViewModel`: El ViewModel se encarga de la lógica de negocio de la traducción. Utiliza la biblioteca ML Kit de Google para realizar la traducción. Además, gestiona la descarga del modelo de traducción según sea necesario.

- `TranslateView`: Esta vista muestra los campos de entrada y salida de texto, así como los menús desplegables para seleccionar el idioma de origen y destino. Los resultados de la traducción se muestran en tiempo real.

<img src="https://github.com/jamirou/Personal_Schedule/assets/48457084/ec033cd9-3120-475f-b005-1f9201e2df72" height="600">

