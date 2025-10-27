#----- Mini Sistema de Login -----#
class Usuario:
    def __init__(self, nombre, contraseña):
        self.nombre = nombre
        self.contraseña = contraseña

    def mostrar_bienvenida(self):
        print(f"Bienvenido {self.nombre}")


# --- Clases hijas ---
class Administrador(Usuario):
    def mostrar_bienvenida(self):
        print(f"Bienvenido administrador {self.nombre}. Tienes acceso total al sistema.")


class Cliente(Usuario):
    def mostrar_bienvenida(self):
        print(f"Bienvenido cliente {self.nombre}. Puedes realizar tus operaciones.")


# --- Sistema de Login ---
class SistemaLogin:
    def __init__(self):
        self.usuarios = {}

    def registro(self, nombre, contraseña, tipo="cliente"):
        if nombre in self.usuarios:
            print("El usuario ya está registrado.")
        elif tipo == "admin":
                self.usuarios[nombre] = Administrador(nombre, contraseña)
        else:
            self.usuarios[nombre] = Cliente(nombre, contraseña)
            print("Usuario registrado con éxito.")

    def login(self, nombre, contraseña):
        if nombre in self.usuarios and self.usuarios[nombre].contraseña == contraseña:
            self.usuarios[nombre].mostrar_bienvenida()  # <- polimorfismo
        else:
            print("Usuario o contraseña incorrecta.")


# --- PROGRAMA PRINCIPAL ---
sistema = SistemaLogin()

while True:
    print("\n--- MENÚ PRINCIPAL ---")
    print("1. Registrar usuario")
    print("2. Iniciar sesión")
    print("3. Salir")
    opcion = input("Elige una opción: ")

    if opcion == "1":
        nombre = input("Nombre de usuario: ")
        contraseña = input("Contraseña: ")
        tipo = input("Tipo de usuario (admin/cliente): ").lower()
        sistema.registro(nombre, contraseña, tipo)

    elif opcion == "2":
        nombre = input("Nombre de usuario: ")
        contraseña = input("Contraseña: ")
        sistema.login(nombre, contraseña)

    elif opcion == "3":
        print("Haz Salido del Sistema")
        break

    else:
        print("Opción no válida. Intenta nuevamente.")
