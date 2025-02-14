package app;

public class Imposto {
    public static final float[] FAIXAS = {2259.20f, 2826.65f, 3751.05f, 4664.68f};
    public static final float[] ALIQUOTAS = {0.0f, 0.075f, 0.15f, 0.225f, 0.275f};

    public float calcularImposto(float baseCalculo) {
        float imposto = 0;
        float[] limites = {FAIXAS[0], FAIXAS[1], FAIXAS[2], FAIXAS[3], Float.MAX_VALUE};

        for (int i = 1; i < limites.length; i++) {
            if (baseCalculo > limites[i - 1]) {
                float faixa = Math.min(baseCalculo, limites[i]) - limites[i - 1];
                imposto += faixa * ALIQUOTAS[i];
            }
        }
        return imposto;
    }
}