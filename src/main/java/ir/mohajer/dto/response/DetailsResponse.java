package ir.mohajer.dto.response;

import ir.mohajer.model.UserLoan;
import ir.mohajer.model.Users;

import java.io.Serializable;
import java.util.List;

public class DetailsResponse implements Serializable {

    private final Users users;
    private final List<UserLoanResponse> userLoans;

    public DetailsResponse(Users users, List<UserLoanResponse> userLoans) {
        this.users = users;
        this.userLoans = userLoans;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Users getUsers() {
        return users;
    }

    public List<UserLoanResponse> getUserLoans() {
        return userLoans;
    }

    public static class Builder{

        private Users users;
        private List<UserLoanResponse> userLoans;

        private Builder() {
        }

        public Builder setUsers(Users users) {
            this.users = users;
            return this;
        }

        public Builder setUserLoans(List<UserLoanResponse> userLoans) {
            this.userLoans = userLoans;
            return this;
        }

        public DetailsResponse build(){
            return new DetailsResponse(users,userLoans);
        }
    }
}
