package com.fiap.ecommerce.pagamento.entity;

import lombok.Getter;

@Getter
public enum tipoPagamento {
    DEBITO("DEBITO"),
    CREDITO("CREDITO"),
    BOLETO("BOLETO"),
    PIX("PIX");

    private String descricao;

    tipoPagamento(String descricao){
        this.descricao = descricao;
    }

}
