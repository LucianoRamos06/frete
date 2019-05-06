/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.frete.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author fabio
 */
public class FreteUtil {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final String CHAVE = "leiturapde";
    private static final String ALGORITMO = "DES";

    private static final String CAMINHO_ROOT
            = System.getProperty("user.home") + "/arquivos";
    private static final String CAMINHO_USUARIOS
            = System.getProperty("user.home") + "/arquivos/usuarios";
    private static final String CAMINHO_INSTITUICOES
            = System.getProperty("user.home") + "/arquivos/instituicoes";
    private static final String CAMINHO_INSTITUICOES_LOGOMARCAS
            = System.getProperty("user.home") + "/arquivos/instituicoes/logomarcas";
    private static final String CAMINHO_FORNECEDORES
            = System.getProperty("user.home") + "/arquivos/fornecedores";
    private static final String CAMINHO_FORNECEDORES_LOGOMARCAS
            = System.getProperty("user.home") + "/arquivos/fornecedores/logomarcas";
    private static final String CAMINHO_PRODUTOS
            = System.getProperty("user.home") + "/arquivos/produtos";

    public static File getCaminhoRoot() {
        File f = new File(CAMINHO_ROOT);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoUsuarios() {
        File f = new File(CAMINHO_USUARIOS);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoInstituicoes() {
        File f = new File(CAMINHO_INSTITUICOES);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoInstituicoesLogomarcas() {
        File f = new File(CAMINHO_INSTITUICOES_LOGOMARCAS);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoFornecedores() {
        File f = new File(CAMINHO_FORNECEDORES);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoFornecedoresLogomarcas() {
        File f = new File(CAMINHO_FORNECEDORES_LOGOMARCAS);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static File getCaminhoProdutos() {
        File f = new File(CAMINHO_PRODUTOS);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    public static String extrairExtensao(String arquivo) {
        if (arquivo == null) {
            return null;
        } else {
            String resposta = "";
            for (int i = arquivo.length() - 1; i >= 0; i--) {
                if (arquivo.charAt(i) == '.') {
                    break;
                } else {
                    resposta = arquivo.charAt(i) + resposta;
                }
            }
            return resposta;
        }

    }

    public static boolean excluirArquivo(String arquivo) {
        File file = new File(arquivo);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

    public static boolean salvarStream(InputStream in, String arquivoDestino) {
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(in);
            bout = new BufferedOutputStream(new FileOutputStream(new File(arquivoDestino)));
            while (bin.available() != 0) {
                bout.write(bin.read());
            }
            bin.close();
            bout.flush();
            bout.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

    public static String encriptarSHA256(String senha) {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        String senhaCripto = sha.encodePassword(senha, null);
        return senhaCripto;
    }

    /**
     * Algoritmo insertsort de ordenação
     *
     * @param <T>
     * @param lista que será ordenada
     * @param comparador que será utilizado durante a ordenação
     */
    public static <T> void ordenar(List<T> lista, Comparator<T> comparador) {
        if (lista != null
                && !lista.isEmpty()
                && comparador != null) {
            T item = null;
            int anterior = 0;
            for (int i = 1; i < lista.size(); i++) {
                item = lista.get(i);
                anterior = i - 1;
                for (int j = anterior; j >= 0; j--) {
                    if (comparador.compare(item, lista.get(j)) < 0) {
                        lista.set(j + 1, lista.get(j));
                        lista.set(j, item);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static String gerarSenhaAscii(int caracteres) {
        return RandomStringUtils.randomAlphanumeric(caracteres).toUpperCase();
    }

    public static String gerarCodigoReferencia(int letras, int numeros) {
        String letra = RandomStringUtils.randomAlphanumeric(letras).toUpperCase();
        String cod = letra + "-" + RandomStringUtils.randomNumeric(numeros).toUpperCase();
        return cod;
    }

    public static String criptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(senha.getBytes("UTF-8"));
            return asHex(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String descriptografarPrivado(String senha) {
        try {
            SecretKey key = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] resposta = cipher.doFinal(fromHexString(senha));
            return new String(resposta);
        } catch (Exception ex) {
            return null;
        }
    }

    private static byte[] fromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String asHex(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public static String numero(int numero) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("###00000");
            return decimalFormat.format(numero);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static String removeAcento(String texto) {
        if (texto == null) {
            return null;
        } else {
            String txt = Normalizer.normalize(texto, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            String resposta = pattern.matcher(txt).replaceAll("");
            return resposta;
        }
    }

    public static String removeSimbolosPontuacoes(String texto) {
        if (texto == null) {
            return null;
        } else {
            String txt = Normalizer.normalize(texto, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{Punct}+");
            String resposta = pattern.matcher(txt).replaceAll("");
            return resposta;
        }
    }

    public static String removeCharacterPhone(String telefone) {
        Pattern pattern;
        String resposta, txt;

        txt = Normalizer.normalize(telefone, Normalizer.Form.NFD);

        pattern = Pattern.compile("\\p{Punct}+");
        resposta = pattern.matcher(txt).replaceAll("");

        txt = Normalizer.normalize(resposta, Normalizer.Form.NFD);

        pattern = Pattern.compile("\\p{Space}+");
        resposta = pattern.matcher(txt).replaceAll("");

        return resposta;
    }

    public static boolean isCelular(String numeroCelular) {
        if (numeroCelular == null) {
            return false;
        } else {
            return numeroCelular.matches("[1-9]{2}9?[6-9][0-9]{7}");
        }
    }

    public static boolean isCelularOuFixo(String numeroCelularOuFixo) {
        if (numeroCelularOuFixo == null) {
            return false;
        } else if (numeroCelularOuFixo.matches("[1-9]{2}9?[6-9][0-9]{7}")) {
            return true;

        } else if (numeroCelularOuFixo.matches("[1-9]{2}[2-5][0-9]{7}")) {
            return true;
        } else {
            return false;
        }
    }

    //91 9 81741448
    public static String pegaDdd(String telefone) {
        if (!isCelular(telefone)) {
            return null;
        } else {
            return telefone.substring(0, 1) + telefone.substring(1, 2);
        }
    }

    public static String pegaNumeroCelular(String telefone) {
        if (!isCelular(telefone)) {
            return null;
        } else {
            return telefone.substring(3, 11);
        }
    }

    public static boolean isEmail(final String enderecoEmail) {
        final String EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
        Matcher matcher = pattern.matcher(enderecoEmail);
        return matcher.matches();

    }

    public static boolean isCep(final String cep) {
        final String CEP_PATTERN
                = "[0-9]{5}[0-9]{3}";
        Pattern pattern = Pattern.compile(CEP_PATTERN);
        Matcher matcher = pattern.matcher(removeSimbolosPontuacoes(cep));
        return matcher.matches();

    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isCPFouCNPJ(String CPFouCNPJ) {
        CPFouCNPJ = removeSimbolosPontuacoes(CPFouCNPJ);
        if ((CPFouCNPJ == null) || (CPFouCNPJ.length() < 11) || (CPFouCNPJ.length() > 14)) {
            return false;
        } else if (CPFouCNPJ.length() == 11) {

            Integer digito1 = calcularDigito(CPFouCNPJ.substring(0, 9), pesoCPF);
            Integer digito2 = calcularDigito(CPFouCNPJ.substring(0, 9) + digito1, pesoCPF);
            return CPFouCNPJ.equals(CPFouCNPJ.substring(0, 9) + digito1.toString() + digito2.toString());

        } else if (CPFouCNPJ.length() == 14) {

            Integer digito1 = calcularDigito(CPFouCNPJ.substring(0, 12), pesoCNPJ);
            Integer digito2 = calcularDigito(CPFouCNPJ.substring(0, 12) + digito1, pesoCNPJ);
            return CPFouCNPJ.equals(CPFouCNPJ.substring(0, 12) + digito1.toString() + digito2.toString());

        } else {
            return false;
        }
    }

    public static String formataCpfouCnpj(String valor) {
        if (valor == null) {
            return null;
        } else if (valor.length() == 14) {
            return valor.substring(0, 2)
                    + "." + valor.substring(2, 5)
                    + "." + valor.substring(5, 8)
                    + "/" + valor.substring(8, 12)
                    + "-" + valor.substring(12, 14);
        } else {
            return valor.substring(0, 3)
                    + "." + valor.substring(3, 6)
                    + "." + valor.substring(6, 9)
                    + "-" + valor.substring(9, 11);
        }
    }

    public static String formataValorEmReal(String valor) {

        if (valor == null) {
            return null;
        } else {
            BigDecimal v = new BigDecimal(valor);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            valor = nf.format(v);

            return valor;
        }
    }

    public static Double formataValorEmDouble(String valor) {
        if (valor == null) {
            return null;
        } else {
            Double v = new Double(valor.replaceAll(",", "."));
            return v;
        }
    }

    public static void redirectUrl(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String redirecionarPag(String url) {
        String resposta = null;
        try {
            if (url == null || url.isEmpty()) {
                return resposta;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                resposta = stringBuilder.append(url).append("?faces-redirect=true").toString();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return resposta;
    }

    public static int calculaIdade(Date dataNasc) {

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        } else if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
            idade--;
        }

        return idade;
    }

    public static boolean isApenasNumerosLetras(String textoDigitado) {
        Pattern padrao = Pattern.compile("[a-zA-Z0-9]+"); // A-Z a-z separados permitem "" (espaço)
        Matcher pesquisa = padrao.matcher(textoDigitado);
        if (pesquisa.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static Date converterStringToDate(String data) {
        if (data == null) {
            return null;
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                return formato.parse(data);
            } catch (ParseException ex) {
                return null;
            }
        }
    }

}
