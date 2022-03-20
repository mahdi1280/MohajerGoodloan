package ir.mohajer.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private final long id;
    private final String name;
    private final String nationalCode;

    @JsonCreator
    public UserResponse(long id, String name, String nationalCode) {
        this.id = id;
        this.name = name;
        this.nationalCode = nationalCode;
    }

    public static Builder builder(){
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public static class Builder{

        private long id;
        private String name;
        private String nationalCode;

        private Builder(){}

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public UserResponse build(){
            return new UserResponse(id,name,nationalCode);
        }
    }
}
