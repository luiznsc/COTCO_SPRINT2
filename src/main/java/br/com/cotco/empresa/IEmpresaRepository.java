package br.com.cotco.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
    Page<Empresa> findAllBySituacaoEmpresa(@Param("situacao") String situacaoEmpresa, Pageable paginacao);
}