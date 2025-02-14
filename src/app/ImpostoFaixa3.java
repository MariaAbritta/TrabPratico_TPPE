package app;

public class ImpostoFaixa3 {
    private IRPF irpf;

    public ImpostoFaixa3(IRPF irpf) {
        this.irpf = irpf;
    }

    public float calcular() {
        float baseCalculoImposto = irpf.getBaseCalculoImposto();
        if (baseCalculoImposto < IRPF.MINIMO_FAIXA_3) {
            return 0.0f;
        }
        if (baseCalculoImposto > IRPF.MINIMO_FAIXA_4) {
            baseCalculoImposto = IRPF.MINIMO_FAIXA_4;
        }
        return (baseCalculoImposto - IRPF.MINIMO_FAIXA_3) * IRPF.TAXA_FAIXA_3;
    }
}