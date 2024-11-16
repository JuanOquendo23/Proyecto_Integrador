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
    private static List<String> movimientos = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("");
        System.out.println("Bienvenido a el sistema de inventario | Invexus");
        System.out.println("");

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
    public static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("");
            System.out.println("--------- Menu De Categorias ---------");
            System.out.println("");
            System.out.println("1. Agregar categorias");
            System.out.println("2. Editar categoria");
            System.out.println("3. Eliminar categoria");
            System.out.println("4. Listar Categorias");
            System.out.println("5. Seleccionar categoria");
            System.out.println("6. Salir del sistema");
            System.out.println("");
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
            System.out.println("");
            System.out.println("--------- Menu De Productos Para " + categorias.get(categoriaIndex) + "---------");
            System.out.println("");

            System.out.println("1. Agregar producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Listar  productos");
            System.out.println("5. Sumar unidades a un producto");
            System.out.println("6. Restar unidades a un producto");
            System.out.println("7. Cambiar precio a algun producto");
            System.out.println("8. Ver historial de movimientos ");
            System.out.println("9. MostrarPDF");
            System.out.println("10. Volver a el menu de categorias");
            System.out.println("");
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
                    sumarUnidadesProducto(categoriaIndex);
                    break;
                case 6:
                    restarUnidadesProducto(categoriaIndex);
                    break;
                case 7:
                    cambiarPrecioProducto(categoriaIndex);
                    break;
                case 8:
                    verMovimientos();
                    break;
                case 9:
                    mostrarPDF();
                    break;

                case 10:
                    System.out.println("Volviendo al menu de categorias...");
                    break;
                default:
                    System.out.println("Opcion invalida, por favor seleccione nuevamente");
            }

        } while (opcion != 10);

    }

    // Opciones de categorias = Metodos

    private static void seleccionarCategoria() {

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias disponibles.");
            return;
        }

        listarCategoria();
        while (true) {

            System.out.println("Seleccione el numero de la categoria: ");
            int indice = input.nextInt() - 1;
            input.nextLine();

            if (indice >= 0 && indice < categorias.size()) {
                System.out.println("Has seleccionado la categoria: " + categorias.get(indice));
                gestionarProductos(indice);
                break;

            } else {
                System.out.println("Indice invalido, vuelve a intentarlo.");
            }
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
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias para editar");
            return;
        }

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

        if (categorias.isEmpty()) {
            System.out.println("No hay categorias disponibles para eliminar");
            return;
        }

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

    private static void listarCategoria() {
        System.out.println("");
        System.out.println("--- Lista De Categorias ---");
        System.out.println("");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + categorias.get(i));
        }
        System.out.println("");
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias disponibles.");

        }

    }
    // Opciones de productos = metodos

    private static void agregarProducto(int categoriaIndex) {
        System.out.println("Ingrese el nombre del nuevo producto");
        String productoNombre = input.nextLine();

        System.out.println("Ingrese la cantidad: ");
        int cantidad = input.nextInt();

        System.out.println("Ingrese el precio");
        double precio = input.nextDouble();

        input.nextLine();

        System.out.println("Ingrese la fecha (dd/mm/yyyy): ");
        String fecha = input.nextLine();

        System.out.println("Ingresa una observacion: ");
        String observacion = input.nextLine();

        preciosPorCategorias.get(categoriaIndex).add(precio);

        productosPorCategorias.get(categoriaIndex).add(productoNombre);

        cantidadesPorCategorias.get(categoriaIndex).add(cantidad);

        movimientos.add("Agregar producto: " + productoNombre + " - Cantidad: " + cantidad + " - Fecha: " + fecha
                + " - Observacion: " + observacion);

        System.out.println("Producto agregado: " + productoNombre);
    }

    private static void editarProducto(int categoriaIndex) {
        if (productosPorCategorias.get(categoriaIndex).isEmpty()) {
            System.out.println("No hay productos disponibles para editar en esta categoria");
            return;

        }

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
        if (preciosPorCategorias.get(categoriaIndex).isEmpty()) {
            System.out.println("No hay productos para eliminar en esta categoria");
            return;

        }

        listarProductos(categoriaIndex);
        System.out.println("Seleccione el numero del producto a eliminar: ");
        int indice = input.nextInt() - 1;

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {
            String productoEliminado = productosPorCategorias.get(categoriaIndex).remove(indice);
            cantidadesPorCategorias.get(categoriaIndex).remove(indice);
            preciosPorCategorias.get(categoriaIndex).remove(indice);

            input.nextLine();

            System.out.println("Ingrese la fecha (dd/mm/yyyy): ");
            String fecha = input.nextLine();

            System.out.println("Ingrese una observacion: ");
            String observacion = input.nextLine();

            movimientos.add("Eliminar producto: " + productoEliminado + " - Fecha: " + fecha + " - Observacion: "
                    + observacion);

            System.out.println("Producto eliminado con exito: " + productoEliminado);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo.");
        }
    }

    private static void listarProductos(int categoriaIndex) {
        System.out.println("");
        System.out.println("--- Lista De Productos En " + categorias.get(categoriaIndex) + " ---");
        System.out.println("");

        List<String> productos = productosPorCategorias.get(categoriaIndex);

        List<Integer> cantidades = cantidadesPorCategorias.get(categoriaIndex);

        List<Double> precios = preciosPorCategorias.get(categoriaIndex);

        for (int i = 0; i < productos.size(); i++) {
            System.out.println(
                    "\t" + (i + 1) + ". " + productos.get(i) + " - cantidad: " + cantidades.get(i) + " - precio: "
                            + precios.get(i));
        }
        System.out.println("");
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en esta categoria");

        }

    }

    // Añadir y restar unidades, metodo para ver movimientos y por ultimo generar
    // pdf (control de salida)

    private static void sumarUnidadesProducto(int categoriaIndex) {
        if (productosPorCategorias.get(categoriaIndex).isEmpty()) {
            System.out.println("No hay productos disponibles para sumar en esta categoria");
            return;

        }

        listarProductos(categoriaIndex);
        System.out.println("Seleccione el numero del producto al que desea agregar unidades: ");
        int indice = input.nextInt() - 1;
        input.nextLine();

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {
            System.out.println("Ingrese la cantidad de unidades a agregar: ");
            int cantidad = input.nextInt();
            input.nextLine();

            int nuevaCantidad = cantidadesPorCategorias.get(categoriaIndex).get(indice) + cantidad;
            cantidadesPorCategorias.get(categoriaIndex).set(indice, nuevaCantidad);

            System.out.println("Ingrese la fecha (dd/mm/yyyy): ");
            String fecha = input.nextLine();

            System.out.println("Ingrese la observacion: ");
            String observacion = input.nextLine();

            movimientos.add("Sumar unidades: " + cantidad + " al producto: "
                    + productosPorCategorias.get(categoriaIndex).get(indice) + " - Nueva cantidad: " + nuevaCantidad
                    + " - Fecha: " + fecha + " - Observacion: " + observacion);

            System.out.println("Unidades agregadas exitosamente. Nueva cantidad: " + nuevaCantidad);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo");
        }

    }

    private static void restarUnidadesProducto(int categoriaIndex) {

        if (productosPorCategorias.get(categoriaIndex).isEmpty()) {
            System.out.println("No hay productos disponibles para restar en esta categorias");
            return;

        }

        listarProductos(categoriaIndex);
        System.out.println("Seleccione el numero del producto que desea restar unidades: ");
        int indice = input.nextInt() - 1;
        input.nextLine();

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {

            System.out.println("Ingrese la cantidad de unidades a restar: ");
            int cantidad = input.nextInt();
            input.nextLine();

            int cantidadActual = cantidadesPorCategorias.get(categoriaIndex).get(indice);

            int nuevaCantidad = cantidadActual - cantidad;

            if (nuevaCantidad < 0) {
                System.out.println("No se puede reducir por debajo de cero. cantidad actual: " + cantidadActual);
                return;

            }

            cantidadesPorCategorias.get(categoriaIndex).set(indice, nuevaCantidad);

            System.out.println("Ingrese la fecha (dd/mm/yyyy): ");
            String fecha = input.nextLine();

            System.out.println("Ingrese una observacion: ");
            String observacion = input.nextLine();

            movimientos.add("Restar unidades: " + cantidad + " al producto: "
                    + productosPorCategorias.get(categoriaIndex).get(indice) + " - Nueva cantidad: " + nuevaCantidad
                    + " - Fecha: " + fecha + " - Observacion: " + observacion);

            if (nuevaCantidad < 10) {
                System.out.println("Alerta: stock bajo. quedan menos de 10 unidades.");

            }

            System.out.println("unidades restadas exixtosamente. Nueva cantidad: " + nuevaCantidad);

        } else {
            System.out.println("Indice invalido, vuelve a intentarlo");
        }

    }

    private static void verMovimientos() {
        System.out.println("");
        System.out.println("-------- Historial de movimientos --------");
        System.out.println("");

        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");

        } else {
            for (String movimiento : movimientos) {
                System.out.println(movimiento);
            }
        }
    }

    // mostrar pdf
    private static void mostrarPDF() {

        System.out.println("Ingrese la fecha del pdf (cualquier formato es valido): ");
        String fechaInput = input.nextLine();

        System.out.println("============================================");
        System.out.println("          HISTORIAL DE MOVIMIENTOS          ");
        System.out.println("============================================");
        System.out.println("Fecha de generacion: " + fechaInput);
        System.out.println("-----------------------------------------------");

        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");

        } else {
            // enumeracion de los movimientos en el pdf
            for (int i = 0; i < movimientos.size(); i++) {
                System.out.println((i + 1) + "." + movimientos.get(i));
                System.out.println("-----------------------------------------------");
            }

        }

        System.out.println("============================================");
        System.out.println("        Fin Del Historial De Movimientos    ");
        System.out.println("============================================");
        System.out.println("");
        mostrarProductosBajoStock();

    }

    private static void mostrarProductosBajoStock() {
        System.out.println("==========================================");
        System.out.println("    Productos con menos de 10 unidades    ");
        System.out.println("==========================================");

        for (int i = 0; i < categorias.size(); i++) { // Recorre cada categoría
            String categoria = categorias.get(i);
            List<String> productos = productosPorCategorias.get(i);
            List<Integer> cantidades = cantidadesPorCategorias.get(i);

            for (int j = 0; j < productos.size(); j++) { // Recorre cada producto en la categoría
                int cantidad = cantidades.get(j);

                if (cantidad < 10) { // Filtra productos con cantidad menor a 10
                    System.out.println("Categoría: " + categoria +
                            ", Producto: " + productos.get(j) +
                            ", Cantidad: " + cantidad);
                    System.out.println("----------------------------------------------");
                }

            }

        }
    }

    // Agregamos un metodo que se pueda cambiar precio
    private static void cambiarPrecioProducto(int categoriaIndex) {
        if (productosPorCategorias.get(categoriaIndex).isEmpty()) {
            System.out.println("No hay productos disponibles para cambiar el precio");
            return;
            
        }

        listarProductos(categoriaIndex);
        System.out.println("Seleccione el numero del producto cuyo precio desea cambiar: ");
        int indice = input.nextInt() -1;
        input.nextLine();

        if (indice >= 0 && indice < productosPorCategorias.get(categoriaIndex).size()) {


            System.out.println("Ingrese el nuevo precio: ");
            double nuevoPrecio = input.nextDouble();
            input.nextLine();

            double precioAnterior = preciosPorCategorias.get(categoriaIndex).get(indice);
            preciosPorCategorias.get(categoriaIndex).set(indice, nuevoPrecio);

            System.out.println("Precio actualizado de " + productosPorCategorias.get(categoriaIndex).get(indice) + precioAnterior + " a " + nuevoPrecio);

            System.out.println("Ingrese la fecha (dd/mm/yyyy): ");
            String fecha = input.nextLine();

            System.out.println("Ingrese una observacion: ");
            String observacion = input.nextLine();

            movimientos.add("Cambios de precio: " + productosPorCategorias.get(categoriaIndex).get(indice) + " - Precio anterior: " +  precioAnterior + " - Nuevo precio: " + nuevoPrecio + " - Fecha: " + fecha + " - Observacion: " + observacion);
            
        } else {
            System.out.println("Indice invalido, vuelve a intentarlo");
        }

    }

}