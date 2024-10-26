package br.com.joaodiniz.medicalconsult.consulta.domain;

import br.com.joaodiniz.medicalconsult.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONSULTAS")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;

    @Column(name = "DATA_CONSULTA")
    private String dataConsulta;

    @Column(name = "PROFISSIONAL")
    private String profissional;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    public Consulta(){
    }

    public Consulta(Long idConsulta, String dataConsulta, String profissional, String especialidade, Usuario usuario){
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.profissional = profissional;
        this.especialidade = especialidade;
        this.usuario = usuario;
    }
}
