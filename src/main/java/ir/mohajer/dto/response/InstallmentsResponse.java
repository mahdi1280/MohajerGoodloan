package ir.mohajer.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

public class InstallmentsResponse implements Serializable {

    private final Long id;
    private final Integer amount;
    private final LocalDate createdDate;

    public InstallmentsResponse(Long id, Integer amount, LocalDate createdDate) {
        this.id = id;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public static class Builder{

        private Long id;
        private Integer amount;
        private LocalDate createdDate;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        
        public InstallmentsResponse build(){
            return new InstallmentsResponse(id,amount,createdDate);
        }
    }
}
