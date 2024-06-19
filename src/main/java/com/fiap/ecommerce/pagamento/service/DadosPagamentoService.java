package com.fiap.ecommerce.pagamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ecommerce.pagamento.DTO.DadosPagamentoDTO;
import com.fiap.ecommerce.pagamento.entity.DadosPagamento;
import com.fiap.ecommerce.pagamento.repository.DadosPagamentoRepository;

@Service
public class DadosPagamentoService {

    @Autowired
    private DadosPagamentoRepository dadosPagamentoRepository;

    public DadosPagamentoDTO criarDadosPagamento(DadosPagamentoDTO dadosPagamentoDTO) throws Exception{
        DadosPagamento dadosPagamento = convertToEntity(dadosPagamentoDTO);
        if(dadosPagamento == null)
            throw new Exception("Dados do Cartão inválidos");  
    
        return convertToDTO(dadosPagamentoRepository.save(dadosPagamento));
    }

    public Optional<DadosPagamento> lerDadosPagamentoPorId(Long id) {
        Optional<DadosPagamento> OptionalDadosPagamento = dadosPagamentoRepository.findById(id);
        
        if(!OptionalDadosPagamento.isPresent())
            return Optional.empty();    

        return OptionalDadosPagamento;            
    }

    //UPDATE - não é necessario pois a partir do momento que o cartão é validado, não
    //faz sentido o atualiza-lo, apenas deletar e criar um novo.


    //DELETE
    public void deletarDadosPagamento(Long id) {
    dadosPagamentoRepository.deleteById(id);
    }


    /**
     * Converts a DadosPagamento object to a DadosPagamentoDTO object.
     *
     * @param  dadosPagamento  the DadosPagamento object to convert
     * @return                the converted DadosPagamentoDTO object
     */
    private DadosPagamentoDTO convertToDTO(DadosPagamento dadosPagamento) {
        return new DadosPagamentoDTO(dadosPagamento.getCartaoId(),
                                     dadosPagamento.getNome(),
                                     dadosPagamento.getSobrenome(),
                                     dadosPagamento.getNumeroCartao(),
                                     dadosPagamento.getCodSeguranca(),
                                     dadosPagamento.getBandeira(),
                                     dadosPagamento.getValidade());
    }

    /**
     * Converts a DadosPagamentoDTO object to a DadosPagamento object.
     *
     * @param  dadosPagamentoDTO  the DadosPagamentoDTO object to convert
     * @return                   the converted DadosPagamento object
     */
    private DadosPagamento convertToEntity(DadosPagamentoDTO dadosPagamentoDTO) {
        return new DadosPagamento(dadosPagamentoDTO.cartaoId(),
                                  dadosPagamentoDTO.nome(),
                                  dadosPagamentoDTO.sobrenome(),
                                  dadosPagamentoDTO.numeroCartao(),
                                  dadosPagamentoDTO.codSeguranca(),
                                  dadosPagamentoDTO.bandeira(),
                                  dadosPagamentoDTO.validade());
    }

}