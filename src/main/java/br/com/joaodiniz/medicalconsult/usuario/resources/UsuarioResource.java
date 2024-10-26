package br.com.joaodiniz.medicalconsult.usuario.resources;

import br.com.joaodiniz.medicalconsult.usuario.domain.Usuario;
import br.com.joaodiniz.medicalconsult.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
       Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>>listusuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
                return ResponseEntity.ok().body(usuarios);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarUsuario(id);
        return  ResponseEntity.ok().body(usuario);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();


    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        return ResponseEntity.ok().body(usuario);
    }


}
