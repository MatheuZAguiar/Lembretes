package com.atividade.lembretes.service;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.DTOs.PessoaDTO;
import com.atividade.lembretes.entity.Lembrete;
import com.atividade.lembretes.entity.Pessoa;
import com.atividade.lembretes.repository.LembreteRepository;
import com.atividade.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final LembreteRepository lembreteRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, LembreteRepository lembreteRepository) {
        this.pessoaRepository = pessoaRepository;
        this.lembreteRepository = lembreteRepository;
    }

    public PessoaDTO cadastrarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = mapDTOToPessoa(pessoaDTO);
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return mapPessoaToDTO(savedPessoa);
    }

    public PessoaDTO obterPessoaPorNome(String nome) {
        Pessoa pessoa = pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return mapPessoaToDTO(pessoa);
    }

    public LembreteDTO associarLembrete(Long pessoaId, LembreteDTO lembreteDTO) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Lembrete lembrete = new Lembrete();
        lembrete.setTexto(lembreteDTO.getTexto());
        lembrete.setPessoa(pessoa);

        Lembrete savedLembrete = lembreteRepository.save(lembrete);

        pessoa.getLembretes().add(savedLembrete);
        pessoaRepository.save(pessoa);

        return mapLembreteToDTO(savedLembrete);
    }

    private Pessoa mapDTOToPessoa(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        return pessoa;
    }
    public List<PessoaDTO> obterTodasAsPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(this::mapPessoaToDTO)
                .collect(Collectors.toList());
    }

    private PessoaDTO mapPessoaToDTO(Pessoa pessoa) {
        List<LembreteDTO> lembretesDTO = mapLembretesToDTOs(pessoa.getLembretes());

        return new PessoaDTO(pessoa.getId(), pessoa.getNome(), lembretesDTO);
    }

    private List<LembreteDTO> mapLembretesToDTOs(List<Lembrete> lembretes) {
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

    public PessoaDTO atualizarPessoa(Long pessoaId, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoa.setNome(pessoaDTO.getNome());

        Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return mapPessoaToDTO(updatedPessoa);
    }

    public void deletarPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        pessoaRepository.delete(pessoa);
    }
}
