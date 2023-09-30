package anderk222.stock.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "public", name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0l;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)

    private List<Role> roles = new ArrayList<>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.addAll(roles);

        for (Role role : roles) {

            authorities.addAll(role.getAuthorities());

        }

        if (authorities.isEmpty())
            return new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

        return authorities;

    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.getName();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}