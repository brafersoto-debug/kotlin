// ==========================================
// ENUM: Roles disponibles
// ==========================================

enum class Rol {
    ADMIN,
    EDITOR,
    VISITANTE
}


// ==========================================
// INTERFACE: Comportamiento de autenticación
// ==========================================

interface Autenticable {
    fun autenticar(passwordIngresada: String): Boolean
}


// ==========================================
// DATA CLASS: Datos del usuario
// ==========================================

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val password: String,
    val rol: Rol
) : Autenticable {

    override fun autenticar(passwordIngresada: String): Boolean {
        return password == passwordIngresada
    }
}


// ==========================================
// OBJECT: Gestor de usuarios
// ==========================================

object GestorUsuarios {

    private val usuarios = mutableListOf<Usuario>()

    // Agregar usuario
    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
        println("Usuario ${usuario.nombre} agregado correctamente.")
    }

    // Mostrar usuarios
    fun listarUsuarios() {

        println("\n===== USUARIOS REGISTRADOS =====")

        for (usuario in usuarios) {
            println(
                "ID: ${usuario.id} | " +
                "Nombre: ${usuario.nombre} | " +
                "Email: ${usuario.email} | " +
                "Rol: ${usuario.rol}"
            )
        }
    }

    // Buscar usuario por email
    fun buscarUsuario(email: String): Usuario? {

        return usuarios.find {
            it.email == email
        }
    }

    // Iniciar sesión
    fun iniciarSesion(
        email: String,
        password: String
    ): Usuario? {

        val usuario = buscarUsuario(email)

        if (usuario == null) {
            println("Usuario no encontrado.")
            return null
        }

        if (usuario.autenticar(password)) {
            println("Inicio de sesión correcto.")
            return usuario
        }

        println("Contraseña incorrecta.")
        return null
    }

    // Validar rol usando WHEN
    fun validarRol(usuario: Usuario) {

        when (usuario.rol) {

            Rol.ADMIN -> {
                println("${usuario.nombre}: eres ADMIN.")
                println("Puedes administrar todo el sistema.")
            }

            Rol.EDITOR -> {
                println("${usuario.nombre}: eres EDITOR.")
                println("Puedes editar contenido.")
            }

            Rol.VISITANTE -> {
                println("${usuario.nombre}: eres VISITANTE.")
                println("Solo puedes consultar información.")
            }
        }
    }
}


// ==========================================
// MAIN
// ==========================================

fun main() {

    // Crear usuarios

    val usuario1 = Usuario(
        id = 1,
        nombre = "Carlos",
        email = "carlos@gmail.com",
        password = "1234",
        rol = Rol.ADMIN
    )

    val usuario2 = Usuario(
        id = 2,
        nombre = "Ana",
        email = "ana@gmail.com",
        password = "5678",
        rol = Rol.EDITOR
    )

    val usuario3 = Usuario(
        id = 3,
        nombre = "Luis",
        email = "luis@gmail.com",
        password = "abcd",
        rol = Rol.VISITANTE
    )


    // ==========================================
    // Agregar usuarios
    // ==========================================

    GestorUsuarios.agregarUsuario(usuario1)
    GestorUsuarios.agregarUsuario(usuario2)
    GestorUsuarios.agregarUsuario(usuario3)


    // ==========================================
    // Mostrar usuarios
    // ==========================================

    GestorUsuarios.listarUsuarios()


    // ==========================================
    // Probar autenticación
    // ==========================================

    println("\n===== INICIAR SESIÓN =====")

    val usuarioLogueado =
        GestorUsuarios.iniciarSesion(
            "ana@gmail.com",
            "5678"
        )


    // ==========================================
    // Validar rol
    // ==========================================

    if (usuarioLogueado != null) {

        println("\n===== VALIDAR ROL =====")

        GestorUsuarios.validarRol(usuarioLogueado)
    }
}
