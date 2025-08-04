package dev.Zerphyis.botFinanceiro.infra;

import dev.Zerphyis.botFinanceiro.application.services.GastosService;
import dev.Zerphyis.botFinanceiro.model.gastos.RequestGastos;
import dev.Zerphyis.botFinanceiro.model.gastos.ResponseGastos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
