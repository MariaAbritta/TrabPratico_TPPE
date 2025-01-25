package app;

public class IRPF {

    public static final boolean TRIBUTAVEL = true;
    public static final boolean NAOTRIBUTAVEL = false;

    public static final float MINIMO_FAIXA_2 = 2259.20f;
    public static final float MINIMO_FAIXA_3 = 2826.65f;
    public static final float MINIMO_FAIXA_4 = 3751.05f;
    public static final float MINIMO_FAIXA_5 = 4664.68f;

    public static final float TAXA_FAIXA_2 = 0.075f;
    public static final float TAXA_FAIXA_3 = 0.15f;
    public static final float TAXA_FAIXA_4 = 0.225f;
    public static final float TAXA_FAIXA_5 = 0.275f;

    private String[] nomeRendimento;
    private boolean[] rendimentoTributavel;
    private float[] valorRendimento;
    private int numRendimentos;
    private float totalRendimentos;
    
    private String[] nomesDependentes;
    private String[] parentescosDependentes;
    private int numDependentes;
    
    private int numContribuicaoPrevidenciaria;
    private float totalContribuicaoPrevidenciaria;
    
    private float totalPensaoAlimenticia;
    
    private String[] nomesDeducoes;
    private float[] valoresDeducoes;
    
    public IRPF() {
        nomeRendimento = new String[0];
        rendimentoTributavel = new boolean[0];
        valorRendimento = new float[0];
        
        nomesDependentes = new String[0];
        parentescosDependentes = new String[0];
        numDependentes = 0;
        
        numContribuicaoPrevidenciaria = 0; 
        totalContribuicaoPrevidenciaria = 0f;
        
        totalPensaoAlimenticia = 0f;
        
        nomesDeducoes = new String[0];
        valoresDeducoes = new float[0];
    }

    public void criarRendimento(String nome, boolean tributavel, float valor) {
        String[] temp = new String[nomeRendimento.length + 1];
        for (int i=0; i<nomeRendimento.length; i++)
            temp[i] = nomeRendimento[i];
        temp[nomeRendimento.length] = nome;
        nomeRendimento = temp;

        boolean[] temp2 = new boolean[rendimentoTributavel.length + 1];
        for (int i=0; i<rendimentoTributavel.length; i++) 
            temp2[i] = rendimentoTributavel[i];
        temp2[rendimentoTributavel.length] = tributavel;
        rendimentoTributavel = temp2;
        
        float[] temp3 = new float[valorRendimento.length + 1];
        for (int i=0; i<valorRendimento.length; i++) {
            temp3[i] = valorRendimento[i];
        }
        temp3[valorRendimento.length] = valor; 
        valorRendimento = temp3;
        
        this.numRendimentos += 1;
        this.totalRendimentos += valor;
        
    }

    public int getNumRendimentos() {
        return numRendimentos;
    }

    public float getTotalRendimentos() {
        return totalRendimentos;
    }

    public float getTotalRendimentosTributaveis() {
        float totalRendimentosTributaveis = 0;
        for (int i=0; i<rendimentoTributavel.length; i++) {
            if (rendimentoTributavel[i]) {
                totalRendimentosTributaveis += valorRendimento[i];
            }
        }
        return totalRendimentosTributaveis;
    }

    public void cadastrarDependente(String nome, String parentesco) {
        String[] temp = new String[nomesDependentes.length + 1];
        for (int i=0; i<nomesDependentes.length; i++) {
            temp[i] = nomesDependentes[i];
        }
        temp[nomesDependentes.length] = nome;
        nomesDependentes = temp;
        
        String[] temp2 = new String[parentescosDependentes.length + 1];
        for (int i=0; i<parentescosDependentes.length; i++) {
            temp2[i] = parentescosDependentes[i];
        }
        temp2[parentescosDependentes.length] = parentesco;
        parentescosDependentes = temp2;
        
        numDependentes++;
    }

    public int getNumDependentes() {
        return numDependentes;
    }

    public float getDeducao() {
        float total = 0; 
        for (String d: nomesDependentes) {
            total += 189.59f;
        }
        total += totalContribuicaoPrevidenciaria;
        
        return total;
    }

