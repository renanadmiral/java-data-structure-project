package br.com.letscode.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

//remover getter depois que finalizar
@Getter
@ToString
public class BankAccount{
    private String id;
    private String bankName;
    private String agencyId;
    private String accountNumber;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private final Set<BankingOperation> operations = new TreeSet<>();

    public BankAccount(String id, String bankName, String agencyId, String accountNumber) {
        this.id = id;
        this.bankName = bankName;
        this.agencyId = agencyId;
        this.accountNumber = accountNumber;
    }

    public void addOperation(BankingOperation operation) {
        this.operations.add(operation);
    }

    public void doOperationsCalculation () {
        for (BankingOperation operation : this.operations) {
            if (operation.getOperationType().equals(operationTypes.SAQUE.toString())) {
                this.balance = this.balance.subtract(operation.getValue());
            } else {
                this.balance = this.balance.add(operation.getValue());
            }
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(this.id).
                append(this.bankName).
                append(this.agencyId).
                append(this.accountNumber).
                append(this.balance).
                append(this.operations).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankAccount))
            return false;
        if (((BankAccount) obj).id.equals(this.id))
            return true;

        BankAccount rhs = (BankAccount) obj;
        return new EqualsBuilder().
                append(this.id, rhs.id).
                append(this.bankName, rhs.bankName).
                append(this.agencyId, rhs.agencyId).
                append(this.accountNumber, rhs.accountNumber).
                append(this.balance, rhs.balance).
                append(this.operations, rhs.operations).
                isEquals();
    }
}
