import api.ApiConsulta;
import errores.ApiErrores;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    //https://app.exchangerate-api.com/dashboard
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ApiConsulta apiConsulta = new ApiConsulta();

        String[][] monedas = {
                {"USD", "Dólar Estadounidense"},
                {"EUR", "Euro"},
                {"ARS", "Peso Argentino"},
                {"VES", "Bolívar Venezolano"},
                {"PEN", "Sol Peruano"},
                {"CLP", "Peso Chileno"},
                {"MXN", "Peso Mexicano"},
                {"BOB", "Peso Boliviano"},
                {"COP", "Peso Colombiano"},
                {"BRL", "Real Brasileño"},
                {"PYG", "Guaraní"},
                {"UYU", "Peso Uruguayo"},
                {"CNY", "Yuanes"},
                {"RUB", "Rublo"}
        };

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Ver lista de monedas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese la moneda base (ej. USD, EUR): ");
                        String monedaBase = scanner.next().toUpperCase();

                        System.out.print("Ingrese la moneda de destino (ej. USD, EUR): ");
                        String monedaCambio = scanner.next().toUpperCase();

                        System.out.print("Ingrese la cantidad a convertir: ");
                        double cantidad = scanner.nextDouble();

                        double tipoCambio = apiConsulta.obtenerCambio(monedaBase, monedaCambio);

                        double resultado = cantidad * tipoCambio;
                        System.out.printf("El resultado es: %.2f %s = %.2f %s%n", cantidad, monedaBase, resultado, monedaCambio);

                    } catch (ApiErrores | IOException | InterruptedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:

                    System.out.println("\nLista de monedas disponibles:");
                    for (String[] moneda : monedas) {
                        System.out.printf("%s - %s (%s)%n", moneda[0], moneda[1], moneda[2]);
                    }
                    break;

                case 3:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}