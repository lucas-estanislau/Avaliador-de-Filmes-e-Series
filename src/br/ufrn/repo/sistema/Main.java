package br.ufrn.repo.sistema;

public class Main {
    public static void main(String[] args) {
        /*Menu menu = new Menu();
        menu.exibir();  */

        Sistema sistema = new Sistema();

        System.out.println("\n===== TESTE =====");

        for (var teste : sistema.getListaMidias()) {
            System.out.println(teste);
        }

        System.out.println("==============================================================================\n");
    }
}
    
