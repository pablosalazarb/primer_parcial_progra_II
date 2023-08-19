import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Creamos un objeto pelicula con la informacion de la pelicula
        Pelicula pelicula = new Pelicula("Megalodon 2", 120, 13, "Director X");
        //Instanciamos un objeto cine con el costo y cantidad de asientos de la sala
        Cine cine = new Cine(pelicula, 10.0, 8, 9);
        
        //Creamos un scanner para leer la informacion
        Scanner scanner = new Scanner(System.in);

        //Comenzamos la captura de datos
        System.out.print("\nIngrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("\nIngrese la edad: ");
        int edad = scanner.nextInt();

        if (edad >= pelicula.edadMinima) {
                    cine.mostrarInfoCine();
                    System.out.print("\nIngrese el dinero: ");
                    double dinero = scanner.nextDouble();
                    if(dinero < cine.precioEntrada){
                        System.out.print("\nLo sentimos, no alcanza para comprar entrada...");
                    }else{
                        System.out.print("Ingrese el numero de asiento: ");
                        String asiento = scanner.nextLine();
                        //Agregue un segundo scanner pues en el primero se va un caracter vacio
                        String input = scanner.nextLine();
                        // Encontrar la posicion donde comienza el numero
                        int index = 0;
                        while (index < input.length() && !Character.isDigit(input.charAt(index))) {
                            index++;
                        }
                        // Verificar si se encontro un numero
                        if (index < input.length()) {
                            // Extraer la letra y el numero
                            String letraString = input.substring(0, index);
                            String numeroString = input.substring(index);
                        
                            // Verificar si numeroString es un numero valido
                            if (!numeroString.isEmpty()) {
                                // Convertir el numero en un int
                                int numero = Integer.parseInt(numeroString);
                                
                                //Convertir letra char
                                char letra = letraString.charAt(0);
                                
                                cine.ocuparAsiento(letra, numero);
                                System.out.print("\nBienvenido al cine: " + nombre + " Tu vuelto es: " + (dinero - cine.precioEntrada) + " tu asiento es: " + input + " que lo disfrutes! \n");
                                cine.mostrarInfoCine();
                            } else {
                                System.out.println("Numero de asiento invalido.");
                            }
                        } else {
                            System.out.println("Entrada invalida.");
                        }
                    }
                } else {
                    System.out.print("\n--- ALERTA: El cliente no tiene la edad minima para ver esta pelicula! ---");
                }
    }
}

class Pelicula {
    String titulo;
    int duracion;
    int edadMinima;
    String director;

    public Pelicula(String titulo, int duracion, int edadMinima, String director) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.edadMinima = edadMinima;
        this.director = director;
    }
}

class Espectador {
    String nombre;
    int edad;
    double dinero;

    public Espectador(String nombre, int edad, double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
    }
}

class Asiento {
    char letra;
    int numero;
    boolean ocupado;

    public Asiento(char letra, int numero) {
        this.letra = letra;
        this.numero = numero;
        this.ocupado = false;
    }
}

class Cine {
    Pelicula peliculaActual;
    double precioEntrada;
    Asiento[][] asientos;

    public Cine(Pelicula peliculaActual, double precioEntrada, int filas, int columnas) {
        this.peliculaActual = peliculaActual;
        this.precioEntrada = precioEntrada;
        this.asientos = new Asiento[filas][columnas];

        // Inicializar los asientos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                char letra = (char) ('A' + j);
                int numero = filas - i;
                asientos[i][j] = new Asiento(letra, numero);
            }
        }
    }
    
    public void ocuparAsiento(char letra, int numero) {
        int fila = asientos.length - numero;
        int columna = letra - 'A';

        if (fila >= 0 && fila < asientos.length && columna >= 0 && columna < asientos[0].length) {
            if (!asientos[fila][columna].ocupado) {
                asientos[fila][columna].ocupado = true;
                System.out.println("\nEl asiento " + letra + numero + " ha sido reservado.");
            } else {
                System.out.println("\nEl asiento " + letra + numero + " ya esta reservado.");
            }
        } else {
            System.out.println("\nAsiento invalido.");
        }
    }

    public void mostrarInfoCine() {
        System.out.println("Pelicula actual: " + peliculaActual.titulo + "\n");
        System.out.println("Director: " + peliculaActual.director + "\n");
        System.out.println("Duracion: " + peliculaActual.duracion + "\n");
        System.out.println("Edad Minima: " + peliculaActual.edadMinima + "\n");
        System.out.println("\n------------------------------------------------\n");
        System.out.println("Precio de entrada: " + precioEntrada + "\n");
        System.out.println("Asientos (x = ocupado):\n");
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                 if (asientos[i][j].ocupado) {
                    System.out.print(" X ");
                } else {
                    System.out.print(asientos[i][j].letra + "" + asientos[i][j].numero + " ");
                }
            }
            System.out.println();
        }
    }
}
