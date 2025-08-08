package TeamOne.BW_ENERGIA.security;

import TeamOne.BW_ENERGIA.entities.Utente;
import TeamOne.BW_ENERGIA.exceptions.UnauthorizedException;
import TeamOne.BW_ENERGIA.services.UtenteService;
import TeamOne.BW_ENERGIA.tools.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Inserire il token nel formato corretto!");
        }

        String accessToken = authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(accessToken);

        String utenteId = jwtTools.extractIdFromToken(accessToken);
        Optional<Utente> currentUtente = this.utenteService.findById(Long.valueOf(utenteId));

        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null, currentUtente.get().getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match("/api/auth/**", request.getServletPath())
                || matcher.match("/api/clienti/**", request.getServletPath())
                || matcher.match("/api/utenti/**", request.getServletPath());
    }
}
