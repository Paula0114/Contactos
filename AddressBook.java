import java.io.*;
import java.util.*;

public class AddressBook {

    private HashMap<String, String> contactos;

    public AddressBook() {
        contactos = new HashMap<>();
    }

    public void load(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                contactos.put(partes[0], partes[1]);
            }
            br.close();
            System.out.println("Contactos cargados correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    public void save(String archivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            bw.close();
            System.out.println("Cambios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contactos.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String numero, String nombre) {
        contactos.put(numero, nombre);
        System.out.println("Contacto creado correctamente.");
    }

    public void delete(String numero) {
        if (contactos.containsKey(numero)) {
            contactos.remove(numero);
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El número telefónico no existe en la agenda.");
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("\n------ Agenda Telefónica ------\n");
            System.out.println("1. Cargar contactos desde archivo.");
            System.out.println("2. Guardar cambios en archivo.");
            System.out.println("3. Mostrar contactos en la agenda.");
            System.out.println("4. Crear nuevo contacto.");
            System.out.println("5. Eliminar contacto existente.");
            System.out.println("6. Salir.");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del archivo CSV: ");
                    scanner.nextLine();
                    String archivo1 = scanner.nextLine();
                    load(archivo1);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del archivo CSV: ");
                    scanner.nextLine();
                    String archivo2 = scanner.nextLine();
                    save(archivo2);
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    System.out.print("Ingrese el número del nuevo contacto: ");
                    scanner.nextLine();
                    String numero = scanner.nextLine();
                    System.out.print("Ingrese el nombre del nuevo contacto: ");
                    String nombre = scanner.nextLine();
                    create(numero, nombre);
                    break;
                case 5:
                    System.out.print("Ingrese el número del contacto a eliminar: ");
                    scanner.nextLine();
                    String numeroEliminar = scanner.nextLine();
                    delete(numeroEliminar);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        AddressBook agenda = new AddressBook();
        agenda.showMenu();
    }

}
