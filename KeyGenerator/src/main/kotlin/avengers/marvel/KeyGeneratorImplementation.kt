package avengers.marvel

import io.grpc.stub.StreamObserver
import java.util.Base64
import java.util.Random

class KeyGeneratorImplementation : KeyGeneratorApiGrpc.KeyGeneratorApiImplBase() {
    override fun fetchKey(request: FetchKeyRequest?, responseObserver: StreamObserver<FetchKeyResponse>?) {
        responseObserver?.apply {
            onNext(FetchKeyResponse.newBuilder().setKey(generateKey()).build())
            onCompleted()
        }
    }

    private fun generateKey(): String {
        val randomBytes = ByteArray(256).apply {
            Random().nextBytes(this)
        }
        return Base64.getEncoder().encodeToString(randomBytes)
    }
}
