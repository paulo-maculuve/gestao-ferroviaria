package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.UserNotFundException;
import com.estacao.ferroviaria.model.Users;
import com.estacao.ferroviaria.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> userList(){
        return (List<Users>) userRepository.findAll();
    }

    public void save(Users user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public Users getUser(Long id) throws  UserNotFundException{
        Optional<Users> user= userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFundException("Could not find any users with ID" + id);
    }

    public void deleteUser(Long id) throws UserNotFundException {
        Long idUser= userRepository.countById(id);
        if(idUser == null || idUser == 0){
            throw new UserNotFundException("Could not find any users with ID" + id);
        }
       userRepository.deleteById(id);
    }
}
