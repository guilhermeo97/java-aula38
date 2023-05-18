package com.t3.visitoraccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t3.visitoraccess.entity.Visitor;


public interface VisitorRepository extends JpaRepository<Visitor, Long>{
    
}
