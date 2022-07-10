package ir.mohajer.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class UserProperty extends BaseEntity {

    private LocalDateTime createdAt;
    private int price;
    private Users user;

    public UserProperty(int price, Users user) {
        this.price = price;
        this.user = user;
    }

    public UserProperty() {
    }

    public static Builder builder() {
        return new Builder();
    }

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public static class Builder {

        private int price;
        private Users user;

        private Builder() {
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder user(Users user) {
            this.user = user;
            return this;
        }

        public UserProperty build() {
            return new UserProperty(price, user);
        }
    }
}
