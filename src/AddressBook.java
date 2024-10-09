import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
    private HashMap<String, String> contacts = new HashMap<>(); // Mapa para almacenar contactos

    // Cargar los contactos desde el archivo
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                contacts.put(parts[0], parts[1]); // Guardar en el mapa (número -> nombre)
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo.");
        }
    }

    // Guardar los contactos en el archivo
    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("contacts.csv"))) {
            for (String number : contacts.keySet()) {
                writer.println(number + "," + contacts.get(number)); // Guardar en CSV
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo.");
        }
    }

    // Listar los contactos
    public void list() {
        System.out.println("Contactos:");
        for (String number : contacts.keySet()) {
            System.out.println(number + " : " + contacts.get(number));
        }
    }

    // Crear un nuevo contacto
    public void create(String number, String name) {
        contacts.put(number, name);
    }

    // Eliminar un contacto
    public void delete(String number) {
        contacts.remove(number);
    }

    // Método principal (menú interactivo)
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load(); // Cargar contactos al inicio
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n1. Listar contactos");
            System.out.println("2. Crear nuevo contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar y salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (option == 1) {
                addressBook.list();
            } else if (option == 2) {
                System.out.print("Ingrese el número: ");
                String number = scanner.nextLine();
                System.out.print("Ingrese el nombre: ");
                String name = scanner.nextLine();
                addressBook.create(number, name);
            } else if (option == 3) {
                System.out.print("Ingrese el número a eliminar: ");
                String number = scanner.nextLine();
                addressBook.delete(number);
            }
        } while (option != 4);

        addressBook.save(); // Guardar antes de salir
        System.out.println("Contactos guardados.");
        scanner.close();
    }
}
