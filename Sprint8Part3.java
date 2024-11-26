import java.util.Scanner;

public class Sprint8Part3 {

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
            System.out.println("\n=====================");
            System.out.println("\n--- Registro de un nuevo cliente ---");

            // Solicitar tipo de venta
            String tipusVentaString = readTipusVenta(input, tipusVenta);
            if (tipusVentaString == null) {
                showError ("el tipo de venta.");
                continue; // Pasar al siguiente cliente
            }

            // Solicitar el resto de los datos del cliente
            int id = readInt(input, "Introduce el número de ID del cliente (111-999):", 111, 999);
            if (id == -1) {
                showError ("el ID.");
                continue;
            }

            int edad = readInt(input, "Introduce la edad del cliente (14-120):", 14, 120);
            if (edad == -1) {
                showError ("la edad.");
                continue;
            }

            int compra = readInt(input, "Introduce el importe de la compra (0-1000):", 0, 1000);
            if (compra == -1) {
                showError("el importe.");
                continue;
            }

            int telefon = readInt(input, "Introduce el número de teléfono (111111111-999999999):", 111111111, 999999999);
            if (telefon == -1) {
                showError("el teléfono.");
                continue;
            }

            // Mostrar datos del cliente registrado
            System.out.println("\n=====================");
            System.out.println("Cliente registrado con éxito:");
            System.out.println("=====================");
            System.out.println("ID: " + id + " | Edad: " + edad + " | Tipo de venta: " + tipusVentaString + " | Importe: " + compra + " | Teléfono: " + telefon);

            // Incrementar contador de clientes registrados correctamente
            totalClientes++;

            // Preguntar si se desea registrar otro cliente
            System.out.println("\n=====================");
            continuar = askToContinue(input, "¿Quieres registrar otro cliente? (s/n): ");
        }

        // Mostrar total de clientes registrados al final
        System.out.printf("S'han introduït" + totalClientes + "client(s)");

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

    public static boolean askToContinue(Scanner input, String impr) {
    while (true) {
        System.out.print(impr);
        String response = input.next().toLowerCase();
        if (response.equals("s") || response.equals("si")) {
            return true;
        } else if (response.equals("n") || response.equals("no")) {
            return false;
        } else {
            System.out.println("Respuesta no válida. Por favor, introduce 's' para sí o 'n' para no.");
            }
        }
    }

    // método para mostrar mensajes estándar en caso de error

    public static void showError(String fieldName) {
        System.out.println("Error: No se pudo registrar este cliente por intentos fallidos en " + fieldName + ".");
    }

}