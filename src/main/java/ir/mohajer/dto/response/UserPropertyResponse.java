package ir.mohajer.dto.response;

import ir.mohajer.model.UserProperty;

import java.io.Serializable;
import java.util.List;

public class UserPropertyResponse implements Serializable {

    private final long id;
    private final String username;
    private final List<UserProperty> userProperties;

    public UserPropertyResponse(long id, String username, List<UserProperty> userProperties) {
        this.id = id;
        this.username = username;
        this.userProperties = userProperties;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<UserProperty> getUserProperties() {
        return userProperties;
    }

    public static class Builder {

        private long id;
        private String username;
        private List<UserProperty> userProperties;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder userProperties(List<UserProperty> userProperties) {
            this.userProperties = userProperties;
            return this;
        }

        public UserPropertyResponse build() {
            return new UserPropertyResponse(id, username, userProperties);
        }
    }
}
