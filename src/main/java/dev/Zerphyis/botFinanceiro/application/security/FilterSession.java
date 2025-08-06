package dev.Zerphyis.botFinanceiro.application.security;

import dev.Zerphyis.botFinanceiro.application.services.UsuarioServiceLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterSession extends OncePerRequestFilter {

    private final UsuarioServiceLogin usuarioService;

    public FilterSession(UsuarioServiceLogin usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var context = SecurityContextHolder.getContext();

        if (context.getAuthentication() == null) {
            var nome = (String) request.getSession().getAttribute("USER_NOME");
            if (nome != null) {
                var userDetails = usuarioService.loadUserByUsername(nome);
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
