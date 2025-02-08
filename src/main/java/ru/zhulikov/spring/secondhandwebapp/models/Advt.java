package ru.zhulikov.spring.secondhandwebapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@Entity
@Table(name = "advt")
public class Advt {

    private String name;
    private int price;
    private String description;
    private String imageName;

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Advt(String name, int price ,String description,String imageName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
    }

    public Advt() {

    }

    @Override
    public String toString() {
        return "Advt{" +
                "name='" + name + '\'' +
                ", price=" + price +

                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                ", id=" + id +
                '}';
    }
}
