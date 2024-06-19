package br.com.inkinvite.infrastructure.service;

import br.com.inkinvite.application.service.StorageService;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.util.Optional;

@ApplicationScoped
public class StorageS3Service implements StorageService {

    @ConfigProperty(name = "storage-region")
    String region;

    @ConfigProperty(name = "storage-bucket")
    String bucket;

    @ConfigProperty(name = "storage-access-key")
    String acessKey;

    @ConfigProperty(name = "storage-secret-key")
    String acessSecretKey;

    @ConfigProperty(name = "storage-endpoint")
    Optional<String> endPoint;

    @Override
    public void adicionarArquivoCapituloObra(Integer obra, Integer capitulo, byte[] bytes, String mimeType) {
        String caminhoArquivo = montarCaminhoObra(obra, capitulo, mimeType);
        PutObjectRequest request = MontarRequisicaoS3Pput(caminhoArquivo, mimeType, bucket);
        enviarStorage(request, acessKey, acessSecretKey, bytes);
    }

    @Override
    public byte[] buscarArquivoCapituloObra(Integer obra, Integer capitulo, String mimeType) {
        String caminhoArquivo = montarCaminhoObra(obra, capitulo, mimeType);
        return baixarComoBytes(caminhoArquivo);
    }

    private String montarCaminhoObra(Integer obra, Integer capitulo, String mimeType) {
        return obra + "/" + capitulo + "/" + montarNomeArquivo(obra, capitulo, mimeType);
    }

    private String montarNomeArquivo(Integer obra, Integer capitulo, String mimeType) {
        return "obra_" + obra + "_capitulo_" + capitulo + "." + mimeType;
    }

    private PutObjectRequest MontarRequisicaoS3Pput(String nomeArquivo, String mimeType, String bucket) {
        return PutObjectRequest.builder().bucket(bucket)
                .key(nomeArquivo).contentType(mimeType).build();
    }

    private void enviarStorage(PutObjectRequest request, String accessKey, String accessSecretKey, byte[] bytes) {
        S3ClientBuilder builder = configurarClienteStorage();
        try (S3Client s3Client = builder.build()) {
            s3Client.putObject(request, RequestBody.fromBytes(bytes));
        }
    }

    private S3ClientBuilder configurarClienteStorage() {
        S3ClientBuilder builder = adicionarCredenciais(acessKey, acessSecretKey);
        configurarClienteStorage(builder);
        return builder;
    }

    private S3ClientBuilder adicionarCredenciais(String accessKey, String accessSecretKey) {
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, accessSecretKey)));
    }

    private void configurarClienteStorage(S3ClientBuilder builder) {
        String endPoint = this.endPoint.orElse("");
        if (StringUtils.isNotBlank(endPoint)) {
            builder.endpointOverride(URI.create(endPoint)).forcePathStyle(true);
        } else {
            builder.region(Region.of(region));
        }
    }

    private byte[] baixarComoBytes(String caminhoArquivo) {
        S3ClientBuilder builder = configurarClienteStorage();
        try (S3Client s3 = builder.build()) {
            GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(caminhoArquivo).build();
            return s3.getObject(request, ResponseTransformer.toBytes()).asByteArray();
        }
    }

}
