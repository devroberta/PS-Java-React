package br.com.banco.service;

import br.com.banco.dto.response.TransferenciaResponse;
import br.com.banco.entity.Conta;
import br.com.banco.entity.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private TransferenciaRepository transferenciaRepository;
    private ContaRepository contaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaRepository contaRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional(readOnly = true)
    public List<TransferenciaResponse> buscarPorDatasEPorNomeOperadorTransacao(
            Long id,
            String dataInicialRequest,
            String dataFinalRequest,
            String nomeOperadorTransacao) {

        LocalDate dataInicialResponse;
        LocalDate dataFinalResponse;

        Optional<Conta> conta = contaRepository.findById(id);


        dataInicialResponse = dataInicialRequest.equals("0") ?
                            converterStringData("1900-01-01") : converterStringData(dataInicialRequest);

        dataFinalResponse = dataFinalRequest.equals("0") ?
                converterStringData("2999-12-31") : converterStringData(dataFinalRequest);

        if (dataInicialResponse.isAfter(dataFinalResponse)) {
            //Lançar Exception
        }

        if (nomeOperadorTransacao.equals("0")) {
            nomeOperadorTransacao = "%%";
        } else {
            nomeOperadorTransacao = new StringBuilder()
                    .append("%")
                    .append(nomeOperadorTransacao)
                    .append("%")
                    .toString();
        }

        List<Transferencia> lista = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                id, dataInicialResponse, dataFinalResponse, nomeOperadorTransacao);
        List<TransferenciaResponse> listaResponse = lista.stream().map(element -> new TransferenciaResponse(element)).collect(Collectors.toList());
        return listaResponse;
    }

    private LocalDate converterStringData(String dataRequest) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dataRequest, sdf);
    }
}
