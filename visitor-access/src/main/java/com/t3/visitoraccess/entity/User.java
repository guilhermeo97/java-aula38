package com.t3.visitoraccess.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(unique = true)
    private String username;

    private String password;
    
    private String roles;

    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Visitor> myVisitors = new HashSet<>();

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void addNewVisitor(Visitor newVisitor) {
        newVisitor.setResident(this);
        myVisitors.add(newVisitor);
    }
}
