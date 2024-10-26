package br.com.joaodiniz.medicalconsult.usuario.repositories;

import br.com.joaodiniz.medicalconsult.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
