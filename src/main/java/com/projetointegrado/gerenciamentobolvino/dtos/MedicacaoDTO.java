package com.projetointegrado.gerenciamentobolvino.dtos;

import com.projetointegrado.gerenciamentobolvino.domain.Medicacao;

import java.io.Serializable;

public class MedicacaoDTO implements Serializable {

    private Integer id;
    private String periodicidade;
    private String nomeMedicacao;
    private String loteMedicacao;

    public MedicacaoDTO(Integer id, String periodicidade, String nomeMedicacao, String loteMedicacao) {
        this.id = id;
        this.periodicidade = periodicidade;
        this.nomeMedicacao = nomeMedicacao;
        this.loteMedicacao = loteMedicacao;
    }

    public MedicacaoDTO(Medicacao obj) {
        this.id = obj.getId();
        this.periodicidade = obj.getPeriodicidade();
        this.nomeMedicacao = obj.getNomeMedicacao();
        this.loteMedicacao = obj.getLoteMedicacao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getNomeMedicacao() {
        return nomeMedicacao;
    }

    public void setNomeMedicacao(String nomeMedicacao) {
        this.nomeMedicacao = nomeMedicacao;
    }

    public String getLoteMedicacao() {
        return loteMedicacao;
    }

    public void setLoteMedicacao(String loteMedicacao) {
        this.loteMedicacao = loteMedicacao;
    }
}
