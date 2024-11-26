import java.util.Scanner;

public class Sprint8Part2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalClientes = 0; // Contador de clientes introducidos correctamente

        final String[] tipusVenta = {
            "Venta lliure", // 0
            "Pensionista",   // 1
            "Carnet Jove",   // 2
            "Soci"           // 3
        };

        boolean continuar = true;

        // Bucle para registrar múltiples clientes
        while (continuar) {
            System.out.println("\n--- Registro de un nuevo cliente ---");

            // Solicitar tipo de venta
            String tipusVentaString = readTipusVenta(input, tipusVenta);
            if (tipusVentaString == null) {
                System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en el tipo de venta.");
                continue; // Pasar al siguiente cliente
            }

            // Solicitar el resto de los datos del cliente
            int id = readInt(input, "Introduce el número de ID del cliente (111-999):", 111, 999);
            if (id == -1) {
                System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en el ID.");
                continue;
            }

            int edad = readInt(input, "Introduce la edad del cliente (14-120):", 14, 120);
            if (edad == -1) {
                System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en la edad.");
                continue;
            }

            int compra = readInt(input, "Introduce el importe de la compra (0-1000):", 0, 1000);
            if (compra == -1) {
                System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en el importe.");
                continue;
            }

            int telefon = readInt(input, "Introduce el número de teléfono (111111111-999999999):", 111111111, 999999999);
            if (telefon == -1) {
                System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en el teléfono.");
                continue;
            }

            // Mostrar datos del cliente registrado
            System.out.println("Cliente registrado con éxito:");
            System.out.printf("ID: %d | Edad: %d | Tipo de venta: %s | Importe: %d | Teléfono: %d%n",
                    id, edad, tipusVentaString, compra, telefon);

            // Incrementar contador de clientes registrados correctamente
            totalClientes++;

            // Preguntar si se desea registrar otro cliente
            continuar = askToContinue(input, "¿Quieres registrar otro cliente? (s/n): ");
        }

        // Mostrar total de clientes registrados al final
        System.out.printf("%nS'han introduït %d client(s).%n", totalClientes);

        input.close();
    }

    // Método para leer el tipo de venta
    public static String readTipusVenta(Scanner input, String[] tipusVenta) {
        int attempts = 0;

        while (attempts < 3) {
            System.out.println("Introduce el tipo de venta");
            System.out.println("--------------------------");
            System.out.println("venta lliure (0)");
            System.out.println("pensionista (1)");
            System.out.println("carnet jove (2)");
            System.out.println("soci (3)");

            if (input.hasNextInt()) {
                int numVenta = input.nextInt();
                if (numVenta >= 0 && numVenta < tipusVenta.length) {
                    return tipusVenta[numVenta];
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
        int attempts = 0;

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
            attempts++;
        }

        return -1; // Indicar error en la entrada tras tres intentos
    }

    // Método para preguntar si se desea continuar
    public static boolean askToContinue(Scanner input, String prompt) {
        System.out.print(prompt);
        String response = input.next();

        return response.equals("s");
    }
}
