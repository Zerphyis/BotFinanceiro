package dev.Zerphyis.botFinanceiro.infra.controllers;

import dev.Zerphyis.botFinanceiro.application.services.UsuarioServiceLogin;
import dev.Zerphyis.botFinanceiro.infra.exceptions.SenhaIncorretaException;
import dev.Zerphyis.botFinanceiro.infra.exceptions.UsuarioNaoEncontradoException;
import dev.Zerphyis.botFinanceiro.model.usuarios.DataLoginUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.DataRegisterUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.ResponseUsuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioLoginController {

    private final UsuarioServiceLogin usuarioService;

    public UsuarioLoginController(UsuarioServiceLogin usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@Valid @RequestBody DataRegisterUsuario data) {
        Long id = usuarioService.register(data);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUsuario> login(@Valid @RequestBody DataLoginUsuario data, HttpServletRequest request) {
        try {
            ResponseUsuario usuario = usuarioService.login(data);
            request.getSession().setAttribute("USER_NOME", usuario.nome());
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNaoEncontradoException | SenhaIncorretaException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, @AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        request.getSession().invalidate();
        return ResponseEntity.noContent().build();
    }
}
