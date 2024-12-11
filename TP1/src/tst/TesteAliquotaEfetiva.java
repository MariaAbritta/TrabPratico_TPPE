package tst;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.IRPF;

public class TesteAliquotaEfetiva {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testeAliquotaEfetiva() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 10000f);
        irpf.cadastrarContribuicaoPrevidenciaria(3000.59f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 189f);

        assertEquals(0.0976, irpf.getAliquotaEfetiva(), 0.0001f);
    }

}
