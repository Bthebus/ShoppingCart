package shoppingcart.cput.ac.za.shoppingcart.domain.Personal;

import android.support.annotation.Size;

import java.io.Serializable;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class User implements Serializable {



    @Size(min=5, max = 10)
    private String username;

    @Size(min=5, max = 10)
    private String password;

    private User() {
    }

    public User(Builder builder){

        this.username = builder.username;
        this.password = builder.password;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //builder starts here
    public static class Builder{

        private Long id;
        private String username;
        private String password;

        public Builder()
        {}

        public Builder(String username, String password)
        {
            this.username = username;
            this.password = password;
        }

        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder copy(User user){
            this.username = user.username;
            this.password = user.password;
            return this;
        }

        public User build()
        {
            return  new User(this);
        }
    }
}
