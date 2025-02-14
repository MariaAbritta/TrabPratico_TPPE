package tst;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import app.IRPF;

@RunWith(Parameterized.class)
public class TesteBaseCalculoImposto {

    private IRPF irpf;
    private float rendimento;
    private float contribuicaoPrevidenciaria;
    private float deducaoIntegral;
    private float expectedBaseCalculo;

    public TesteBaseCalculoImposto(float rendimento, float contribuicaoPrevidenciaria, float deducaoIntegral, float expectedBaseCalculo) {
        this.rendimento = rendimento;
        this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
        this.deducaoIntegral = deducaoIntegral;
        this.expectedBaseCalculo = expectedBaseCalculo;
    }

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 5000f, 1000f, 500f, 3500f }, // Caso 1
            { 10000f, 3000f, 2000f, 5000f }, // Caso 2
            { 7000f, 1500f, 800f, 4700f }, // Caso 3
            { 4500f, 1200f, 300f, 3000f }, // Caso 4
            { 3000f, 1000f, 1000f, 1000f }  // Caso 5
        });
    }

    @Test
    public void testeBaseCalculoImpostoParametrizado() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, rendimento);
        irpf.cadastrarContribuicaoPrevidenciaria(contribuicaoPrevidenciaria);
        irpf.cadastrarDeducaoIntegral("Funpresp", deducaoIntegral);

        assertEquals(expectedBaseCalculo, irpf.getBaseCalculoImposto(), 0.01f);
    }
}