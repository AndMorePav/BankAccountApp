package reckoning.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reckoning.security.model.Role;
import reckoning.security.model.SecurityUser;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticatedUser {

    public Long getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = auth.getPrincipal();
        Long id = null;
        if (principal instanceof UserDetails) {
            id = ((SecurityUser) principal).getId();
        }
        return id;
    }

    public List<String> getRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public boolean hasRole(Role userRole) {
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.stream().
                anyMatch(a -> a.getAuthority().equals(userRole.name()));
    }
}
