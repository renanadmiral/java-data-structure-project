package br.com.letscode.controllers;

import br.com.letscode.models.BankAccount;
import br.com.letscode.models.BankingOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileDataManager {
    private final Set<BankAccount> accounts = new HashSet<>();

    public void loadDataFromCsv(String path) {
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            br.readLine(); // to skip first line in csv file
            while ((line = br.readLine()) != null) {
                String[] operation = line.split(splitBy);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                Date date = dateFormat.parse(operation[0]);
                String accountId = operation[1];
                String bankName = operation[2];
                String agencyId = operation[3];
                String accountNumber = operation[4];
                String teller = operation[5];
                String operationType = operation[6];
                BigDecimal operationValue = new BigDecimal(operation[7]);

                BankAccount currentAccount = new BankAccount(accountId, bankName, agencyId, accountNumber);
                BankingOperation currentOperation = new BankingOperation(date, teller, operationType, operationValue);

                this.accounts.add(currentAccount);

                for (BankAccount account: this.accounts) {
                    if (account.equals(currentAccount)) {
                        account.addOperation(currentOperation);
                    }
                }
            }

            this.doAccountsOperationsCalculation();
        } catch (IOException | ParseException e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public void doAccountsOperationsCalculation() {
        for (BankAccount account : this.accounts) {
            account.doOperationsCalculation();
        }
    }
}
