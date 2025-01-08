package ru.zhulikov.spring.secondhandwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.zhulikov.spring.secondhandwebapp.models.Advt;

import java.util.List;

public interface AdvtRepository extends CrudRepository<Advt, Long> {

    default List<Advt> findByTitle(String title) {return null;}

    default List<Advt> findByPrice(int price) {return null;}

    default List<Advt> findByTitleAndPrice(String title,int price) {return null;}
}
