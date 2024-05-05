package br.com.inkinvite.domain.objetosDeValor.documento;

import br.com.inkinvite.domain.DominioException;

public class CPF extends DocumentoIdentificacao {
    private String numero;

    private CPF() {
    }

    public static CPF criar(String numero) throws CPFInvalido {
        CPF cpf = new CPF();
        cpf.numero = numero.replaceAll("\\D", "");
        if (!validar(cpf.numero)) throw new CPFInvalido();
        return cpf;
    }

    public CPF carregar(String numero) {
        CPF cpf = new CPF();
        cpf.numero = numero.replaceAll("\\D", "");
        return cpf;
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public String formatar() {
        StringBuilder cpfFormatado = new StringBuilder();
        cpfFormatado.append(numero, 0, 3).append(".");
        cpfFormatado.append(numero, 3, 6).append(".");
        cpfFormatado.append(numero, 6, 9).append("-");
        cpfFormatado.append(numero, 9, 11);
        return cpfFormatado.toString();
    }

    private static boolean validar(String cpf) {
        String cpfGerado = "";
        cpf = removerCaracteres(cpf);
        if (verificarSeTamanhoInvalido(cpf)) return false;
        if (verificarSeDigIguais(cpf)) return false;
        cpfGerado = cpf.substring(0, 9);
        cpfGerado += calcularDigitoVerificador(cpfGerado);
        cpfGerado += calcularDigitoVerificador(cpfGerado);
        return cpfGerado.equals(cpf);
    }

    private static String removerCaracteres(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private static boolean verificarSeTamanhoInvalido(String cpf) {
        return cpf.length() != 11;
    }

    private static boolean verificarSeDigIguais(String cpf) {
        char primeiroDigito = cpf.charAt(0);
        for (char c : cpf.toCharArray()) {
            if (c != primeiroDigito) return false;
        }
        return true;
    }

    private static String calcularDigitoVerificador(String cpf) {
        int digitoGerado = 0;
        int multiplicador = cpf.length() + 1;
        for (char c : cpf.toCharArray()) {
            digitoGerado += Character.getNumericValue(c) * multiplicador--;
        }
        digitoGerado = digitoGerado % 11 < 2 ? 0 : 11 - digitoGerado % 11;
        return String.valueOf(digitoGerado);
    }
}

class CPFInvalido extends DominioException {
    public CPFInvalido() {
        super.mensagem = "CPF inválido";
    }
}
