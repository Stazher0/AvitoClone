package ru.zhulikov.spring.secondhandwebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhulikov.spring.secondhandwebapp.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long Long);

}
