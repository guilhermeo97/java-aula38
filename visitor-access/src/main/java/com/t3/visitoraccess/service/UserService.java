package com.t3.visitoraccess.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.t3.visitoraccess.entity.User;
import com.t3.visitoraccess.entity.Visitor;
import com.t3.visitoraccess.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser(User user){
        user.setRoles("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void addVisitor(String username, Visitor visitor){
        Optional<User> optUser = userRepository.findByUsername(username);

        if(optUser.isPresent()){
            //Pega o usuario no banco
            User user = optUser.get();
            //setamos as relaçoes do usuario e do visitante
            user.addNewVisitor(visitor);
            //salvamos o usuario e consequentemente salvamos o visitante devido ao cascade.ALL
            userRepository.save(user);
        }
        
    }

    public Set<Visitor> returnAllVisitorsFromUser(String username){
        Optional<User> optUser = userRepository.findByUsername(username);
        
        if(optUser.isPresent()){
            User user = optUser.get();
            return user.getMyVisitors();
        } else { 
            return null;
        }
    }

    @Transactional
    public void removeVisitor(long id, String username){
        Optional<User> optUser = userRepository.findByUsername(username);

        if(optUser.isPresent()){
            //Pega o usuario no banco
            User user = optUser.get();
            // se o id do visitante dfor igual o id passado, ele é removido do Set
            user.getMyVisitors().removeIf(visitor -> visitor.getId() == id);
            //salvamos o usuario e consequentemente removemos o visitante devido ao cascade.ALL
            userRepository.save(user);
    }
}
}
