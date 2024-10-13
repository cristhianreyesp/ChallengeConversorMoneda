package modelo;

public record ResultadoConversion(String base_code,
                                  String target_code,
                                  double conversion_rate) {
    public double resultado() {
        return conversion_rate;
    }
}
