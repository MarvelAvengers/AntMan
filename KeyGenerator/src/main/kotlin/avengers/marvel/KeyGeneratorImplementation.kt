package avengers.marvel

import io.grpc.stub.StreamObserver

class KeyGeneratorImplementation : KeyGeneratorApiGrpc.KeyGeneratorApiImplBase()
{
    override fun fetchKey(request: FetchKeyRequest?, responseObserver: StreamObserver<FetchKeyResponse>?) {
        responseObserver?.apply {
            onNext(FetchKeyResponse.newBuilder().setKey("1").build())
            onCompleted()
        }
    }
}
