package com.fiap.ecommerce.pagamento.DTO;

import java.time.LocalDate;

import com.fiap.ecommerce.pagamento.entity.DadosPagamento;
import com.fiap.ecommerce.pagamento.entity.StatusPagamento;
import com.fiap.ecommerce.pagamento.entity.tipoPagamento;

public record PagamentoDTO(
    long idPagamento,
    StatusPagamento statusPagamento,
    LocalDate dataPagamento,
    //private List<item> itens;
    tipoPagamento tipoPagamento,
    DadosPagamento dadosPagamento
) {

}
