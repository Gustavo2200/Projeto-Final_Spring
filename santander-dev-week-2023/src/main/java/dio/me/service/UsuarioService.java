package dio.me.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import dio.me.domain.model.Usuario;
import dio.me.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioServiceTODO {
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	@Override
	public Usuario buscarPorId(long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElseThrow(NoSuchElementException :: new);
	}

	@Override
	public Usuario criar(Usuario usuario) {
		if(usuario.getId() != null && usuarioRepository.existsById(usuario.getId())) {
			throw new IllegalArgumentException("Esse id de usuario ja existe ");
		}
		return usuarioRepository.save(usuario);
	}

}
