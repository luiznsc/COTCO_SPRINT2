package br.com.cotco.empresa;

import br.com.cotco.endereco.DadosEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "TB_EMPRESA")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEmpresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPRESA")
    @SequenceGenerator(name="SQ_EMPRESA", sequenceName = "SQ_EMPRESA")
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    @Column(name = "RZSOCIAL_EMPRESA", nullable = false)
    private String rzSocialEmpresa;

    @Column(name = "NMFANT_EMPRESA", nullable = false)
    private String nmFantEmpresa;

    @Column(name = "CNPJ_EMPRESA", nullable = false)
    private String cnpjEmpresa;

    @Column(name = "TEL_EMPRESA", nullable = false)
    private String telEmpresa;

    @Column(name = "EMAIL_EMPRESA", nullable = false)
    private String emailEmpresa;

    @Column(name =  "SITUACAO_EMPRESA", nullable = false)
    private String situacaoEmpresa;

    @Embedded
    private DadosEndereco EnderecoEmpresa;

    //construtor para repository campo empresaUsuario
    public Empresa(String cnpjEmpresa) {
        this();
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Empresa(RDadosCadastroEmpresa dadosEmpresa) {
        this.rzSocialEmpresa = dadosEmpresa.rzSocialEmpresa();
        this.nmFantEmpresa = dadosEmpresa.nmFantEmpresa();
        this.cnpjEmpresa = dadosEmpresa.cnpjEmpresa();
        this.telEmpresa = dadosEmpresa.telEmpresa();
        this.emailEmpresa = dadosEmpresa.emailEmpresa();
        this.EnderecoEmpresa = new DadosEndereco();
    }
    //metodo para tratar os campos de atualizacao do endereco
    public void atualizarInformacoes(RDadosAtualizacaoEmpresa dadosEmpresa){
        if (dadosEmpresa.nmFantEmpresa() != null) {
            this.nmFantEmpresa = dadosEmpresa.nmFantEmpresa();
        }
        if (dadosEmpresa.telEmpresa() != null) {
            this.telEmpresa = dadosEmpresa.telEmpresa();
        }
        if (dadosEmpresa.emailEmpresa() != null) {
            this.emailEmpresa = dadosEmpresa.emailEmpresa();
        }
        if (dadosEmpresa.endereco() != null) {
            this.EnderecoEmpresa.atualizarInformacoes(dadosEmpresa.endereco());
        }

    }

    //delete fará empresa ficar inativa
    public void excluirEmpresa() {
        this.situacaoEmpresa = "INATIVA";
    }
}
