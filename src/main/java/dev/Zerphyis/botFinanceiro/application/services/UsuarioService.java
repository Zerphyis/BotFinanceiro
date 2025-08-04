package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.model.repositorys.UsuarioRepository;
import dev.Zerphyis.botFinanceiro.model.usuarios.RequestUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.ResponseUsuario;
import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository repository;

   public ResponseUsuario salvar(RequestUsuario request){
       Usuario u= new Usuario();
       u.setNome(request.nome());
       u.setMediaSalarial(request.mediaSalarial());
       Usuario salvar= repository.save(u);
       return  new ResponseUsuario(salvar.getNome(), salvar.getMediaSalarial());
   }
}
