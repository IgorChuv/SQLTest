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
@Table(name = "table_name1")
public class TestObject2 {
    @Id
    long colid;
    @Column(name = "colname")
    String colname;
    @Column(name = "colsurname")
    String colsurname;
}
