package ru.zhulikov.spring.secondhandwebapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@Entity
public class Advt {

    public Advt() {}

    public Advt(String name, int price ,String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private String name;
    private int price;
    private String description;

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
