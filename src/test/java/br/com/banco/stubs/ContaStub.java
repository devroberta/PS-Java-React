package br.com.banco.stubs;

import br.com.banco.entity.Conta;

public class ContaStub {

    public static Conta gerarContaCustomizada() {
        return Conta.builder()
                .idConta(1L)
                .nomeResponsavel("Fulano")
                .build();
    }
}
