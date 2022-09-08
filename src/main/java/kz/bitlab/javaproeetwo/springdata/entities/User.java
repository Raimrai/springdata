package kz.bitlab.javaproeetwo.springdata.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @Column(name = "commentary")
    private String comment;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "handled", columnDefinition = "boolean default false")
    private boolean handled;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Operator> operators;

}
