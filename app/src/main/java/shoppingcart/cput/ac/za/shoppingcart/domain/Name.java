package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Name implements IPerson, Serializable {
    private String name;
    private String middleName;
    private String surname;

    private Name(){}

    public Name(Builder builder){
        this.name = builder.name;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
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

        private String name;
        private String middleName;
        private String surname;

        public Builder(){}

        public Builder(String name,String surname){
            this.name = name;
            this.surname = surname;
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
