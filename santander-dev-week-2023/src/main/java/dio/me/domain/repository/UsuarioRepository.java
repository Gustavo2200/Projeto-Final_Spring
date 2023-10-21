package dio.me.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.me.domain.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
