package com.example.demo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name = "fable")
public class TestObject {
    @Id
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
}
