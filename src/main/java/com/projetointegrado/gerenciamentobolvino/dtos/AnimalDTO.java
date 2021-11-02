package com.projetointegrado.gerenciamentobolvino.dtos;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;

import java.io.Serializable;

public class AnimalDTO implements Serializable {

    private Integer id;
    private String brinco;
    private String raca;
    private String dataCriacao;
    private String dataSaida;
    
    public AnimalDTO(Integer id, String brinco, String raca, String dataCriacao, String dataSaida) {
		this.id = id;
		this.brinco = brinco;
		this.raca = raca;
		this.dataCriacao = dataCriacao;
		this.dataSaida = dataSaida;
	}

	public AnimalDTO(Animal obj) {
        this.id = obj.getId();
        this.brinco = obj.getBrinco();
        this.raca = obj.getRaca();
        this.dataCriacao = obj.getDataCriacao();
        this.dataSaida = obj.getDataSaida();
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrinco() {
        return brinco;
    }

    public void setBrinco(String brinco) {
        this.brinco = brinco;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

}
