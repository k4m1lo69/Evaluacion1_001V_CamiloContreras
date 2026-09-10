data class ReservarAlojamiento(
    val idReserva: Int,
    val nombreClienteReserva: String,
    val nochesReserva: Int,
    val valorPorNoche: Double,
    val cantidadPersonas: Int,
    val tipo: TipoAlojamiento
) : Reserva(idReserva, nombreClienteReserva, nochesReserva) {

    override fun descripcion(): String {
        return "$nombreCliente - ${tipo.descripcion} - $cantidadPersonas persona(s) - $cantidadNoches noche(s)"
    }

    fun valorTotal(): Double {
        return valorPorNoche * cantidadNoches
    }
}