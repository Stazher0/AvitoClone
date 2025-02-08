package ru.zhulikov.spring.secondhandwebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhulikov.spring.secondhandwebapp.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}

//ЗАПРОС ЕСЛИ ПОНАДОБИТСЯ
//@Query(value = "SELECT nextval(pg_get_serial_sequence('t_user', 'id'))", nativeQuery = true)
//Long getNextId();