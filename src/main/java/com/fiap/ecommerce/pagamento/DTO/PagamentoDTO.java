package com.fiap.ecommerce.pagamento.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fiap.ecommerce.pagamento.entity.DadosCartao;
import com.fiap.ecommerce.pagamento.entity.StatusPagamento;
import com.fiap.ecommerce.pagamento.entity.tipoPagamento;

public record PagamentoDTO(
    long idPagamento,
    StatusPagamento statusPagamento,
    LocalDate dataPagamento,
    //private List<item> itens;
    tipoPagamento tipoPagamento,
    BigDecimal valorTotal,
    DadosCartao dadosPagamento
) {

}
