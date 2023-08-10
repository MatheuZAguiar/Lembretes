package com.atividade.lembretes.DTOs;

public class LembreteDTO {
    private Long id;
    private String texto;


    public LembreteDTO(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public LembreteDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}