Conversor de Monedas (Java)

Pequeña aplicación de consola para convertir monedas usando la API pública de ExchangeRate-API.

Autora: Camila Mikan
Contacto: camilitamikan@hotmail.com

✨ Funcionalidades

Menú interactivo en terminal.

Conversiones soportadas:

USD ⇄ ARS (Peso argentino)

USD ⇄ BRL (Real brasileño)

USD ⇄ COP (Peso colombiano)

Consumo de API REST y parseo de JSON con org.json.

📦 Estructura
conversoMoneda/
└── Conversor.java


Paquete declarado: conversoMoneda
Clase principal: conversoMoneda.Conversor

🔧 Requisitos

JDK 17+ (recomendado)

Conexión a Internet

Dependencia org.json

Maven
<dependency>
  <groupId>org.json</groupId>
  <artifactId>json</artifactId>
  <version>20240303</version>
</dependency>

Gradle
implementation 'org.json:json:20240303'


Si no usas build tool, descarga el JAR de org.json y colócalo en lib/.

🔐 Configurar la API Key

El código usa ExchangeRate-API (v6). Regístrate y obtén tu API Key.

Recomendado: guarda la API Key en una variable de entorno y léela en el código.

Define la variable:

Windows (PowerShell)

$env:EXCHANGE_API_KEY="TU_API_KEY"


macOS / Linux (bash/zsh)

export EXCHANGE_API_KEY="TU_API_KEY"


Cambia en Conversor.java:

// Antes:
// String apiKey = "codigo api personal";

// Después:
String apiKey = System.getenv("EXCHANGE_API_KEY");
if (apiKey == null || apiKey.isBlank()) {
    throw new IllegalStateException("Falta definir la variable de entorno EXCHANGE_API_KEY");
}

▶️ Ejecución
Con Maven
mvn -q exec:java -Dexec.mainClass=conversoMoneda.Conversor

Sin Maven (con JAR de org.json)

Windows

javac -cp .;lib\json.jar conversoMoneda\Conversor.java
java  -cp .;lib\json.jar conversoMoneda.Conversor


macOS / Linux

javac -cp .:lib/json.jar conversoMoneda/Conversor.java
java  -cp .:lib/json.jar conversoMoneda.Conversor

🧭 Uso

Ejecuta el programa.

Elige una opción del menú (1–6).

Ingresa el valor a convertir.

Mira el resultado en la consola.

Opción 7 cierra la aplicación.

🗒️ Notas y límites

La API puede tener límites de uso (rate limits). Si excedes el límite, recibirás errores.

La app muestra errores por consola si la API falla o si no hay Internet.

Las tasas se consultan “al momento”; no se cachean localmente.

🚀 Mejoras futuras

Soportar más monedas y/o que el usuario las elija libremente.

Manejo de errores más amigable y mensajes localizados.

Tests automatizados y refactor a un diseño con servicios.

Soporte para Gradle/Maven wrapper y empaquetado jar.

📄 Licencia

Proyecto con fines educativos. Puedes adaptarlo libremente.
Si deseas una licencia formal, se sugiere MIT.

👩‍💻 Autora

Camila Mikan – camilitamikan@hotmail.com

¿Sugerencias o issues? ¡Bienvenidas!
