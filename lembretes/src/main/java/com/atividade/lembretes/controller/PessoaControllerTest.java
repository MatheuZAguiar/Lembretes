package com.atividade.lembretes.controller;
import com.atividade.lembretes.DTOs.PessoaDTO;
import com.atividade.lembretes.DTOs.LembreteDTO;
import com.atividade.lembretes.service.PessoaService;
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

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        // Cria uma lista de lembretes fictícia
        List<LembreteDTO> listaDeLembretes = Arrays.asList(
                new LembreteDTO(1L, "Lembrete 1"),
                new LembreteDTO(2L, "Lembrete 2")
        );

        PessoaDTO pessoa = new PessoaDTO();
        pessoa.setId(1L);
        pessoa.setNome("Nome da Pessoa");
        pessoa.setLembretes(listaDeLembretes);
        List<PessoaDTO> pessoas = Arrays.asList(pessoa);
        Mockito.when(pessoaService.obterTodasAsPessoas()).thenReturn(pessoas);
        Mockito.when(pessoaService.obterPessoaPorNome(Mockito.anyString())).thenReturn(pessoa);
    }

    @Test
    public void testObterTodasAsPessoas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Nome da Pessoa"));
    }

    @Test
    public void testObterPessoaPorNome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/AlgumNome")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Nome da Pessoa"));
    }

    @Test
    public void testAtualizarPessoa() throws Exception {
        Long pessoaId = 1L;
        PessoaDTO pessoaAtualizada = new PessoaDTO();
        pessoaAtualizada.setNome("Novo Nome");
        Mockito.when(pessoaService.atualizarPessoa(pessoaId, pessoaAtualizada)).thenReturn(pessoaAtualizada);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/pessoas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Nome\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Novo Nome"));
    }

    @Test
    public void testDeletarPessoa() throws Exception {
        Long pessoaId = 1L;
        Mockito.doNothing().when(pessoaService).deletarPessoa(pessoaId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pessoas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
