package br.com.inkinvite.application.service;

public interface StorageService {

    void adicionarArquivoCapituloObra(Integer obra, Integer capitulo, byte[] bytes, String mimeType);

    byte[] buscarArquivoCapituloObra(Integer obra, Integer numeroCapitulo, String mimeType);
}
