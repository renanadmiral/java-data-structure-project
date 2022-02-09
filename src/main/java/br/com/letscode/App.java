package br.com.letscode;

import br.com.letscode.controllers.FileManager;

public class App {
    public static void main(String[] args) {
        FileManager fm = new FileManager();

        fm.loadDataFromCsv("assets/operacoes.csv");
    }
}
