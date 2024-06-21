package com.fiap.ecommerce.pagamento.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ecommerce.pagamento.DTO.DadosPagamentoDTO;
import com.fiap.ecommerce.pagamento.entity.DadosPagamento;
import com.fiap.ecommerce.pagamento.service.DadosPagamentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/dados-cartao")
public class DadosCartaoController {
       
    @Autowired
    private DadosPagamentoService dadosPagamentoService;


    @PostMapping
    public ResponseEntity<DadosPagamentoDTO> criarDadosPagamento(@RequestBody DadosPagamentoDTO dadosPagamentoDTO) throws Exception{
        DadosPagamentoDTO dadosPagamento = dadosPagamentoService.criarDadosPagamento(dadosPagamentoDTO);
        
        return new ResponseEntity<>(dadosPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DadosPagamento>> lerDadosPagamentoPorId(@RequestParam Long id){
        Optional<DadosPagamento> dadosPagamento = dadosPagamentoService.lerDadosPagamentoPorId(id);
        return dadosPagamento != null ? new ResponseEntity<>(dadosPagamento, HttpStatus.OK) : 
                                        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarDadosPagamento(@RequestParam Long id){
        dadosPagamentoService.deletarDadosPagamento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
