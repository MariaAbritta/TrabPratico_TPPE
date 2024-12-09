import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TesteCadastrarDependente.class,
    TesteRendimentos.class,
    TesteCalculosDeducoesDependentes.class,
    TesteCadastroContribuicaoPrevidenciaria.class,
    TesteCadastroPensaoAlimenticia.class,
    TesteCadastroOutrasDeducoes.class,
    TesteBaseCalculoImposto.class 
})
public class AllTests {
}