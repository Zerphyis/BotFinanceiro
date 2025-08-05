package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.infra.exceptions.ResourceNotFoundException;
import dev.Zerphyis.botFinanceiro.model.cartao.Cartao;
import dev.Zerphyis.botFinanceiro.model.cartao.RequestCartao;
import dev.Zerphyis.botFinanceiro.model.cartao.ResponseCartao;
import dev.Zerphyis.botFinanceiro.model.repositorys.CartaoRepository;
import dev.Zerphyis.botFinanceiro.model.repositorys.UsuarioRepository;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ResponseCartao> listarTodos() {
        List<Cartao> lista = repo.findAll();
        return lista.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ResponseCartao buscarPorId(Long id) {
        Cartao c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));
        return mapToResponse(c);
    }

    public ResponseCartao atualizar(Long id, RequestCartao dto) {
        Cartao c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));

        Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        c.setNomeCartao(dto.nomeCartao());
        c.setLimite(dto.limite());
        c.setPortadorAtual(dto.portadorAtual());
        c.setDataInicio(dto.dataInicio());
        c.setDataFim(dto.dataFim());
        c.setUsuario(usuario);

        Cartao atualizado = repo.save(c);

        return mapToResponse(atualizado);
    }

    public void deletar(Long id) {
        Cartao c = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));
        repo.delete(c);
    }

    private ResponseCartao mapToResponse(Cartao c) {
        return new ResponseCartao(
                c.getNomeCartao(),
                c.getLimite(),
                c.getPortadorAtual(),
                c.getDataInicio(),
                c.getDataFim(),
                c.getUsuario().getNome()
        );
    }

}
