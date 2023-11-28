package br.com.cotco.controller;

import br.com.cotco.empresa.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import br.com.cotco.empresa.Empresa;
import br.com.cotco.empresa.IEmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import br.com.cotco.empresa.*;

@RestController
@RequestMapping("empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaRepository EmpRepository;

    //cadastrar
    @PostMapping
    @org.springframework.transaction.annotation.Transactional
    public void cadastroEmpresa(@RequestBody @Valid RDadosCadastroEmpresa dadosEmpresa){
        EmpRepository.save(new Empresa(dadosEmpresa));
    }
    //buscar
    @GetMapping
    public Page<RDadosListagemEmpresa> listar(@RequestParam(name = "situacaoEmpresa") String situacaoEmpresa, @PageableDefault(size = 10, sort = {"nmFantEmpresa"}) Pageable paginacao){
        return EmpRepository.findAllBySituacaoEmpresa(situacaoEmpresa, paginacao).map(RDadosListagemEmpresa::new);
    }

    //atualizar
    @PutMapping("empresas/{idEmpresa}")
    @org.springframework.transaction.annotation.Transactional
    public void atualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody @Valid RDadosAtualizacaoEmpresa dadosEmpresa){
        var empresa = EmpRepository.getReferenceById(dadosEmpresa.id());
        empresa.atualizarInformacoes(dadosEmpresa);
    }

    //excluir
    @DeleteMapping("empresas/{idEmpresa}")
    @Transactional
    public void excluirEmpresa(@PathVariable Long idEmpresa){
        var empresa = EmpRepository.getReferenceById(idEmpresa);
        empresa.excluirEmpresa();
    }
}