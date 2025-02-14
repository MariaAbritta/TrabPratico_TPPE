package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TesteAliquotaEfetiva.class,
    TesteCadastrarDependente.class,
    TesteRendimentos.class,
    TesteCalculosDeducoesDependentes.class,
    TesteCadastroContribuicaoPrevidenciaria.class,
    TesteCadastroPensaoAlimenticia.class,
    TesteCadastroOutrasDeducoes.class,
    TesteBaseCalculoImposto.class,
    TesteImpostoPago.class
})
public class AllTests {
}