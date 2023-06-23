package sockets;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private String id;
    private String ip;
    private String nome;

    private String careta;
    private PrintStream saida;
    private Socket socket;

    private static Map<Integer, String> perguntas;

    public Cliente() {

        perguntas = new HashMap<>();
        perguntas.put(1, "É do sexo masculino?");
        perguntas.put(2, "Tem cabelo?");
        perguntas.put(3, "Tem os olhos azuis?");
        perguntas.put(4, "Usa chapéu?");
        perguntas.put(5, "Tem barba?");
        perguntas.put(6, "Tem barba?");
        perguntas.put(7, "Usa óculos?");
        perguntas.put(8, "Tem sardas?");
        perguntas.put(9, "Tem cabelo loiro?");
        perguntas.put(10, "Tem cabelo curto?");
        perguntas.put(11, "Usa brincos?");
        perguntas.put(12, "Tem olhos verdes?");
        perguntas.put(13, "Usa bigode?");
        perguntas.put(14, "Tem cabelo castanho?");
        perguntas.put(15, "Usa boné?");
        perguntas.put(16, "Tem cabelo cacheado?");
        perguntas.put(17, "Tem olhos castanhos?");

    }


    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCareta() {
        return careta;
    }

    public void setCareta(String careta) {
        this.careta = careta;
    }

    /**
     * @return the saida
     */
    public PrintStream getSaida() {
        return saida;
    }

    /**
     * @param saida the saida to set
     */
    public void setSaida(PrintStream saida) {
        this.saida = saida;
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String retirarPergunta(int numeroPergunta) {
        if (perguntas.containsKey(numeroPergunta)) {
            return perguntas.remove(numeroPergunta);
        } else {
            return "Pergunta não encontrada.";
        }
    }
}
