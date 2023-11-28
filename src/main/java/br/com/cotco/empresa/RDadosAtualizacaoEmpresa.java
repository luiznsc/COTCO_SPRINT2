package br.com.cotco.empresa;

import br.com.cotco.endereco.RDadosEndereco;
import jakarta.validation.constraints.NotNull;

public record RDadosAtualizacaoEmpresa(
        @NotNull
        Long id,
        String nmFantEmpresa,
        String telEmpresa,
        String emailEmpresa,
        RDadosEndereco endereco) {
}
