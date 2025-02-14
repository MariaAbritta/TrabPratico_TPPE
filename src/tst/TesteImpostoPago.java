package tst;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.IRPF;

public class TesteImpostoPago {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testeImpostoFaixa1() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(0.0f, irpf.getImpostoFaixa1(), 0.01f);
    }

    @Test
    public void testeImpostoFaixa2() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(42.56f, irpf.getImpostoFaixa2(), 0.01f);
    }

    @Test
    public void testeImpostoFaixa3() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(138.66f, irpf.getImpostoFaixa3(), 0.01f);
    }

    @Test
    public void testeImpostoFaixa4() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(205.56f, irpf.getImpostoFaixa4(), 0.01f);
    }

    @Test
    public void testeImpostoFaixa5() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(590.07f, irpf.getImpostoFaixa5(), 0.01f);
    }

    @Test
    public void testeImpostoTotal() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(976.86f, irpf.getImpostoTotal(), 0.01f);
    }
}
