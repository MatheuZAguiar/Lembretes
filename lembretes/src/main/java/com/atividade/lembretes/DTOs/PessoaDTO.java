package com.atividade.lembretes.DTOs;

import java.util.List;

public class PessoaDTO {
    private Long id;
    private String nome;
    private List<LembreteDTO> lembretes;

    public PessoaDTO(Long id, String nome, List<LembreteDTO> lembretes) {
        this.id = id;
        this.nome = nome;
        this.lembretes = lembretes;
    }

    public PessoaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LembreteDTO> getLembretes() {
        return lembretes;
    }

    public void setLembretes(List<LembreteDTO> lembretes) {
        this.lembretes = lembretes;
    }
}
