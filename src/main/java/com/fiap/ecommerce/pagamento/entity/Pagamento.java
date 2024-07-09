package com.fiap.ecommerce.pagamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPagamento;

    private StatusPagamento statusPagamento;

    private LocalDate dataPagamento;

    //private List<item> itens;

    private tipoPagamento tipoPagamento;

    private BigDecimal valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_pagamento_id", nullable = false)
    private DadosPagamento dadosPagamento;    
    

    //metodo simula o pagamento
    public StatusPagamento validarPagamento() {
        Random random = new Random();

        int min = 1;  // valor mínimo do intervalo
        int max = 100;  // valor máximo do intervalo

        int randomNum = random.nextInt(max - min) + min;

        if (randomNum % 2 != 0)
            return this.statusPagamento = StatusPagamento.RECUSADO;
        
        return this.statusPagamento = StatusPagamento.APROVADO;
    }
}
