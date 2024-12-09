// Objetivo: Desenvolvemos um teste que confirme a determinação da base de cálculo do imposto, que corresponde à diferença entre os ganhos tributáveis e as deduções (pensão alimentícia, contribuições para a previdência, deduções por dependentes, etc.).

package tst;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.IRPF;

public class TesteBaseCalculoImposto {

    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testeBaseCalculoImposto() {
      // Criamos um rendimento tributável de R$5000 e adicionamos deduções
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000f);
        irpf.cadastrarContribuicaoPrevidenciaria(1000f); // Dedução de 1000
        irpf.cadastrarDeducaoIntegral("Funpresp", 500f); // Dedução adicional de 500

        // Em seguida, validamos se a base de cálculo do imposto foi calculada corretamente, que é a diferença entre o rendimento e as deduções.
        // Esperamos que a base de cálculo seja 5000 - 1000 - 500 = 3500
        assertEquals(3500f, irpf.getBaseCalculoImposto(), 0f);
    }
}