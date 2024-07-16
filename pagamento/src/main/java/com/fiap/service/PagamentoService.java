package com.fiap.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.dto.PagamentoDTO;
import com.fiap.entity.Pagamento;
import com.fiap.entity.StatusPagamento;
import com.fiap.repository.PagamentoRepository;

import lombok.NonNull;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public static Logger LOGGER = LoggerFactory.getLogger(PagamentoService.class);

    //CREATE
    public PagamentoDTO criarPagamento(PagamentoDTO pagamentoDTO) throws Exception{
        Pagamento pagamento = convertToEntity(pagamentoDTO);
        if(pagamento == null)
            throw new Exception("Pagamento inválido");
        
        pagamento = pagamentoRepository.save(pagamento);
        
        return pagamentoDTO;
    }
    
    //READ
    public Optional<Pagamento> lerPagamentoPorId(Long id) {
        LOGGER.error("BUSCANDO PAGAMENTO POR ID {}", id);
        Optional<Pagamento> OptionalPagamento = pagamentoRepository.findById(id);
        if (OptionalPagamento.isEmpty()) {
            LOGGER.error("PAGAMENTO NÃO ENCONTRADO");
            return Optional.empty();
        }

        Pagamento pagamento = OptionalPagamento.get();
        LOGGER.info("PAGAMENTO ENCONTRADO id:{}", pagamento.getIdPagamento());
        return Optional.of(pagamento);
    }

    //READALL
    public List<Pagamento> lerTodosPagamentos(){
        return pagamentoRepository.findAll();
    }

    //UPDATE
    // a unica parte do pagamento que sera atualizada é o status
    public Pagamento atualizarPagamento(Long id, Pagamento pagamento){
        return pagamentoRepository.findById(id)
                .map(pagamentoExistente -> {
                    pagamentoExistente.setStatusPagamento(validarPagamento());
                    LOGGER.info( "Pagamento atualizado: {}", pagamentoExistente.getStatusPagamento());
                    return pagamentoRepository.save(pagamentoExistente);
                }).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    //DELETE
    //nao há necessidade de exclusão dos pagamentos

    /**
     * Converts a Pagamento object to a PagamentoDTO object.
     *
     * @param  pagamento  the Pagamento object to convert
     * @return            the converted PagamentoDTO object
     */
    public PagamentoDTO convertToDTO(Pagamento pagamento){
        return new PagamentoDTO(pagamento.getIdPagamento(),
        pagamento.getStatusPagamento(),
        pagamento.getDataPagamento(),
        pagamento.getTipoPagamento(),
        pagamento.getValorTotal(),
        pagamento.getDadosPagamento());
    }

    /**
     * Converts a PagamentoDTO object to a Pagamento object.
     *
     * @param  pagamentoDTO  the PagamentoDTO object to convert
     * @return                the converted Pagamento object
     */
    public Pagamento convertToEntity(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(pagamentoDTO.idPagamento());
        pagamento.setStatusPagamento(pagamentoDTO.statusPagamento());
        pagamento.setDataPagamento(pagamentoDTO.dataPagamento());
        pagamento.setTipoPagamento(pagamentoDTO.tipoPagamento());
        pagamento.setValorTotal(pagamentoDTO.valorTotal());
        pagamento.setDadosPagamento(pagamentoDTO.dadosPagamento());
               
        return pagamento;
    }

        //metodo simula o pagamento
    public StatusPagamento validarPagamento() {
        Random random = new Random();

        int min = 1;  // valor mínimo do intervalo
        int max = 100;  // valor máximo do intervalo

        int randomNum = random.nextInt(max - min) + min;

        if (randomNum % 2 != 0)
            return StatusPagamento.RECUSADO;
        
        return StatusPagamento.APROVADO;
    }
}