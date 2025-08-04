package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.model.cartao.Cartao;
import dev.Zerphyis.botFinanceiro.model.cartao.RequestCartao;
import dev.Zerphyis.botFinanceiro.model.cartao.ResponseCartao;
import dev.Zerphyis.botFinanceiro.model.repositorys.CartaoRepository;
import dev.Zerphyis.botFinanceiro.model.repositorys.UsuarioRepository;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public ResponseCartao salvar(RequestCartao dto) {
        Usuario usuario = usuarioRepo.findById(dto.usuarioId()).orElseThrow();

        Cartao c = new Cartao();
        c.setNomeCartao(dto.nomeCartao());
        c.setLimite(dto.limite());
        c.setPortadorAtual(dto.portadorAtual());
        c.setDataInicio(dto.dataInicio());
        c.setDataFim(dto.dataFim());
        c.setUsuario(usuario);

        Cartao salvo = repo.save(c);

        return new ResponseCartao(
                salvo.getNomeCartao(),
                salvo.getLimite(),
                salvo.getPortadorAtual(),
                salvo.getDataInicio(),
                salvo.getDataFim(),
                salvo.getUsuario().getNome()
        );
    }
}
