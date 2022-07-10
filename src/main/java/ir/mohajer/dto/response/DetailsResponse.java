package ir.mohajer.dto.response;

import java.io.Serializable;
import java.util.List;

public class DetailsResponse implements Serializable {

    private final UserResponse userResponse;
    private final List<UserLoanResponse> userLoans;
    private final List<LoanResponse> loanResponses;
    private final Integer allPrice;

    public DetailsResponse(UserResponse userResponse, List<UserLoanResponse> userLoans, List<LoanResponse> loanResponses, Integer allPrice) {
        this.userResponse = userResponse;
        this.userLoans = userLoans;
        this.loanResponses = loanResponses;
        this.allPrice = allPrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public List<UserLoanResponse> getUserLoans() {
        return userLoans;
    }

    public List<LoanResponse> getLoanResponses() {
        return loanResponses;
    }

    public Integer allPrice() {
        return allPrice;
    }

    public static class Builder {

        private UserResponse userResponse;
        private List<UserLoanResponse> userLoans;
        private List<LoanResponse> loanResponses;
        private Integer allPrice;

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

        public Builder setLoanResponses(List<LoanResponse> loanResponses) {
            this.loanResponses = loanResponses;
            return this;
        }

        public Builder setAllPrice(Integer allPrice) {
            this.allPrice = allPrice;
            return this;
        }

        public DetailsResponse build() {
            return new DetailsResponse(userResponse, userLoans, loanResponses, allPrice);
        }
    }
}
