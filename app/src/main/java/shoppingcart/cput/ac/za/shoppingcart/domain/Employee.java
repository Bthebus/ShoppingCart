package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Name;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Employee implements Serializable {

    private Long id;
    private Name name;
    private User user;

    private Employee(){
    }

    public Employee(Builder builder){
        this.name = builder.name;
        this.user = builder.user;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    //Builder starts here
    public static class Builder{
        private Long id;
        private Name name;
        private User user;

        public Builder(){}

        public Builder(Name name, User user){
            this.name=name;
            this.user= user;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder name(Name name){
            this.name = name;
            return this;
        }

        public Builder user(User user)
        {
            this.user = user;
            return this;
        }

        public Builder copy(Employee employee){
            this.id = employee.id;
            this.name = employee.name;
            this.user = employee.user;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
