class Repo {
    private val reservas = mutableListOf<ReservarAlojamiento>()

    fun registrar(reserva: ReservarAlojamiento): ResultadoRegistro {
        if (reserva.id <= 0) {
            return ResultadoRegistro.Error("El id debe ser mayor que cero")
        }
        if (reserva.nombreCliente.isBlank()) {
            return ResultadoRegistro.Error("El nombre del cliente no puede estar vacío")
        }
        if (reserva.cantidadNoches <= 0) {
            return ResultadoRegistro.Error("La cantidad de noches no debe ser cero")
        }
        if (reserva.valorPorNoche <= 0) {
            return ResultadoRegistro.Error("El valor por noche debe ser mayor que cero")
        }
        if (reserva.cantidadPersonas <= 0) {
            return ResultadoRegistro.Error("La cantidad de no debe ser cero")
        }
        val yaExiste = reservas.any { it.id == reserva.id }
        if (yaExiste) {
            return ResultadoRegistro.Error("Ya existe un registro con id ${reserva.id}")
        }

        reservas.add(reserva)
        return ResultadoRegistro.Exito(reserva)
    }

    fun obtenerReservas(): List<ReservarAlojamiento> {
        return reservas.toList()
    }
}