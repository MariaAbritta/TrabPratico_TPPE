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
	
	/**
	 * Cadastra um rendimento na base do contribuinte, informando o nome do 
	 * rendimento, seu valor e se ele é tributável ou não. 
	 * @param nome nome do rendimento a ser cadastrado
	 * @param tributavel true caso seja tributável, false caso contrário
	 * @param valor valor do rendimento a ser cadastrado
	 */
	public void criarRendimento(String nome, boolean tributavel, float valor) {
		//Adicionar o nome do novo rendimento
		String[] temp = new String[nomeRendimento.length + 1];
		for (int i=0; i<nomeRendimento.length; i++)
			temp[i] = nomeRendimento[i];
		temp[nomeRendimento.length] = nome;
		nomeRendimento = temp;

		//adicionar tributavel ou nao no vetor 
		boolean[] temp2 = new boolean[rendimentoTributavel.length + 1];
		for (int i=0; i<rendimentoTributavel.length; i++) 
			temp2[i] = rendimentoTributavel[i];
		temp2[rendimentoTributavel.length] = tributavel;
		rendimentoTributavel = temp2;
		
		//adicionar valor rendimento ao vetor
		float[] temp3 = new float[valorRendimento.length + 1];
		for (int i=0; i<valorRendimento.length; i++) {
			temp3[i] = valorRendimento[i];
		}
		temp3[valorRendimento.length] = valor; 
		valorRendimento = temp3;
		
		this.numRendimentos += 1;
		this.totalRendimentos += valor;
		
	}

	/**
	 * Retorna o número de rendimentos já cadastrados para o contribuinte
	 * @return numero de rendimentos
	 */
	public int getNumRendimentos() {
		return numRendimentos;
	}

	/**
	 * Retorna o valor total de rendimentos cadastrados para o contribuinte
	 * @return valor total dos rendimentos
	 */
	public float getTotalRendimentos() {
		return totalRendimentos;
	}

	/**
	 * Retorna o valor total de rendimentos tributáveis do contribuinte
	 * @return valor total dos rendimentos tributáveis
	 */
	public float getTotalRendimentosTributaveis() {
		float totalRendimentosTributaveis = 0;
		for (int i=0; i<rendimentoTributavel.length; i++) {
			if (rendimentoTributavel[i]) {
				totalRendimentosTributaveis += valorRendimento[i];
			}
		}
		return totalRendimentosTributaveis;
	}

	/**
	 * Método para realizar o cadastro de um dependente, informando seu grau 
	 * de parentesco
	 * @param nome Nome do dependente
	 * @param parentesco Grau de parentesco
	 */
	public void cadastrarDependente(String nome, String parentesco) {
		// adicionar dependente 
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

	/**
	 * Método que retorna o numero de dependentes do contribuinte
	 * @return numero de dependentes
	 */
	public int getNumDependentes() {
		return numDependentes;
	}
	
	/**
	 * Return o valor do total de deduções para o contribuinte
	 * @return valor total de deducoes
	 */
	public float getDeducao() {
		float total = 0; 
		for (String d: nomesDependentes) {
			total += 189.59f;
		}
		total += totalContribuicaoPrevidenciaria;
		
		return total;
	}

	/**
	 * Cadastra um valor de contribuição previdenciária oficial
	 * @param contribuicao valor da contribuição previdenciária oficial
	 */
	public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
		numContribuicaoPrevidenciaria++;
		totalContribuicaoPrevidenciaria += contribuicao;
	}

	/**
	 * Retorna o numero total de contribuições realizadas como contribuicao 
	 * previdenciaria oficial
	 * @return numero de contribuições realizadas
	 */
	public int getNumContribuicoesPrevidenciarias() {
		return numContribuicaoPrevidenciaria;
	}

	/**
	 * Retorna o valor total de contribuições oficiais realizadas
	 * @return valor total de contribuições oficiais
	 */
	public float getTotalContribuicoesPrevidenciarias() {
		return totalContribuicaoPrevidenciaria;
	}

	/**
	 * Realiza busca do dependente no cadastro do contribuinte
	 * @param nome nome do dependente que está sendo pesquisado
	 * @return nome do dependente ou null, caso nao conste na lista de dependentes
	 */
	public String getDependente(String nome) {
		for (String d : nomesDependentes) {
			if (d.contains(nome))
				return d;
		}
		return null;
	}

	/**
	 * Método que retorna o grau de parentesco para um dado dependente, caso ele
	 * conste na lista de dependentes
	 * @param dependente nome do dependente
	 * @return grau de parentesco, nulo caso nao exista o dependente
	 */
	public String getParentesco(String dependente) {
		for (int i = 0; i<nomesDependentes.length; i++) {
			if (nomesDependentes[i].equalsIgnoreCase(dependente))
				return parentescosDependentes[i];
		}
		return null;
	}

	/**
	 * Realiza o cadastro de uma pensao alimenticia para um dos dependentes do 
	 * contribuinte, caso ele seja um filho ou alimentando. 
	 * @param dependente nome do dependente 
	 * @param valor valor da pensao alimenticia
	 */
	public void cadastrarPensaoAlimenticia(String dependente, float valor) {
		String parentesco = getParentesco(dependente); 
		if (parentesco.toLowerCase().contains("filh") || 
			parentesco.toLowerCase().contains("alimentand")) {
			totalPensaoAlimenticia += valor;
		}
	}

	/**
	 * Retorna o valor total pago em pensões alimentícias pelo contribuinte.
	 * @return valor total de pensoes alimenticias
	 */
	public float getTotalPensaoAlimenticia() {
		return totalPensaoAlimenticia;
	}

	
	/**
	 * Metodo para cadastrar deduções integrais para o contribuinte. Para cada
	 * dedução é informado seu nome e valor. 
	 * @param nome nome da deducao 
	 * @param valorDeducao valor da deducao
	 */
	public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
	    nomesDeducoes = adicionarElemento(nomesDeducoes, nome);
	    valoresDeducoes = adicionarElemento(valoresDeducoes, valorDeducao);
	}

	/**
	 * Metodo para cadastrar nome da Dedução
	 * @param array array com o nome das deduções
	 * @param elemento nome a ser adicionado
	 */
	private String[] adicionarElemento(String[] array, String elemento) {
	    String[] novoArray = new String[array.length + 1];
	    for (int i = 0; i < array.length; i++) {
	        novoArray[i] = array[i];
	    }
	    novoArray[array.length] = elemento;
	    return novoArray;
	}

	/**
	 * Metodo para cadastrar valores de dedução
	 * @param array array com os valores das deduções
	 * @param elemento valor a ser adicionado
	 */
	private float[] adicionarElemento(float[] array, float elemento) {
	    float[] novoArray = new float[array.length + 1];
	    for (int i = 0; i < array.length; i++) {
	        novoArray[i] = array[i];
	    }
	    novoArray[array.length] = elemento;
	    return novoArray;
	}

	
	/**
	 * Método para pesquisar uma deducao pelo seu nome. 
	 * @param substring do nome da deducao a ser pesquisada
	 * @return nome da deducao, ou null caso na esteja cadastrada
	 */
	public String getOutrasDeducoes(String nome) {
		for (String d : nomesDeducoes) {
			if (d.toLowerCase().contains(nome.toLowerCase()))
				return d;
		}
		return null;
	}

	/**
	 * Obtem o valor da deducao à partir de seu nome 
	 * @param nome nome da deducao para a qual se busca seu valor
	 * @return valor da deducao
	 */
	public float getDeducao(String nome) {
		for (int i=0; i<nomesDeducoes.length; i++) {
			if (nomesDeducoes[i].toLowerCase().contains(nome.toLowerCase()))
				return valoresDeducoes[i];
		}
		return 0;
	}

	/**
	 * Obtem o valor total de todas as deduções que nao sao do tipo
	 * contribuicoes previdenciarias ou por dependentes
	 * @return valor total das outras deducoes
	 */
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

	public float getImpostoFaixa3() {
		float baseCalculoImposto = getBaseCalculoImposto();
		if (baseCalculoImposto < MINIMO_FAIXA_3) {
			return 0.0f;
		}
		if (baseCalculoImposto > MINIMO_FAIXA_4) {
			baseCalculoImposto = MINIMO_FAIXA_4;
		}
		return (baseCalculoImposto - MINIMO_FAIXA_3) * TAXA_FAIXA_3;
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