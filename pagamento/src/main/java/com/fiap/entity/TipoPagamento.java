package com.fiap.entity;

import lombok.Getter;

@Getter
public enum TipoPagamento {
    DEBITO("DEBITO"),
    CREDITO("CREDITO"),
    BOLETO("BOLETO"),
    PIX("PIX");

    private String descricao;

    TipoPagamento(String descricao){
        this.descricao = descricao;
    }

}