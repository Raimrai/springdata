package kz.bitlab.javaproeetwo.springdata.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operators")
@Getter
@Setter
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "operator_name")
    private String name;

    @Column(name = "operator_surname")
    private String surname;

    @Column(name = "department")
    private String department;


}
