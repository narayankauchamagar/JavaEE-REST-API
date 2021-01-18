package com.f1soft.college.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Data
@NamedQueries({
    @NamedQuery(name="Student.findAll", query="FROM Student s"),
    @NamedQuery(name="Student.findById", query="FROM Student s WHERE s.id = :id"),
    @NamedQuery(name="Student.findByName", query="FROM Student s WHERE s.name = :name"),
})
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name is required")
    @Column
    private String name;

    @NotNull(message = "Date is required")
    @Column
    private LocalDate dob;

    @NotNull(message = "Standard is required")
    @Column
    private String standard;

    @Column
    @NotNull(message = "Section is required")
    private String section;


}
