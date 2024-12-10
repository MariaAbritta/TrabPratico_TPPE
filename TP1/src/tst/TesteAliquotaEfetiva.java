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
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000f);
        irpf.cadastrarContribuicaoPrevidenciaria(1000f);
        irpf.cadastrarDeducaoIntegral("Funpresp", 500f); 

        irpf.setImpostoPago(500f);

        assertEquals(14.29f, irpf.getAliquotaEfetiva(), 0.01f);
    }
}
