package conversoMoneda;

import org.json.JSONObject;
//import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Conversor {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        int i;

        System.out.println("********** Conversor de Monedas **********");
        System.out.println("1) Dólar => Peso Argentino");
        System.out.println("2) Peso Argentino => Dólar");
        System.out.println("3) Dólar => Real Brasileño");
        System.out.println("4) Real Brasileño => Dólar");
        System.out.println("5) Dólar => Peso Colombiano");
        System.out.println("6) Peso Colombiano => Dólar");
        System.out.println("7) Salir");
        System.out.println("*******************************************");

        System.out.print("Elija una opción del menú válida: ");
        i = lectura.nextInt();

        if (i >= 1 && i <= 6) {
            System.out.print("Ingrese el valor de moneda que desea convertir según su opción elegida: ");
            double valor = lectura.nextDouble();
            double resultado = 0;

            switch (i) {
                case 1:
                    resultado = convertir(valor, "USD", "ARS");
                    System.out.println(valor + " USD son " + resultado + " ARS");
                    break;
                case 2:
                    resultado = convertir(valor, "ARS", "USD");
                    System.out.println(valor + " ARS son " + resultado + " USD");
                    break;
                case 3:
                    resultado = convertir(valor, "USD", "BRL");
                    System.out.println(valor + " USD son " + resultado + " BRL");
                    break;
                case 4:
                    resultado = convertir(valor, "BRL", "USD");
                    System.out.println(valor + " BRL son " + resultado + " USD");
                    break;
                case 5:
                    resultado = convertir(valor, "USD", "COP");
                    System.out.println(valor + " USD son " + resultado + " COP");
                    break;
                case 6:
                    resultado = convertir(valor, "COP", "USD");
                    System.out.println(valor + " COP son " + resultado + " USD");
                    break;
            }
        } else {
            System.out.println("¡Hasta luego!");
        }

        lectura.close();
    }

    public static JSONObject obtenerTasasDesde(String monedaBase) {
        try {
            String apiKey = "codigo api personal";
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaBase;
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            return new JSONObject(response.toString());

        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio.");
            e.printStackTrace();
            return null;
        }
    }

    public static double convertir(double valor, String monedaBase, String monedaDestino) {
        JSONObject json = obtenerTasasDesde(monedaBase);

        if (json != null && json.getString("result").equals("success")) {
            JSONObject tasas = json.getJSONObject("conversion_rates");
            double tasa = tasas.getDouble(monedaDestino);
            return valor * tasa;
        } else {
            System.out.println("Error al convertir la moneda.");
            return 0;
        }
    }
}
