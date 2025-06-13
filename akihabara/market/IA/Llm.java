package IA;

import java.io.FileInputStream;
import java.util.Properties;
import java.net.http.*;
import java.net.URI;

public class Llm {
    private String apiKey;

    public Llm() {
        try {
            Properties props = new Properties();
            FileInputStream input = new FileInputStream("config.properties");
            props.load(input);
            apiKey = props.getProperty("OPENROUTER_API_KEY");
        } catch (Exception e) {
            System.out.println("No se pudo cargar la API Key: " + e.getMessage());
        }
    }

    public String sugerirNombreProducto(String tipo, String franquicia) {
        try {
            String body = """
                {
                    "model": "mistral:latest",
                    "messages": [
                        { "role": "user", "content": \" + prompt + "\" }"
                    ]
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openrouter.ai/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String respuesta = response.body();
            int inicio = respuesta.indexOf("\"content\":\"");
            if (inicio == -1) return "No se pudo generar un nombre.";
            String texto = respuesta.substring(inicio + 10);
            return texto.split("\"")[0].replace("\r\n", "").trim();

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error real en consola
            return "Error al conectar con la IA.";
        }
    }
}
