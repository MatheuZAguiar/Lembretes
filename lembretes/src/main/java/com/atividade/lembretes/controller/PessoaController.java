package com.atividade.lembretes.controller;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.DTOs.PessoaDTO;
import com.atividade.lembretes.entity.Lembrete;
import com.atividade.lembretes.entity.NotFoundException;
import com.atividade.lembretes.entity.Pessoa;
import com.atividade.lembretes.repository.LembreteRepository;
import com.atividade.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private final PessoaRepository pessoaRepository;
    @Autowired
    private final LembreteRepository lembreteRepository;

    public PessoaController(PessoaRepository pessoaRepository, LembreteRepository lembreteRepository) {
        this.pessoaRepository = pessoaRepository;
        this.lembreteRepository = lembreteRepository;
    }

    @GetMapping("/{nome}")
    public PessoaDTO buscarPessoaPorNome(@PathVariable String nome) {
        Pessoa pessoa = pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        return mapPessoaToDTO(pessoa);
    }

    @PostMapping("/{pessoaId}/lembretes")
    public LembreteDTO adicionarLembrete(@PathVariable Long pessoaId, @RequestBody LembreteDTO lembreteDTO) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        Lembrete lembrete = new Lembrete();
        lembrete.setPessoa(pessoa);
        lembrete.setTexto(lembreteDTO.getTexto());

        Lembrete savedLembrete = lembreteRepository.save(lembrete);
        return mapLembreteToDTO(savedLembrete);
    }

    @GetMapping("/{pessoaId}/lembretes")
    public List<LembreteDTO> listarLembretesDaPessoa(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        List<Lembrete> lembretes = lembreteRepository.findByPessoa(pessoa);
        return lembretes.stream()
                .map(this::mapLembreteToDTO)
                .collect(Collectors.toList());
    }

    private PessoaDTO mapPessoaToDTO(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setLembretes(mapLembretesToDTO(pessoa.getLembretes()));
        return dto;
    }

    private List<LembreteDTO> mapLembretesToDTO(List<Lembrete> lembretes) {
        return lembretes.stream()
                .map(this::mapLembreteToDTO)
                .collect(Collectors.toList());
    }

    private LembreteDTO mapLembreteToDTO(Lembrete lembrete) {
        LembreteDTO dto = new LembreteDTO();
        dto.setId(lembrete.getId());
        dto.setTexto(lembrete.getTexto());
        return dto;
    }
}