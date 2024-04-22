package modulo_calculadoras;

import interfaces.CalculoGeometrico;

public class CalculadoraSalaRetangular implements CalculoGeometrico {
    double largura;
    double altura;
    public CalculadoraSalaRetangular(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return this.altura * this.largura;
    }

    @Override
    public double calcularPerimetro() {
        return (2*this.altura) + (2 * this.largura);
    }
}
