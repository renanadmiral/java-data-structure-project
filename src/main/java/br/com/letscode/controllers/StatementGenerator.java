package br.com.letscode.controllers;

import br.com.letscode.models.BankAccount;
import br.com.letscode.models.BankingOperation;
import br.com.letscode.models.OperationTypes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Set;


public final class StatementGenerator {

    public static void generateStatements(Set<BankAccount> accounts) {
        try {
            for (BankAccount account : accounts) {
                File statementsDirectory = new File("assets/statements");
                if (!statementsDirectory.exists()){
                    statementsDirectory.mkdirs();
                }

                File statementFile = new File(statementsDirectory + "/" + account.getId() + ".txt");

                if (statementFile.createNewFile()) {
                    System.out.println("File created: " + statementFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
                writeStatement(account, statementFile);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void writeStatement (BankAccount account, File statement) {
        try {
            PrintWriter writer = new PrintWriter(statement);
            String horizontalLine = "----------------------------------------------------------------";

            writer.printf("Banco "+ account.getBankName() +"\n");
            writer.printf("Agência: ... "+ account.getAgencyId() +"\n");
            writer.printf("Conta: ..... "+ account.getAccountNumber() +"\n");
            writer.println(horizontalLine);

            writer.printf("Data \t\t\t");
            writer.printf("Tipo \t\t");
            writer.printf("Valor \t\t");
            writer.printf("Operador \n");
            writer.println(horizontalLine);

            for (BankingOperation operation : account.getOperations()) {
                String datePattern = "dd-MM-yy HH:mm:ss";
                SimpleDateFormat df = new SimpleDateFormat(datePattern);
                writer.printf(df.format(operation.getDate()) + "\t");

                boolean isWithdrawal = operation.getOperationType().equals(OperationTypes.SAQUE.toString());
                String extraSeparator = isWithdrawal ? "\t" : "";
                String operator = isWithdrawal ? "-" : "+";
                writer.printf(operation.getOperationType() + "\t" + extraSeparator);
                writer.write(operator + operation.getValue() + "\t\t");
                writer.printf(operation.getTeller()+"\t\n");
                writer.println(horizontalLine);
            }
            writer.printf("\nSaldo: ...............................\t");
            writer.printf(account.getBalance().toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
