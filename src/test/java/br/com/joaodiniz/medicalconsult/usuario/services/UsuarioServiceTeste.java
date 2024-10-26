package br.com.joaodiniz.medicalconsult.usuario.services;

import br.com.joaodiniz.medicalconsult.usuario.domain.Usuario;
import br.com.joaodiniz.medicalconsult.usuario.repositories.UsuarioRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Alysson");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        var resultado = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Alysson", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void listarUsuarios(){
        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("Alysson");
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("José");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        var resultado = usuarioService.listarUsuarios();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Alysson", resultado.get(0).getNomeUsuario());
        assertEquals("José", resultado.get(1).getNomeUsuario());
    }

    @Test
    void buscarUsuariosPorId(){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Alysson");
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        var resultado = usuarioService.buscarUsuario(1L);

        assertNotNull(resultado);
        assertEquals("Alysson", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void deletarUsuarioPorId(){
        doNothing().when(usuarioRepository).deleteById(anyLong());

        usuarioService.deleteUsuario(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void atualizarUsuarioPorId(){
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNomeUsuario("Alysson");
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNomeUsuario("José");

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        var resultado = usuarioService.atualizarUsuario(1L, usuarioAtualizado);

        assertNotNull(resultado);
        assertEquals("José", resultado.getNomeUsuario());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuarioAtualizado);
    }
}