import modulo_calculadoras.CalculadoraSalaRetangular;
import modulo_calculadoras.ConversorMoeda;

public class Calculadoras {

    public static void main(String[] args) {
        ConversorMoeda convDolarReal = new ConversorMoeda();
        convDolarReal.converterDolarParaReal(5);

        CalculadoraSalaRetangular calRetangulo =
                new CalculadoraSalaRetangular(5, 6);
        double area = calRetangulo.calcularArea();
        System.out.println("Area %.2f".formatted(area));
        double perimeto = calRetangulo.calcularPerimetro();
        System.out.println("Perímetro %.2f".formatted(perimeto));
    }
}
