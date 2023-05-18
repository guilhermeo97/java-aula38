package com.t3.visitoraccess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t3.visitoraccess.entity.Visitor;
import com.t3.visitoraccess.repository.VisitorRepository;

@Service
public class VisitorService {
    
    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor newVisit(Visitor visitor){
        return visitorRepository.save(visitor);
    }

    public List<Visitor> returnAllVisitors(){
        List<Visitor> allVisitors = visitorRepository.findAll();
        return allVisitors;
    }
}
