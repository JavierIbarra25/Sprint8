import java.util.Scanner;

public class Sprint8Part1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        final String[] tipusVenta = {
            "Venta lliure", // 0
            "Pensionista",   // 1
            "Carnet Jove",   // 2
            "Soci"           // 3
        };

        String tipusVentaString = "";

        // Solicitar el tipo de venta
        tipusVentaString = readTipusVenta(input, tipusVenta);
        if (tipusVentaString == null) {
            exitProgram(input, "Error: Has superado el número máximo de intentos. No se pudo obtener un tipo de venta válido.");
        }
        
        // Solicitar el resto de los datos

        int id = readInt(input, "Introduce el número de ID del cliente (111-999):", 111, 999);
        int edad = readInt(input, "Introduce la edad del cliente (14-120):", 14, 120);
        int compra = readInt(input, "Introduce el importe de la compra (0-1000):", 0, 1000);
        int telefon = readInt(input, "Introduce el número de teléfono (111111111-999999999):", 111111111, 999999999);

        // Mostrar resultados
        System.out.println("ID: " + id + " | Edad: " + edad + " | Tipo de venta: " + tipusVentaString + " | Importe: " + compra + " | Teléfono: " + telefon);

        input.close();
    }


    // Método para leer el tipo de venta
    public static String readTipusVenta(Scanner input, String[] tipusVenta) {
        
        int attempts = 0;

        while (attempts <3) {
            System.out.println("Introduce el tipo de venta");
            System.out.println("--------------------------");
            System.out.println("venta lliure (0)");
            System.out.println("pensionista (1)");
            System.out.println("carnet jove (2)");
            System.out.println("soci (3)");

            if (input.hasNextInt()) {
                int numVenta = input.nextInt();  // Damos a la variable numVenta el valor del input
                if (numVenta >= 0 && numVenta < tipusVenta.length) {
                    return tipusVenta[numVenta]; // Retornar el tipo de venta correspondiente
                } else {
                    System.out.println("Error: el tipo de venta es incorrecto. Por favor, inténtalo de nuevo.");
                }
            } else {
                System.out.println("Error: por favor, ingresa un número entero.");
                input.next(); // Limpiar el buffer
            }
            attempts++;
            
        }
        return null; // Retornar null si no se obtuvo un tipo de venta válido
    }

    // Método para leer un entero y verificar si está en un rango
    public static int readInt(Scanner input, String impr, int min, int max) {
        
        int attempts = 0; // Contador de intentos

        while (attempts < 3) {
            System.out.println(impr);

            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= min && value <= max) {
                    return value; // Salir del bucle si la entrada es válida
                } else {
                    System.out.println("Error: el valor debe estar entre " + min + " y " + max + ". Por favor, inténtalo de nuevo.");
                }
            } else {
                System.out.println("Error: por favor, ingresa un número entero.");
                input.next(); // Limpiar el buffer
            }
            attempts++; // Incrementar el contador de intentos
        }

        exitProgram(input, "Error: Has superado el número máximo de intentos.");
        return -1; // Esto nunca se ejecutará por la salida previa
    }

     // Método para detener el programa de forma segura
     public static void exitProgram(Scanner input, String message) {
        System.out.println(message);
        input.close();
        System.exit(0);
    }
   
}