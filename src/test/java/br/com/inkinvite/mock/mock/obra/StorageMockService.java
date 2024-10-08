package br.com.inkinvite.mock.mock.obra;

import br.com.inkinvite.application.service.StorageService;

public class StorageMockService implements StorageService {

    public StorageMockService() {
    }

    @Override
    public void adicionarArquivoCapituloObra(Integer obra, Integer capitulo, byte[] bytes, String mimeType) {
        if (bytes.length == 0) throw new RuntimeException("Arquivo vazio");
    }

    @Override
    public byte[] buscarArquivoCapituloObra(Integer obra, Integer numeroCapitulo, String mimeType) {
        return new byte[0];
    }
}
