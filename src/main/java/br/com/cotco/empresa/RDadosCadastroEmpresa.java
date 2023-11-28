package br.com.cotco.empresa;

import br.com.cotco.endereco.RDadosEndereco;

public record RDadosCadastroEmpresa(
        String rzSocialEmpresa,
        String nmFantEmpresa,
        String cnpjEmpresa,
        String telEmpresa,
        String emailEmpresa,
        RDadosEndereco enderecoEmpresa
) {
}