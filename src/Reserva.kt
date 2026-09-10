abstract class Reserva(
    val id: Int,
    val nombreCliente: String,
    val cantidadNoches: Int
) {
    abstract fun descripcion(): String
}