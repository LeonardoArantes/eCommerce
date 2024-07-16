package com.fiap.dto;

import java.time.LocalDate;


public record DadosCartaoDTO(
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