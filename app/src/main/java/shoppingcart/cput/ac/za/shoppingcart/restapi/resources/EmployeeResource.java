package shoppingcart.cput.ac.za.shoppingcart.restapi.resources;

import java.io.Serializable;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class EmployeeResource implements Serializable {

    private Name name;
    private User user;

    private EmployeeResource() {
    }

    public EmployeeResource(Builder builder) {
        this.name = builder.name;
        this.user = builder.user;
    }

    public Name getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    //Builder starts here
    public static class Builder {
        private Name name;
        private User user;

        public Builder() {
        }

        public Builder(Name name, User user) {
            this.name = name;
            this.user = user;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(EmployeeResource employee) {
            this.name = employee.name;
            this.user = employee.user;
            return this;
        }

        public EmployeeResource build() {
            return new EmployeeResource(this);
        }
    }
}
