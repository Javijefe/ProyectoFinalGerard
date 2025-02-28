import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {

        try (ServerSocket servidor = new ServerSocket(12345)) {
            System.out.println("Esperando jugadores...");

            Socket jugador1 = servidor.accept();
            System.out.println("Jugador 1 conectado.");
            PrintWriter salida1 = new PrintWriter(jugador1.getOutputStream(), true);
            BufferedReader entrada1 = new BufferedReader(new InputStreamReader(jugador1.getInputStream()));

            Socket jugador2 = servidor.accept();
            System.out.println("Jugador 2 conectado.");
            PrintWriter salida2 = new PrintWriter(jugador2.getOutputStream(), true);
            BufferedReader entrada2 = new BufferedReader(new InputStreamReader(jugador2.getInputStream()));

            int puntaje1 = 0, puntaje2 = 0;
            boolean turnoJugador1 = true;

            while (puntaje1 < 30 && puntaje2 < 30) {
                if (turnoJugador1) {
                    salida1.println("Tira el dado");
                    int tiro = Integer.parseInt(entrada1.readLine());
                    puntaje1 += tiro;
                    System.out.println("Jugador 1 tiró: " + tiro + " (Total: " + puntaje1 + ")");
                } else {
                    salida2.println("Tira el dado");
                    int tiro = Integer.parseInt(entrada2.readLine());
                    puntaje2 += tiro;
                    System.out.println("Jugador 2 tiró: " + tiro + " (Total: " + puntaje2 + ")");
                }
                turnoJugador1 = !turnoJugador1;
            }

            if (puntaje1 >= 30) {
                salida1.println("¡Ganaste!");
                salida2.println("Perdiste.");
            } else {
                salida1.println("Perdiste.");
                salida2.println("¡Ganaste!");
            }

            jugador1.close();
            jugador2.close();
            System.out.println("Juego terminado.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



