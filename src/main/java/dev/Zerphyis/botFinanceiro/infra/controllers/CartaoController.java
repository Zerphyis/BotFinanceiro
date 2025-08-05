package dev.Zerphyis.botFinanceiro.infra.controllers;

import dev.Zerphyis.botFinanceiro.application.services.CartaoService;
import dev.Zerphyis.botFinanceiro.model.cartao.RequestCartao;
import dev.Zerphyis.botFinanceiro.model.cartao.ResponseCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cartoes")
@RestController
public class CartaoController {

    @Autowired
    CartaoService service;

    @PostMapping
    public ResponseEntity<ResponseCartao> criar (@RequestBody RequestCartao request){
        ResponseCartao response= service.salvar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCartao>> listarTodos() {
        List<ResponseCartao> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCartao> buscarPorId(@PathVariable Long id) {
        try {
            ResponseCartao response = service.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCartao> atualizar(@PathVariable Long id, @RequestBody RequestCartao request) {
        try {
            ResponseCartao response = service.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
