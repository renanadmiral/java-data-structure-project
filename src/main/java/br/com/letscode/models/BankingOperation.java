package br.com.letscode.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
public class BankingOperation implements Comparable<BankingOperation> {
    private Date date;
    private String teller;
    private String operationType;
    private BigDecimal value;

    @Override
    public int compareTo(BankingOperation anotherOperation) {
        if (this.date.before(anotherOperation.date)) {
            return -1;
        }
        if (this.date.after(anotherOperation.date)){
            return +1;
        }
        return 0;
    }
}
