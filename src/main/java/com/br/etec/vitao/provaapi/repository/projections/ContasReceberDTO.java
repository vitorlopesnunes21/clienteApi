package com.br.etec.vitao.provaapi.repository.projections;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContasReceberDTO {
    private Long id;
    private String nomecliente;
    private LocalDate dataconta;

    public ContasReceberDTO(Long id, String nomecliente, LocalDate dataconta) {
        this.id = id;
        this.nomecliente = nomecliente;
        this.dataconta = dataconta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public LocalDate getDataconta() {
        return dataconta;
    }

    public void setDataconta(LocalDate dataconta) {
        this.dataconta = dataconta;
    }


}
