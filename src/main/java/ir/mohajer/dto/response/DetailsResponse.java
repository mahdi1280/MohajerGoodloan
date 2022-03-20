package ir.mohajer.dto.response;

import java.io.Serializable;
import java.util.List;

public class DetailsResponse implements Serializable {

    private final UserResponse userResponse;
    private final List<UserLoanResponse> userLoans;

    public DetailsResponse(UserResponse userResponse, List<UserLoanResponse> userLoans) {
        this.userResponse = userResponse;
        this.userLoans = userLoans;
    }

    public static Builder builder(){
        return new Builder();
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public List<UserLoanResponse> getUserLoans() {
        return userLoans;
    }

    public static class Builder{

        private UserResponse userResponse;
        private List<UserLoanResponse> userLoans;

        private Builder() {
        }

        public Builder setUserResponse(UserResponse userResponse) {
            this.userResponse = userResponse;
            return this;
        }

        public Builder setUserLoans(List<UserLoanResponse> userLoans) {
            this.userLoans = userLoans;
            return this;
        }

        public DetailsResponse build(){
            return new DetailsResponse(userResponse,userLoans);
        }
    }
}
