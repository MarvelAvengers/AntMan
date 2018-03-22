package avengers.marvel

import avengers.marvel.store.UsedKeysTable
import io.grpc.stub.StreamObserver
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
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
        var key: String
        while (true) {
            key = getRandomBase64Key().substring(0, 20)
            if (!isPresentInDb(key)) {
                break
            }
        }
        return key
    }

    private fun isPresentInDb(key: String): Boolean {
        val result = transaction {
            val query = UsedKeysTable.select {
                UsedKeysTable.name eq key
            }
            if (query.count() == 0) {
                UsedKeysTable.insert {
                    it[name] = key
                }
                return@transaction key
            } else {
                return@transaction ""
            }
        }
        return result.isNullOrEmpty()
    }

    private fun getRandomBase64Key(): String {
        val randomBytes = ByteArray(256).apply {
            Random().nextBytes(this)
        }
        return Base64.getEncoder().encodeToString(randomBytes)
    }
}
