package app;

import java.util.ArrayList;
import java.util.List;

public class Deducao {
    private List<String> nomesDeducoes;
    private List<Float> valoresDeducoes;

    public Deducao() {
        this.nomesDeducoes = new ArrayList<>();
        this.valoresDeducoes = new ArrayList<>();
    }

    public void adicionarDeducao(String nome, float valor) {
        nomesDeducoes.add(nome);
        valoresDeducoes.add(valor);
    }

    public float getTotalDeducoes() {
        return valoresDeducoes.stream().reduce(0f, Float::sum);
    }

    public List<String> getNomesDeducoes() {
        return nomesDeducoes;
    }

    public List<Float> getValoresDeducoes() {
        return valoresDeducoes;
    }
}