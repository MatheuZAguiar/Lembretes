package com.atividade.lembretes.repository;

import com.atividade.lembretes.entity.Lembrete;
import com.atividade.lembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByPessoa(Pessoa pessoa);
}