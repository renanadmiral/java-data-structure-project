package br.com.letscode.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

@Setter
@Getter
public class BankAccount {
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
}
