package dev.Zerphyis.botFinanceiro.infra;

import dev.Zerphyis.botFinanceiro.application.services.CartaoService;
import dev.Zerphyis.botFinanceiro.model.cartao.RequestCartao;
import dev.Zerphyis.botFinanceiro.model.cartao.ResponseCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
