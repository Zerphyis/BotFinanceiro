package dev.Zerphyis.botFinanceiro.infra;

import dev.Zerphyis.botFinanceiro.application.services.UsuarioService;
import dev.Zerphyis.botFinanceiro.model.usuarios.RequestUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.ResponseUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<ResponseUsuario> criar(@RequestBody RequestUsuario dto) {
        ResponseUsuario response = service.salvar(dto);
        return ResponseEntity.ok(response);
    }
}
