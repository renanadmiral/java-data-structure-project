package br.com.letscode;

import br.com.letscode.controllers.FileDataManager;

public class App {
    public static void main(String[] args) {
        FileDataManager fm = new FileDataManager();

        fm.loadDataFromCsv("assets/operacoes.csv");
    }
}
