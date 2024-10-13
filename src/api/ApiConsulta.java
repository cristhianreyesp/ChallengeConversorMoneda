package api;

import com.google.gson.Gson;
import errores.ApiErrores;
import modelo.ResultadoConversion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsulta {
    private final String apikey = "7c2786fb12697f02dd9ed893";
    private final String apiurl = "https://v6.exchangerate-api.com/v6/";

    public double obtenerCambio(String monedaBase,String monedaCambio) throws ApiErrores, IOException, InterruptedException {
        try {
            String url = apiurl+apikey+"/pair/"+monedaBase+"/"+monedaCambio;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ApiErrores("Error en la solicitud: " + response.statusCode());
            }

            Gson gson = new Gson();
            ResultadoConversion resultado = gson.fromJson(response.body(), ResultadoConversion.class);

            return resultado.conversion_rate();
        } catch (IOException | InterruptedException e) {
            throw new ApiErrores("Error al intentar conectar con la API: " + e.getMessage());
        }
    }

}
