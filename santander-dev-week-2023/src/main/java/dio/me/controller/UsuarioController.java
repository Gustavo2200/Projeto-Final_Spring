package dio.me.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dio.me.domain.model.Usuario;
import dio.me.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable long id){
		var usuario = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}
	@PostMapping("/add")
	public ResponseEntity<Usuario> buscarPorId(@RequestBody Usuario usuario){
		var usuarioCriado = usuarioService.criar(usuario);
		URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuarioCriado.getId()).toUri();
		return ResponseEntity.created(localizacao).body(usuarioCriado);
	}
}
