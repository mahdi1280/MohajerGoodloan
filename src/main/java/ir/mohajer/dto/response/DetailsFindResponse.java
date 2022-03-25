package ir.mohajer.dto.response;

import java.io.Serializable;
import java.util.List;

public class DetailsFindResponse implements Serializable {
    
    private final Long userLoanId;
    private final List<InstallmentsResponse> installmentsResponses;

    public DetailsFindResponse(Long userLoanId, List<InstallmentsResponse> installmentsResponses) {
        this.userLoanId = userLoanId;
        this.installmentsResponses = installmentsResponses;
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public Long getUserLoanId() {
        return userLoanId;
    }

    public List<InstallmentsResponse> getInstallmentsResponses() {
        return installmentsResponses;
    }
    
    public static class Builder{

        private Long userLoanId;
        private List<InstallmentsResponse> installmentsResponses;
        
        private Builder(){}

        public Builder setUserLoanId(Long userLoanId) {
            this.userLoanId = userLoanId;
            return this;
        }

        public Builder setInstallmentsResponses(List<InstallmentsResponse> installmentsResponses) {
            this.installmentsResponses = installmentsResponses;
            return this;
        }
        
        public DetailsFindResponse build(){
            return new DetailsFindResponse(userLoanId,installmentsResponses);
        }
    }
}
