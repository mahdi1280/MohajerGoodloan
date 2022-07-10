package ir.mohajer.dto.response;

import ir.mohajer.model.ExtraExpenses;

import java.io.Serializable;
import java.util.List;

public class ExtraExpensesResponse implements Serializable {

    private transient final List<ExtraExpenses> extraExpenses;
    private final int allPrice;

    public ExtraExpensesResponse(List<ExtraExpenses> extraExpenses, int allPrice) {
        this.extraExpenses = extraExpenses;
        this.allPrice = allPrice;
    }

    public List<ExtraExpenses> getExtraExpenses() {
        return extraExpenses;
    }

    public int getAllPrice() {
        return allPrice;
    }
}
