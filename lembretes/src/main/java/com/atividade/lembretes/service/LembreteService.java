package com.atividade.lembretes.service;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.entity.Lembrete;
import com.atividade.lembretes.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public LembreteDTO criarLembrete(LembreteDTO lembreteDTO) {
        Lembrete lembrete = mapDTOToLembrete(lembreteDTO);
        Lembrete savedLembrete = lembreteRepository.save(lembrete);
        return mapLembreteToDTO(savedLembrete);
    }

    private Lembrete mapDTOToLembrete(LembreteDTO dto) {
        Lembrete lembrete = new Lembrete();
        lembrete.setTexto(dto.getTexto());
        return lembrete;
    }

    private LembreteDTO mapLembreteToDTO(Lembrete lembrete) {
        LembreteDTO dto = new LembreteDTO();
        dto.setId(lembrete.getId());
        dto.setTexto(lembrete.getTexto());
        return dto;
    }
}
