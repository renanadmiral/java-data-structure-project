package br.com.letscode.controllers;

import br.com.letscode.models.BankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class FileManager {
    private final Set<BankAccount> accounts = new HashSet<>();

    public void loadDataFromCsv(String path) {
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null)
            {
                String[] operation = line.split(splitBy);
                //accounts.add()
            }
        } catch (IOException e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
}
