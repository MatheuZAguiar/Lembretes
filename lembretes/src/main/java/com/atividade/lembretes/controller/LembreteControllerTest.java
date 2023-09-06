package com.atividade.lembretes.controller;

import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.service.LembreteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(LembreteController.class)
public class LembreteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LembreteService lembreteService;

    @BeforeEach
    public void setUp() {
        LembreteDTO lembrete = new LembreteDTO(1L, "Lembrete 1");
        Mockito.when(lembreteService.criarLembrete(Mockito.any(LembreteDTO.class))).thenReturn(lembrete);
        Mockito.when(lembreteService.atualizarLembrete(Mockito.anyLong(), Mockito.any(LembreteDTO.class))).thenReturn(lembrete);
        Mockito.doNothing().when(lembreteService).deletarLembrete(Mockito.anyLong());
    }

    @Test
    public void testCriarLembrete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lembretes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"texto\":\"Novo Lembrete\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.texto").value("Lembrete 1"));
    }

    @Test
    public void testAtualizarLembrete() throws Exception {
        Long lembreteId = 1L;
        LembreteDTO lembreteAtualizado = new LembreteDTO();
        lembreteAtualizado.setTexto("Novo Texto");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/lembretes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"texto\":\"Novo Texto\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.texto").value("Lembrete 1"));
    }

    @Test
    public void testDeletarLembrete() throws Exception {
        Long lembreteId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lembretes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
