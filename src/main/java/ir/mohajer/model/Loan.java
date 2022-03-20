package ir.mohajer.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = Schema.SCHEMA_NAME)
public class Loan extends BaseEntity {

    private String name;
    private int amount;

    public Loan(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Loan() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static class Builder{

        private String name;
        private int amount;

        private Builder(){}

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public Loan build(){
            return new Loan(name,amount);
        }
    }
}
