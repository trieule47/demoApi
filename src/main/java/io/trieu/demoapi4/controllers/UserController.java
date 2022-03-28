package io.trieu.demoapi4.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.trieu.demoapi4.models.ResponseObject;
import io.trieu.demoapi4.models.Users;
import io.trieu.demoapi4.repositories.UserRepository;

@RestController
@RequestMapping(path="/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    //get all user
    @GetMapping("")
    List<Users> findAll(){
        return repository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Users> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Query product successfully", foundProduct)
            );   
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("ERROR", "Query not found", "")
            );
        }
    }
    
    //insert user
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> InsertUser(@RequestBody Users newUser){
        Optional<Users> foundProduct = repository.findByName(newUser.getName().trim());
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failde","Username already taken","")
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Username have create successfully", repository.save(newUser))
            );
        }
    }

    //update user
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> UpdateUser(@PathVariable Long id, @RequestBody Users newUser){
        Users updateUser = repository.findById(id)
            .map( user -> {
                user.setAge(newUser.getAge());
                user.setName(newUser.getName());
                return repository.save(newUser);
            }).orElseGet(()->{
                newUser.setId(id);
                return repository.save(newUser);
            });
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok","Update successfully",updateUser)
        );
    }

    //delete user
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> DeleteUser(@PathVariable Long id){
        Optional<Users> deleteUser = repository.findById(id);
        if(deleteUser.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","ID have delete successfully", "")
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("faild","Id not found","")
            );
        }
       
    }
}
