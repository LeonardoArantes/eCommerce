package com.fiap.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pagamento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    private LocalDate dataPagamento;

    //private List<item> itens;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private BigDecimal valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_pagamento_id", referencedColumnName = "cartaoId")
    private DadosCartao dadosPagamento;    

}