package br.com.frete.spring;




import br.com.frete.entity.Usuario;
import br.com.frete.rn.UsuarioRN;
import br.com.frete.util.FreteUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class AuthenticationHandler implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = FreteUtil.encriptarSHA256(a.getCredentials().toString());
        UsuarioRN usuarioRN = new UsuarioRN();
        if (usuarioRN.autenticar(username, password)) {
            Usuario u = usuarioRN.obter(username);
            GrantedAuthorityImpl ga = new GrantedAuthorityImpl("ROLE_"+u.getPerfil());
            List<GrantedAuthority> auts = new ArrayList<>();
            auts.add(ga);
            UsernamePasswordAuthenticationToken resposta = new UsernamePasswordAuthenticationToken(username, password, auts);
            return resposta;
        } else {
            throw new BadCredentialsException("Email ou Senha inválidos");

        }
    }

    @Override
    public boolean supports(Class<? extends Object> type) {
        return true;
    }

}
