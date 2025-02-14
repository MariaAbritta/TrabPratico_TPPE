package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteAliquotaEfetiva {

    // totalRendimentosTributaveis = 0

    IRPF irpf;
    
    float rendimento, contribuicaoPrevidenciaria;
    float valoresDeducoes;
    String nomesDeducoes;
	float aliquotaEsperada;

	
	public TesteAliquotaEfetiva(float rendimento, float contribuicaoPrevidenciaria, String nomesDeducoes, float valoresDeducoes, float aliquotaEsperada) {
		this.rendimento = rendimento;
		this.contribuicaoPrevidenciaria = contribuicaoPrevidenciaria;
		this.nomesDeducoes = nomesDeducoes;
		this.valoresDeducoes = valoresDeducoes;
		this.aliquotaEsperada = aliquotaEsperada;
	}

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Parameters
	public static List<Object[]> getParameters() {
		
		Object t1[] = new Object[] {10000f,3000.59f,"Funpresp",189f,0.0976f};
		Object t2[] = new Object[] {5000f,1000f,"Previdência Privada",500f,0.0287f};
		Object t3[] = new Object[] {3456.98f,234.98f,"Carne-Leão: Livro Caixa",234.76f,0.0192f};
		Object t4[] = new Object[] {0f,0f,"",0f,0f};
		
		Object parametros[][] = new Object[][] {t1,t2,t3,t4};
		
		return Arrays.asList(parametros);
	}
    
    @Test
    public void testeAliquotaEfetiva() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, rendimento);     
        irpf.cadastrarContribuicaoPrevidenciaria(contribuicaoPrevidenciaria);
        irpf.cadastrarDeducaoIntegral(nomesDeducoes, valoresDeducoes);   
 
        assertEquals(aliquotaEsperada, irpf.getAliquotaEfetiva(), 0.0001f);
    }

}
