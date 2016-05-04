package shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl;

import java.io.Serializable;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.IPerson;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Name implements IPerson, Serializable {

    private Long id;
    private String name;
    private String middleName;
    private String surname;

    private Name(){}

    public Name(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    //Builder starts here
    public static class Builder{

        private Long id;
        private String name;
        private String middleName;
        private String surname;

        public Builder(){}

        public Builder(String name,String surname){
            this.name = name;
            this.surname = surname;
        }

        public Builder Id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }


        public Builder copy(Name name){
            this.id = name.id;
            this.name = name.name;
            this.middleName = name.middleName;
            this.surname = name.surname;
            return this;
        }

        public Name build(){
            return new Name(this);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s",name,surname);
    }
}
