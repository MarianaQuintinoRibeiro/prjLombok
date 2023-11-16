package com.mariana.lomnok.PrjLombok.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mariana.lomnok.PrjLombok.entities.Usuario;
import com.mariana.lomnok.PrjLombok.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService userService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.userService = usuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUsuariobyId(@PathVariable Long id) {
		Usuario usuario = userService.findUsuarioById(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> findAllUsuarioscontrol() {
		List<Usuario> usuarios = userService.findAllUsuario();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping
	public ResponseEntity<Usuario> insertUsuariosControl(@RequestBody @Valid Usuario usuario) {
		Usuario novoUsuario = userService.insertUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}

	@PutMapping("/id")
	public ResponseEntity<Usuario> updateUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
		Usuario mudausuario = userService.updateUsuario(id, usuario);
		if (mudausuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean remover = userService.deleteUsuario(id);
		if (remover) {
			return ResponseEntity.ok().body("usuario Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
