package ir.mohajer.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class ExtraExpenses extends BaseEntity {

    private int price;
    private String details;
    private LocalDateTime createdAt;

    public ExtraExpenses(int price, String details) {
        this.price = price;
        this.details = details;
    }

    public ExtraExpenses() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @CreationTimestamp
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder{

        private int price;
        private String details;

        private Builder(){}

        public Builder price(int price){
            this.price=price;
            return this;
        }

        public Builder builder(String details){
            this.details=details;
            return this;
        }

        public ExtraExpenses build(){
            return new ExtraExpenses(price,details);
        }
    }
}