    public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
        numContribuicaoPrevidenciaria++;
        totalContribuicaoPrevidenciaria += contribuicao;
    }

    public int getNumContribuicoesPrevidenciarias() {
        return numContribuicaoPrevidenciaria;
    }

    public float getTotalContribuicoesPrevidenciarias() {
        return totalContribuicaoPrevidenciaria;
    }

    public String getDependente(String nome) {
        for (String d : nomesDependentes) {
            if (d.contains(nome))
                return d;
        }
        return null;
    }

    public String getParentesco(String dependente) {
        for (int i = 0; i<nomesDependentes.length; i++) {
            if (nomesDependentes[i].equalsIgnoreCase(dependente))
                return parentescosDependentes[i];
        }
        return null;
    }

    public void cadastrarPensaoAlimenticia(String dependente, float valor) {
        String parentesco = getParentesco(dependente); 
        if (parentesco.toLowerCase().contains("filh") || 
            parentesco.toLowerCase().contains("alimentand")) {
            totalPensaoAlimenticia += valor;
        }
    }

    public float getTotalPensaoAlimenticia() {
        return totalPensaoAlimenticia;
    }

    public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
        nomesDeducoes = adicionarElemento(nomesDeducoes, nome);
        valoresDeducoes = adicionarElemento(valoresDeducoes, valorDeducao);
    }

    private String[] adicionarElemento(String[] array, String elemento) {
        String[] novoArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            novoArray[i] = array[i];
        }
        novoArray[array.length] = elemento;
        return novoArray;
    }

    private float[] adicionarElemento(float[] array, float elemento) {
        float[] novoArray = new float[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            novoArray[i] = array[i];
        }
        novoArray[array.length] = elemento;
        return novoArray;
    }

    public String getOutrasDeducoes(String nome) {
        for (String d : nomesDeducoes) {
            if (d.toLowerCase().contains(nome.toLowerCase()))
                return d;
        }
        return null;
    }

    public float getDeducao(String nome) {
        for (int i=0; i<nomesDeducoes.length; i++) {
            if (nomesDeducoes[i].toLowerCase().contains(nome.toLowerCase()))
                return valoresDeducoes[i];
        }
        return 0;
    }

    public float getTotalOutrasDeducoes() {
        float soma = 0;
        for (float f : valoresDeducoes) {
            soma += f;
        }
        return soma;
    }

    public float getBaseCalculoImposto() {
        float totalRendimentosTributaveis = getTotalRendimentosTributaveis();
        float totalDeducoes = getDeducao() + getTotalOutrasDeducoes();
        return totalRendimentosTributaveis - totalDeducoes;
    }

    public float getImpostoFaixa1() {
        return 0.0f;
    }

    public float getImpostoFaixa2() {
        float baseCalculoImposto = getBaseCalculoImposto();
        if (baseCalculoImposto < MINIMO_FAIXA_2) {
            return 0.0f;
        }
        if (baseCalculoImposto > MINIMO_FAIXA_3) {
            baseCalculoImposto = MINIMO_FAIXA_3;
        }
        return (baseCalculoImposto - MINIMO_FAIXA_2) * TAXA_FAIXA_2;
    }

    // Substituição do método por objeto-método
    public float getImpostoFaixa3() {
        return new ImpostoFaixa3(this).calcular();
    }

    public float getImpostoFaixa4() {
        float baseCalculoImposto = getBaseCalculoImposto();
        if (baseCalculoImposto < MINIMO_FAIXA_4) {
            return 0.0f;
        }
        if (baseCalculoImposto > MINIMO_FAIXA_5) {
            baseCalculoImposto = MINIMO_FAIXA_5;
        }
        return (baseCalculoImposto - MINIMO_FAIXA_4) * TAXA_FAIXA_4;
    }

    public float getImpostoFaixa5() {
        float baseCalculoImposto = getBaseCalculoImposto();
        if (baseCalculoImposto < MINIMO_FAIXA_5) {
            return 0.0f;
        }
        return (baseCalculoImposto - MINIMO_FAIXA_5) * TAXA_FAIXA_5;
    }

    public float getImpostoTotal() {
        float impostoTotal = getImpostoFaixa1();
        impostoTotal += getImpostoFaixa2();
        impostoTotal += getImpostoFaixa3();
        impostoTotal += getImpostoFaixa4();
        impostoTotal += getImpostoFaixa5();
        return impostoTotal;
    }

    public float getAliquotaEfetiva() {
        float totalRendimentosTributaveis = getTotalRendimentosTributaveis();
        if (totalRendimentosTributaveis == 0) return 0;
        return getImpostoTotal() / getTotalRendimentosTributaveis();
    }
}