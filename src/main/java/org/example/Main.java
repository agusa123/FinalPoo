package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        Cliente nuevoCliente = new Cliente( 1,"Juan ","Perez", "Av. Siempre Viva 123", "123456789", "juanexample.com");
        ClienteDAO clienteDAO = new ClienteDAO();

        clienteDAO.registrarCliente(nuevoCliente);
    }
}