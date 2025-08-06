package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.infra.exceptions.SenhaIncorretaException;
import dev.Zerphyis.botFinanceiro.infra.exceptions.UsuarioNaoEncontradoException;
import dev.Zerphyis.botFinanceiro.model.repositorys.UsuarioRepository;
import dev.Zerphyis.botFinanceiro.model.usuarios.DataLoginUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.DataRegisterUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.ResponseUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceLogin implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Long register(DataRegisterUsuario data) {
        Optional<Usuario> existente = usuarioRepository.findByNome(data.nome());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já existe");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(data.nome());
        usuario.setMediaSalarial(data.mediaSalarial());

        Usuario salvo = usuarioRepository.save(usuario);
        return salvo.getId();
    }

    public ResponseUsuario login(DataLoginUsuario data) {
        Usuario usuario = usuarioRepository.findByNome(data.nome())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (!usuario.getMediaSalarial().equals(data.mediaSalarial())) {
            throw new SenhaIncorretaException("Senha incorreta");
        }

        return new ResponseUsuario(
                usuario.getNome(),
                usuario.getMediaSalarial()
        );
    }
}
