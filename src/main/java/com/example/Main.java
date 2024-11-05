package com.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    private static List<String> categorias = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static List<List<String>> productosPorCategorias = new ArrayList<>();
    private static List<List<Integer>> cantidadesPorCategorias = new ArrayList<>();
    private static List<List<Double>> preciosPorCategorias = new ArrayList<>();


    public static void main(String[] args) {
        

     iniciarSesion();
}

// Metodo = Inicio de sesion
    private static void iniciarSesion() {

        String username = "Admin";
        String password = "12345";

        System.out.println("Ingrese el usuario: ");
        String usernameinput = input.nextLine();

        System.out.println("Ingrese la contraseña: ");
        String passwordinput = input.nextLine();

        while (true) {

            if (usernameinput.equals(username) && passwordinput.equals(password)) {
                System.out.println("Inicio de sesion exitoso");
               break;

            } else {
                System.out.println("Error de inicio de sesion,Vuelve a intentarlo.");
                System.out.println("Ingrese usuario:");
                usernameinput = input.nextLine();
                System.out.println("Ingrese contraseña: ");
                passwordinput = input.nextLine();
            }
      
      
        }
        mostrarMenu();
    }


    // Menu de categorias
    public  static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("--------- Menu De Categorias ---------");
            System.out.println("1. Agregar categorias");
            System.out.println("2. Editar categoria");
            System.out.println("3. Eliminar categoria");
            System.out.println("4. Listar Categorias");
            System.out.println("5. Seleccionar categoria");
            System.out.println("6. Salir del sistema");

            System.out.println("Seleccione una opcion: ");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    agregarCategoria();
                    break;

                case 2:
                    editarCategoria();
                    break;

                case 3:
                    eliminarCategoria();
                    break;

                case 4:
                    listarCategoria();
                    break;

                case 5:
                    seleccionarCategoria();
                    break;
                case 6:
                    System.out.println("Saliendo..");
                    break;

                default:
                    System.out.println("Opcion invalida por favor seleccione nuevamente.");
            }
        } while (opcion != 6);
    }

    // Gestion de productos

    private static void gestionarProductos(int categoriaIndex) {
        int opcion;
        do {
            System.out.println("--------- Menu De Productos Para " + categorias.get(categoriaIndex) + "---------");

            System.out.println("1. Agregar producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Listar  productos");
            System.out.println("5. Volver a el menu de categorias");
            System.out.println("Seleccione una opcion: ");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {

                case 1:
                    agregarProducto(categoriaIndex);
                    break;
                case 2:
                    editarProducto(categoriaIndex);
                    break;
                case 3:
                    eliminarProducto(categoriaIndex);
                    break;
                case 4:
                    listarProductos(categoriaIndex);
                    break;
                case 5:
                    System.out.println("Volviendo al menu de categorias...");
                    break;

                default:
                    System.out.println("Opcion invalida, por favor seleccione nuevamente");

            }

        } while (opcion != 5);

    }

    // Opciones de categorias = Metodos

    private static void seleccionarCategoria() {
        listarCategoria();
        System.out.println("Seleccione el numero de la categoria: ");
        int indice = input.nextInt() - 1;
        input.nextLine();

        if (indice >= 0 && indice < categorias.size()) {
            System.out.println("Has seleccionado la categoria: " + categorias.get(indice));
            gestionarProductos(indice);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo.");
        }
    }

    private static void agregarCategoria() {
        System.out.println("Ingrese el nombre de la nueva categorias: ");
        String categoria = input.nextLine();
        categorias.add(categoria);
        productosPorCategorias.add(new ArrayList<>());
        cantidadesPorCategorias.add(new ArrayList<>());
        preciosPorCategorias.add(new ArrayList<>());

        System.out.println("Categoria agregada: " + categoria);
    }

    private static void editarCategoria() {
        listarCategoria();
        System.out.println("Seleccione el numero de la categoria a editar: ");
        int indice = input.nextInt() - 1;
        input.nextLine();

        if (indice >= 0 && indice < categorias.size()) {
            System.out.println("Ingrese el nuevo nombre de la categoria: ");
            String nuevaCategoria = input.nextLine();
            categorias.set(indice, nuevaCategoria);
            System.out.println("Categorias editada con exito: " + nuevaCategoria);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo.");
        }

    }

    private static void eliminarCategoria() {
        listarCategoria();
        System.out.println("Seleccione el numero de la categoria a eliminar: ");
        int indice = input.nextInt() - 1;

        if (indice >= 0 && indice < categorias.size()) {
            String categoriaEliminada = categorias.remove(indice);
            productosPorCategorias.remove(indice);
            cantidadesPorCategorias.remove(indice);
            preciosPorCategorias.remove(indice);

            System.out.println("categoria eliminada: " + categoriaEliminada);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo");
        }

    }

    private  static void listarCategoria() {
        System.out.println("--- Lista De Categorias ---");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias disponibles.");

        }

    }
    // Opciones de productos = metodos

    private  static void agregarProducto(int categoriaIndex) {
        System.out.println("Ingrese el nombre del nuevo producto");
        String productoNombre = input.nextLine();

        System.out.println("Ingrese la cantidad: ");
        int cantidad = input.nextInt();

        System.out.println("Ingrese el precio");
        double precio = input.nextDouble();

        input.nextLine();

        preciosPorCategorias.get(categoriaIndex).add(precio);

        productosPorCategorias.get(categoriaIndex).add(productoNombre);

        cantidadesPorCategorias.get(categoriaIndex).add(cantidad);

        System.out.println("Producto agregado: " + productoNombre);
    }

    private static void editarProducto(int categoriaIndex) {
        listarProductos(categoriaIndex);
        System.out.println("Seleccion el numero del producto a editar: ");
        int indice = input.nextInt() - 1;
        input.nextLine();

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {

            System.out.println("Ingrese el nombre del producto: ");
            String nuevoNombre = input.nextLine();

            System.out.println("Ingrese la nueva cantidad: ");
            int nuevaCantidad = input.nextInt();

            System.out.println("ingrese el nuevo precio: ");
            Double nuevoPrecio = input.nextDouble();

            input.nextLine();

            productosPorCategorias.get(categoriaIndex).set(indice, nuevoNombre);

            cantidadesPorCategorias.get(categoriaIndex).set(indice, nuevaCantidad);

            preciosPorCategorias.get(categoriaIndex).set(indice, nuevoPrecio);

            System.out.println("Producto editado con exito: " + nuevoNombre);
        } else {
            System.out.println("Indice invalido, vuelve a intentarlo");
        }
    }

    private static void eliminarProducto(int categoriaIndex) {
        listarProductos(categoriaIndex);
        System.out.println("Seleccione el numero del producto a eliminar: ");
        int indice = input.nextInt() - 1;

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {
            String productoEliminado = productosPorCategorias.get(categoriaIndex).remove(indice);
            cantidadesPorCategorias.get(categoriaIndex).remove(indice);
            preciosPorCategorias.get(categoriaIndex).remove(indice);

            System.out.println("Producto eliminado con exito: " + productoEliminado);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo.");
        }
    }

    private static void listarProductos(int categoriaIndex) {
        System.out.println("--- Lista De Productos En " + categorias.get(categoriaIndex) + " ---");

        List<String> productos = productosPorCategorias.get(categoriaIndex);

        List<Integer> cantidades = cantidadesPorCategorias.get(categoriaIndex);

        List<Double> precios = preciosPorCategorias.get(categoriaIndex);

        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i) + " - cantidad: " + cantidades.get(i) + " - precio: "
                    + precios.get(i));
        }
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en esta categoria");

        }
    }
}
