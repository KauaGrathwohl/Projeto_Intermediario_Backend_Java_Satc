package br.com.kaua.mostratempo.model;

import jakarta.persistence.*;

import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "criado_em", nullable = false)
    private Instant criadoEm;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @PrePersist
    public void prePersist() {
        this.criadoEm = Instant.now();
        this.ativo = true;
    }

    @PreRemove
    public void preRemove() {
        this.ativo = false;
    }
}
