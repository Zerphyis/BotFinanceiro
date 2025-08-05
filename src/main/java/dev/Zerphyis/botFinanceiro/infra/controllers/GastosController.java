package dev.Zerphyis.botFinanceiro.infra.controllers;

import dev.Zerphyis.botFinanceiro.application.services.GastosService;
import dev.Zerphyis.botFinanceiro.model.gastos.RequestGastos;
import dev.Zerphyis.botFinanceiro.model.gastos.ResponseGastos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastosController {
    @Autowired
    GastosService service;

    @PostMapping
    public ResponseEntity<ResponseGastos> salvar(@RequestBody RequestGastos request){
        ResponseGastos response= service.salvar(request);
        return  ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseGastos>> listarTodos() {
        List<ResponseGastos> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGastos> buscarPorId(@PathVariable Long id) {
        try {
            ResponseGastos response = service.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGastos> atualizar(@PathVariable Long id, @RequestBody RequestGastos request) {
        try {
            ResponseGastos response = service.atualizar(id, request);
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
