package ir.mohajer.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserRequest implements Serializable {

    private final String name;
    private final String username;
    private final String nationalCode;
    private final String password;
    private final String rePassword;
    private final String email;

    @JsonCreator
    public UserRequest(String name,String username, String nationalCode, String password, String rePassword,String email) {
        this.name = name;
        this.username=username;
        this.nationalCode = nationalCode;
        this.password = password;
        this.rePassword = rePassword;
        this.email=email;
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    @NotNull
    @NotBlank
    public String getUsername() {
        return username;
    }

    @NotNull
    @NotBlank
    public String getNationalCode() {
        return nationalCode;
    }

    @NotNull
    @NotBlank
    public String getPassword() {
        return password;
    }

    @NotNull
    @NotBlank
    public String getRePassword() {
        return rePassword;
    }

    @NotNull
    @NotBlank
    public String getEmail() {
        return email;
    }
}
