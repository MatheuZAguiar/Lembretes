package com.atividade.lembretes.controller;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public ResponseEntity<LembreteDTO> criarLembrete(@RequestBody LembreteDTO lembreteDTO) {
        LembreteDTO savedLembreteDTO = lembreteService.criarLembrete(lembreteDTO);
        return ResponseEntity.created(null).body(savedLembreteDTO);
    }
}
