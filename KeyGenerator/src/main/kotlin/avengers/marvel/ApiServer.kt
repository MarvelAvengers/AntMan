package avengers.marvel

import io.grpc.ServerBuilder
import java.util.Properties

class ApiServer {

    fun start() {
//        val properties = Properties().apply {
//            load(javaClass.getResourceAsStream("src/main/res/project.properties"))
//        }
        val server = ServerBuilder
            .forPort(50051)
            .addService(KeyGeneratorImplementation())
            .build()
            .start()
        server.awaitTermination()

    }
}
