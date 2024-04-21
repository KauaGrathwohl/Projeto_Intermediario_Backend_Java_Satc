package br.com.kaua.mostratempo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cidades")
public class Cidade extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cidade")
    private Set<ConsultaTempo> consultasTempo;

    @ManyToMany(mappedBy = "cidadesFavoritas")
    private Set<Usuario> usuarios;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
