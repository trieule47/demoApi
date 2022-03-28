package io.trieu.demoapi4.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)  
    private Long id;
    
    //
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="age", nullable=false)
    private String age;

    public Users() {
    }
    public Users(String name, String age) {
        this.name = name;
        this.age = age;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }


    
}
