sealed class ResultadoRegistro {
    data class Exito(val reserva: ReservarAlojamiento) : ResultadoRegistro()
    data class Error(val mensaje: String) : ResultadoRegistro()
}
