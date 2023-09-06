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

    @PutMapping("/{lembreteId}")
    public ResponseEntity<LembreteDTO> atualizarLembrete(
            @PathVariable Long lembreteId,
            @RequestBody LembreteDTO lembreteDTO) {
        LembreteDTO updatedLembreteDTO = lembreteService.atualizarLembrete(lembreteId, lembreteDTO);
        return ResponseEntity.ok(updatedLembreteDTO);
    }

    @DeleteMapping("/{lembreteId}")
    public ResponseEntity<Void> deletarLembrete(@PathVariable Long lembreteId) {
        lembreteService.deletarLembrete(lembreteId);
        return ResponseEntity.ok().build();
    }
}
