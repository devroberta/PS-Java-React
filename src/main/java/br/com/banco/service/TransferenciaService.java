package br.com.banco.service;

import br.com.banco.dto.response.TransferenciaResponse;
import br.com.banco.entity.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Transactional(readOnly = true)
    public List<TransferenciaResponse> buscarTodas() {
        List<Transferencia> lista = transferenciaRepository.findAll();
        List<TransferenciaResponse> listaResponse = lista.stream().map(element -> new TransferenciaResponse(element)).collect(Collectors.toList());
        return listaResponse;
    }
}
