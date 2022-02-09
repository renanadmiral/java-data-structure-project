package br.com.letscode.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.builder.CompareToBuilder;

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
        return new CompareToBuilder()
                .append(this.date, anotherOperation.date)
                .append(this.teller, anotherOperation.teller)
                .append(this.operationType, anotherOperation.operationType)
                .append(this.value, anotherOperation.value)
                .toComparison();
    }
}
