package com.fiap.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.dto.DadosCartaoDTO;
import com.fiap.entity.DadosCartao;
import com.fiap.service.DadosCartaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/dados-cartao")
public class DadosCartaoController {
       
    @Autowired
    private DadosCartaoService dadosPagamentoService;


    @PostMapping
    public ResponseEntity<DadosCartaoDTO> criarDadosPagamento(@RequestBody DadosCartaoDTO dadosPagamentoDTO) throws Exception{
        DadosCartaoDTO dadosPagamento = dadosPagamentoService.criarDadosPagamento(dadosPagamentoDTO);
        
        return new ResponseEntity<>(dadosPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DadosCartao>> lerDadosPagamentoPorId(@PathVariable Long id){
        Optional<DadosCartao> dadosPagamento = dadosPagamentoService.lerDadosPagamentoPorId(id);
        return dadosPagamento != null ? new ResponseEntity<>(dadosPagamento, HttpStatus.OK) : 
                                        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarDadosPagamento(@PathVariable Long id){
        dadosPagamentoService.deletarDadosPagamento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}