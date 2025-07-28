package dev.Zerphyis.botFinanceiro.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private  String name;

    @NotNull
    @Column(name = "salario")
    private BigDecimal salary;


    public User(RequestUser data){
        this.name= data.name();
        this.salary=data.salary();
    }
}
