package code;

import java.util.Scanner;
import java.util.Vector;

public class TestCassaQueue {

    static Queue<String> coda;
    static Vector<Queue<String>> casse;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        coda = new QueueLinkedList<String>();
        casse = new Vector<Queue<String>>(2);
        for (int i = 0; i < 2; i++) {
            casse.add(new QueueLinkedListLimitata<String>(3));
        }

        clearScreen();
        System.out.println("Benvenuto!!\n");

        boolean esci = false;
        while (!esci) {

            smaltisciClienti();
            // faccio passare i clienti in cassa

            System.out.println("\nCoda: " + coda);
            // System.out.println("Cassa: " + casse);
            for (int i = 0; i < casse.size(); i++) {
                System.out.println("Cassa " + (i + 1) + ": " + casse.get(i).toString());
            }

            System.out.println("\nScegli un'opzione:");
            System.out.println("1: aggiungi un cliente in coda");
            System.out.println("2: servi un cliente alla cassa");
            System.out.println("3: controlla chi è il prossimo della coda");
            System.out.println("4: la coda è vuota?");
            System.out.println("5: esci");
            System.out.println("");
            int scelta = scanner.nextInt();
            scanner.nextLine();
            clearScreen();
            System.out.println("");
            switch (scelta) {
                case 1:
                    addCliente();
                    break;
                case 2:
                    serviCliente();
                    break;
                case 3:
                    checkCliente();
                    break;
                case 4:
                    isCodaVuota();
                    break;
                case 5:
                    esci = true;
                    break;
            }
        }
        scanner.close();

    }

    private static void smaltisciClienti() {
        for (int i = 0; i < casse.size(); i++) {
            Queue<String> cassa = casse.get(i);
            if (cassa instanceof QueueLinkedListLimitata<String>) {
                QueueLinkedListLimitata<String> cassaLimitata = (QueueLinkedListLimitata<String>) cassa;
                while (cassaLimitata.getSize() < cassaLimitata.getMax()) {
                    if (!coda.isEmpty()) {
                        String aux = coda.dequeue();
                        System.out.println("il cliente " + aux + " va alla cassa " + i);
                        cassaLimitata.enqueue(aux);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void addCliente() {
        System.out.println("Inserisci il nome del cliente\n");
        String nome = scanner.nextLine();
        coda.enqueue(nome);
    }

    private static void serviCliente() {
        System.out.println("Inserisci la cassa\n");
        int scelta = scanner.nextInt();
        scanner.nextLine();
        String nome = casse.get(scelta).dequeue();
        System.out.println("Il cliente " + nome + " è stato servito!");
    }

    private static void checkCliente() {
        String nome = coda.top();
        if (nome == null) {
            System.out.println("Non c'è nessuno in coda!");
        } else {
            System.out.println("Il prossimo cliente è " + nome);
        }
    }

    private static void isCodaVuota() {
        if (coda.isEmpty()) {
            System.out.println("Non c'è nessuno in coda!");
        } else {
            System.out.println("C'è qualcuno in coda!");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
