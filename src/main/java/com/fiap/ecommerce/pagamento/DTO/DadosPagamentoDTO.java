package com.fiap.ecommerce.pagamento.DTO;

import java.time.LocalDate;


public record DadosPagamentoDTO(
    Long cartaoId,
    String nome,
    String sobrenome,
    String numeroCartao,
    short codSeguranca,
    String bandeira, 
    LocalDate validade
    //List<Cliente> cliente;    
) {
    
}
