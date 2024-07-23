package com.fiap.entity;

import lombok.Getter;

@Getter
public enum StatusPagamento {

    PENDENTE("PENDENTE"),
    APROVADO("APROVADO"),
    RECUSADO("RECUSADO"),
    CANCELADO("CANCELADO");

    private String descricao;

    StatusPagamento(String descricao){
        this.descricao = descricao;
    }

}