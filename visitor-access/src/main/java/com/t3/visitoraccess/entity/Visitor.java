package com.t3.visitoraccess.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "visitor")
@Getter @Setter @NoArgsConstructor
public class Visitor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, length = 11)
    private String cpf;

    private String apartment;

    private String block;

    @Column(name = "entry_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;

    @Column(name = "date_created")
    @CreationTimestamp //salva o horario da criação
    private Date dateCreated;

}
