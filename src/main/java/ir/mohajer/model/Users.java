package ir.mohajer.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = Schema.SCHEMA_NAME,name = "APPLICATION_USER")
public class Users extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String nationalCode;
    private String name;

    public Users(String username, String password, String email, String nationalCode, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nationalCode = nationalCode;
        this.name = name;
    }

    public Users() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{

        private String username;
        private String password;
        private String email;
        private String nationalCode;
        private String name;

        private Builder(){}

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Users build(){
            return new Users(username,password,email,nationalCode,name);
        }
    }
}
