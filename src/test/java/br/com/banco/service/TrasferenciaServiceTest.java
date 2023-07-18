package br.com.banco.service;

import br.com.banco.entity.Conta;
import br.com.banco.entity.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.stubs.ContaStub;
import br.com.banco.stubs.TransferenciaStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TrasferenciaServiceTest {

    @InjectMocks
    TransferenciaService transferenciaService;

    @Mock
    TransferenciaRepository transferenciaRepository;

    @Mock
    ContaRepository contaRepository;


    @BeforeEach
    void setUp() {
        Conta conta = ContaStub.gerarContaCustomizada();
        Transferencia transferencia = TransferenciaStub.gerarTransferenciaCustomizada();
        transferencia.setNomeOperadorTransacao("Beltrano");
        List<Transferencia> lista = new ArrayList<>();
        lista.add(transferencia);

        when(contaRepository.findById(any())).thenReturn(Optional.ofNullable(conta));
        when(transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(any(), any(), any(), any()))
                .thenReturn(lista);

    }

    @Test
    public void buscarTransferenciasPorContaSemParametrosDeFiltro() {
        Transferencia transferencia = TransferenciaStub.gerarTransferenciaCustomizada();
        Conta conta = ContaStub.gerarContaCustomizada();
        transferenciaService.buscarPorDatasEPorNomeOperadorTransacao(
                transferencia.getId(), transferencia.getDataTransferencia().toLocalDate().toString(),
                transferencia.getDataTransferencia().toLocalDate().toString(), "Beltrano");

        verify(transferenciaRepository, Mockito.times(1))
                .findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                        conta.getIdConta(), transferencia.getDataTransferencia().toLocalDate(),
                        transferencia.getDataTransferencia().toLocalDate(), "Beltrano");
    }
}
