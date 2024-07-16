package com.fiap.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fiap.entity.DadosCartao;
import com.fiap.entity.StatusPagamento;
import com.fiap.entity.TipoPagamento;

public record PagamentoDTO(
    long idPagamento,
    StatusPagamento statusPagamento,
    LocalDate dataPagamento,
    //private List<item> itens;
    TipoPagamento tipoPagamento,
    BigDecimal valorTotal,
    DadosCartao dadosPagamento
) {

}