package br.com.keycloak.domain;

public class DominioException extends RuntimeException {

    protected String mensagem;

    @Override
    public String toString() {
        return mensagem;
    }
}
