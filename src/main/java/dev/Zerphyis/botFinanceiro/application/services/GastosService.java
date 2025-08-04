package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.model.gastos.Gastos;
import dev.Zerphyis.botFinanceiro.model.gastos.RequestGastos;
import dev.Zerphyis.botFinanceiro.model.gastos.ResponseGastos;
import dev.Zerphyis.botFinanceiro.model.repositorys.GastosRepository;
import dev.Zerphyis.botFinanceiro.model.repositorys.UsuarioRepository;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GastosService {
    @Autowired
    private GastosRepository gastoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public ResponseGastos salvar(RequestGastos dto) {
        Usuario usuario = usuarioRepo.findById(dto.usuarioId()).orElseThrow();

        Gastos gasto = new Gastos();
        gasto.setNome(dto.nome());
        gasto.setCategoria(dto.categoria());
        gasto.setValor(dto.valor());
        gasto.setDataHora(dto.dataHora());
        gasto.setUsuario(usuario);

        Gastos salvo = gastoRepo.save(gasto);

        return new ResponseGastos(
                salvo.getNome(),
                salvo.getCategoria(),
                salvo.getValor(),
                salvo.getDataHora(),
                salvo.getUsuario().getNome()
        );
    }
}
