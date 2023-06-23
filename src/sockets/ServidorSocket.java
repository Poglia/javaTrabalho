package sockets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServidorSocket extends Thread {

    private static List<Cliente> clientes;
    private Cliente cliente;

    private Socket conexao;
    private String nomeCliente;
    private String nomeCareta;

    private String pontuacao;

    private String lampada;

    public ServidorSocket(Cliente c) {
        cliente = c;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getSocket().getInputStream()));
            PrintStream saida = new PrintStream(cliente.getSocket().getOutputStream());

            cliente.setSaida(saida);
            nomeCliente = entrada.readLine();

            if (nomeCliente == null) {
                return;
            }

            cliente.setNome(nomeCliente);

            nomeCareta = entrada.readLine();

            if (nomeCareta == null) {
                return;
            }

            cliente.setCareta(nomeCareta);
            String texto = entrada.readLine();
            while (texto != null && !(texto.trim().equals(""))) {
                sendToAll(saida, " disse: ", texto);
                texto = entrada.readLine();
            }
            sendToAll(saida, " saiu da conexao ", " JOGO ENCERRADO");
            clientes.remove(saida);
            conexao.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(PrintStream saida, String texto1, String texto2) {
        try {
            Iterator<Cliente> iter = clientes.iterator();
            while (iter.hasNext()) {
                Cliente outroCliente = iter.next();
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                if (chat != saida) {
                    chat.println(cliente.getNome() + texto1 + texto2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToOne(PrintStream saida, String texto1, String texto2) {
        try {
            Iterator<Cliente> iter = clientes.iterator();
            while (iter.hasNext()) {
                Cliente outroCliente = iter.next();
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                if (chat != saida) {
                    chat.println(cliente.getNome() + " com IP: " + cliente.getSocket().getRemoteSocketAddress() + texto1 + texto2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public static void main(String[] args) {
        clientes = new ArrayList<Cliente>();
         try {
            ServerSocket s = new ServerSocket(2222);

            System.out.println("ESPERANDO ALGUEM CONECTAR...");
            while (true) {

                Socket conexao = s.accept();
                
                Cliente cliente = new Cliente();
                
                cliente.setId(conexao.getRemoteSocketAddress().toString());
                cliente.setIp(conexao.getRemoteSocketAddress().toString());
                
                cliente.setSocket(conexao);
                
                clientes.add(cliente);
                
                System.out.println("CONECTOU "+ conexao.getRemoteSocketAddress());
                
                Thread t = new ServidorSocket(cliente);
                t.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
