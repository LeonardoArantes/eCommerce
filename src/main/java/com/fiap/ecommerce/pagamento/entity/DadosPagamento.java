package com.fiap.ecommerce.pagamento.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "dados_pagamento")
public class DadosPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartaoId;

    private String nome;

    private String sobrenome;

    private String numeroCartao;

    private short codSeguranca;

    private String bandeira; 

    private LocalDate validade;

    //@ManyToMany(mappedBy = "cartao")
    //private List<Cliente> cliente;

}
