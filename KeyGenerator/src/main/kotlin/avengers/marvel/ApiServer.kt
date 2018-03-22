package avengers.marvel

import avengers.marvel.store.DatabaseHelper
import io.grpc.ServerBuilder

class ApiServer {
    private val dbHelper = DatabaseHelper()

    fun start() {
        val server = ServerBuilder
            .forPort(50052)
            .addService(KeyGeneratorImplementation())
            .build()
            .start()
        server.awaitTermination()
    }
}
