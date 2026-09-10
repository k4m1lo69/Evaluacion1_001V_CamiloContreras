fun simularConsultaDisponibilidad() {
    println("Consultando disponibilidad de alojamientos...")
    Thread.sleep(1000)
    println("Consulta completada.\n")
}

fun main() {
    val repo = Repo()

    simularConsultaDisponibilidad()

    val intentos = listOf(
        ReservarAlojamiento(1, "Ana Pérez", 3, 45000.0, 2, TipoAlojamiento.CABANA),
        ReservarAlojamiento(2, "Juan Soto", 2, 30000.0, 1, TipoAlojamiento.HABITACION),
        ReservarAlojamiento(3, "María Rojas", 5, 60000.0, 4, TipoAlojamiento.DEPARTAMENTO),
        ReservarAlojamiento(4, "Pedro Díaz", 1, 25000.0, 2, TipoAlojamiento.HABITACION),
        ReservarAlojamiento(0, "Id inválido", 2, 30000.0, 1, TipoAlojamiento.HABITACION),      // id <= 0
        ReservarAlojamiento(5, "", 2, 30000.0, 1, TipoAlojamiento.HABITACION),                  // nombre vacío
        ReservarAlojamiento(6, "Noches inválidas", 0, 30000.0, 1, TipoAlojamiento.HABITACION),  // noches <= 0
        ReservarAlojamiento(7, "Valor inválido", 2, -1000.0, 1, TipoAlojamiento.HABITACION),    // valor <= 0
        ReservarAlojamiento(8, "Personas inválidas", 2, 30000.0, 0, TipoAlojamiento.HABITACION),// personas <= 0
        ReservarAlojamiento(1, "Id repetido", 2, 30000.0, 1, TipoAlojamiento.CABANA)            // id ya usado
    )

    println("Registro de reservas")
    for (reserva in intentos) {
        try {
            val resultado = repo.registrar(reserva)
            when (resultado) {
                is ResultadoRegistro.Exito ->
                    println("OK!! Registrada: ${resultado.reserva.nombreCliente}")
                is ResultadoRegistro.Error ->
                    println("ERROR!! '${reserva.nombreCliente}' no se registró: ${resultado.mensaje}")
            }
        } catch (e: Exception) {
            println("ERROR!!  ${e.message}")
        }
    }

    val registradas = repo.obtenerReservas()

    println("\nReservas registradas")
    for (reserva in registradas) {
        println(
            "ID: ${reserva.id} | Cliente: ${reserva.nombreCliente} | Noches: ${reserva.cantidadNoches} | " +
                    "Valor x noche: ${reserva.valorPorNoche} | Personas: ${reserva.cantidadPersonas} | " +
                    "Tipo: ${reserva.tipo.descripcion} | Total reserva: ${reserva.valorTotal()} | " +
                    "Descripción: ${reserva.descripcion()}"
        )
    }

    val valorTotalGeneral = registradas.sumOf { it.valorTotal() }
    println("\nValor total recaudado por todas las reservas: $valorTotalGeneral")

    println("\nReservas con más de 2 noches")
    registradas.filter { it.cantidadNoches > 2 }
        .forEach { println("- ${it.nombreCliente} (${it.cantidadNoches} noches)") }

    println("\nUna ReservaAlojamiento tratada como Reserva")
    val reservaGenerica: Reserva = registradas.first()
    println(reservaGenerica.descripcion())
}
 