// Archivo Único: SistemaLogin.java

import java.util.HashMap;
import java.util.Scanner;

// Clase principal del sistema que contiene el mapa de usuarios y la lógica de negocio
public class SistemaLogin {
    // Se usa HashMap<String, Usuario> como equivalente al diccionario de Python
    private HashMap<String, Usuario> usuarios;

    // Constructor
    public SistemaLogin() {
        this.usuarios = new HashMap<>();
    }

    // Método de Registro
    public void registro(String nombre, String contrasena, String tipo) {
        if (usuarios.containsKey(nombre)) {
            System.out.println("El usuario ya está registrado.");
        } else if (tipo.equalsIgnoreCase("admin")) {
            usuarios.put(nombre, new Administrador(nombre, contrasena));
            System.out.println("Administrador registrado con éxito.");
        } else {
            // Si el tipo no es "admin", por defecto es Cliente
            usuarios.put(nombre, new Cliente(nombre, contrasena));
            System.out.println("Usuario registrado con éxito.");
        }
    }

    // Método de Login
    public void login(String nombre, String contrasena) {
        if (usuarios.containsKey(nombre)) {
            Usuario usuario = usuarios.get(nombre);
            // Compara la contraseña usando .equals()
            if (usuario.getContrasena().equals(contrasena)) {
                // Polimorfismo: llama a la versión correcta de mostrarBienvenida
                usuario.mostrarBienvenida(); 
            } else {
                System.out.println("Usuario o contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario o contraseña incorrecta.");
        }
    }

    // --- CLASES INTERNAS (Para mantener la estructura en un solo archivo) ---

    // Clase Base: Usuario (no-pública para permitir que esté en el mismo archivo)
    static class Usuario {
        protected String nombre;
        protected String contrasena; 

        // Constructor
        public Usuario(String nombre, String contrasena) {
            this.nombre = nombre;
            this.contrasena = contrasena;
        }

        // Getter para la contraseña
        public String getContrasena() {
            return contrasena;
        }

        // Método polimórfico
        public void mostrarBienvenida() {
            System.out.println("Bienvenido " + nombre);
        }
    }

    // Clase Hija: Administrador
    static class Administrador extends Usuario {
        // Constructor
        public Administrador(String nombre, String contrasena) {
            super(nombre, contrasena);
        }

        // Sobrescritura (Polimorfismo)
        @Override
        public void mostrarBienvenida() {
            System.out.println("Bienvenido administrador " + nombre + ". Tienes acceso total al sistema.");
        }
    }

    // Clase Hija: Cliente
    static class Cliente extends Usuario {
        // Constructor
        public Cliente(String nombre, String contrasena) {
            super(nombre, contrasena);
        }

        // Sobrescritura (Polimorfismo)
        @Override
        public void mostrarBienvenida() {
            System.out.println("Bienvenido cliente " + nombre + ". Puedes realizar tus operaciones.");
        }
    }


    // --- PROGRAMA PRINCIPAL (Método main) ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaLogin sistema = new SistemaLogin();
        String opcion;

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre de usuario: ");
                    String nombreReg = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasenaReg = scanner.nextLine();
                    System.out.print("Tipo de usuario (admin/cliente): ");
                    String tipoReg = scanner.nextLine().toLowerCase();
                    sistema.registro(nombreReg, contrasenaReg, tipoReg);
                    break;
                case "2":
                    System.out.print("Nombre de usuario: ");
                    String nombreLog = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasenaLog = scanner.nextLine();
                    sistema.login(nombreLog, contrasenaLog);
                    break;
                case "3":
                    System.out.println("Haz Salido del Sistema");
                    scanner.close(); 
                    return; 
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
                    break;
            }
        }
    }
}