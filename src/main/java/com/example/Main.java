package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String username = "Admin";
        String password = "12345";

        System.out.println("Ingrese el usuario: ");
        String usernameinput = input.nextLine();

        System.out.println("Ingrese la contraseña: ");
        String passwordinput = input.nextLine();

        boolean correcto = false;
        while (correcto == false) {

            if (usernameinput.equals(username) && passwordinput.equals(password)) {
                System.out.println("Inicio de sesion exitoso");
                correcto = true;

            } else {
                System.out.println("Error de inicio de sesion,Vuelve a intentarlo.");
                System.out.println("Ingrese usuario:");
                usernameinput = input.nextLine();
                System.out.println("Ingrese contraseña: ");
                passwordinput = input.nextLine();
            }

        }
        input.close();
    }

    

}