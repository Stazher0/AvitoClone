package ru.zhulikov.spring.secondhandwebapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    private String role_name;

    public Role() {}

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public String getRoleName() {
        return role_name;
    }

    @Override
    public String getAuthority() {
        return role_name;
    }
}
