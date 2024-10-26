package br.com.joaodiniz.medicalconsult.consulta.services;

import br.com.joaodiniz.medicalconsult.consulta.domain.Consulta;
import br.com.joaodiniz.medicalconsult.consulta.repositories.ConsultaRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTeste {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void cadastrarConsulta() {
        Consulta consulta = new Consulta();
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        var resultado = consultaService.cadastrarConsulta(consulta);

        assertNotNull(resultado);
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void listarConsultas() {
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        when(consultaRepository.findAll()).thenReturn(consultas);

        var resultado = consultaService.listarConsultas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void buscarConsultaPorId() {
        Consulta consulta = new Consulta();
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));

        var resultado = consultaService.buscarConsulta(1L);

        assertNotNull(resultado);
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarConsultaPorIdNotFound() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> consultaService.buscarConsulta(1L));
    }

    @Test
    void deletarConsultaPorId() {
        Consulta consulta = new Consulta();
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));
        doNothing().when(consultaRepository).deleteById(anyLong());

        consultaService.deletarConsulta(1L);

        verify(consultaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletarConsultaPorIdComRelacionamento() {
        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(new Consulta()));
        doThrow(DataIntegrityViolationException.class).when(consultaRepository).deleteById(anyLong());

        assertThrows(DataIntegrityViolationException.class, () -> consultaService.deletarConsulta(1L));
    }

    @Test
    void atualizarConsulta() {
        Consulta consultaExistente = new Consulta();
        consultaExistente.setIdConsulta(1L);

        Consulta consultaAtualizada = new Consulta();
        consultaAtualizada.setIdConsulta(1L);

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consultaExistente));
        when(consultaRepository.save(any(Consulta.class))).thenReturn(consultaAtualizada);

        var resultado = consultaService.atualizarConsulta(consultaAtualizada);

        assertNotNull(resultado);
        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).save(consultaExistente);
    }
}
