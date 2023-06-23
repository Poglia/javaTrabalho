
package sockets;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ClienteSocket extends Thread {
    private static boolean done = false;
    private Socket conexao;

    public ClienteSocket(Socket s) {
        conexao = s;

    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            String texto;
            while (true) {
                texto = entrada.readLine();

                if (texto == null) {
                    System.out.println("CONEXAO ENCERRADA");
                }
                System.out.println("\n" + texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        done = true;
    }

    public static void main(String[] args) {//
        try {
            Socket conexao = new Socket("127.0.0.1", 2222);
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Digite seu Nome: ");

            String nome = teclado.readLine();
            saida.println(nome);

            System.out.println("Digite o nome da sua Careta: ");

            String careta = teclado.readLine();
            saida.println(careta);

            Thread t = new ClienteSocket(conexao);//recebe do server
            t.start();

            String texto;

//            System.out.println("Escolha uma Careta: ");

            while (done == false) {
                System.out.println("Digite o número da pergunta escolhida: ");

                System.out.println("Array Perguntas ");
//                for (int i = 1; i <= perguntas.size(); i++) {
//                    System.out.println("[" + i + "]: " + perguntas.get(i));
//                }

                texto = teclado.readLine();
                saida.println(texto);//envia ao server

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
