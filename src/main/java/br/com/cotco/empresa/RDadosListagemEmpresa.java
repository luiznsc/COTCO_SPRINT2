package br.com.cotco.empresa;

public record RDadosListagemEmpresa(
        Long   idEmpresa,
        String nmFantEmpresa,
        String cnpjEmpresa){

    public RDadosListagemEmpresa(Empresa empresa){
        this(empresa.getIdEmpresa(),empresa.getNmFantEmpresa(), empresa.getCnpjEmpresa());
    }
}