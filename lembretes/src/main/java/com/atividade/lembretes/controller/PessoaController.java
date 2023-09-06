package com.atividade.lembretes.controller;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.DTOs.PessoaDTO;
import com.atividade.lembretes.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> obterTodasAsPessoas() {
        List<PessoaDTO> pessoasDTO = pessoaService.obterTodasAsPessoas();
        return ResponseEntity.ok(pessoasDTO);
    }
    @GetMapping("/{nome}")
    public ResponseEntity<PessoaDTO> obterPessoaPorNome(@PathVariable String nome) {
        PessoaDTO pessoaDTO = pessoaService.obterPessoaPorNome(nome);
        return ResponseEntity.ok(pessoaDTO);
    }
    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO savedPessoaDTO = pessoaService.cadastrarPessoa(pessoaDTO);
        return ResponseEntity.created(null).body(savedPessoaDTO);
    }
    @PostMapping("/{pessoaId}/lembretes")
    public ResponseEntity<LembreteDTO> associarLembrete(@PathVariable Long pessoaId, @RequestBody LembreteDTO lembreteDTO) {
        LembreteDTO savedLembreteDTO = pessoaService.associarLembrete(pessoaId, lembreteDTO);
        return ResponseEntity.created(null).body(savedLembreteDTO);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long pessoaId, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO updatedPessoaDTO = pessoaService.atualizarPessoa(pessoaId, pessoaDTO);
        return ResponseEntity.ok(updatedPessoaDTO);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long pessoaId) {
        pessoaService.deletarPessoa(pessoaId);
        return ResponseEntity.ok().build();
    }
}

