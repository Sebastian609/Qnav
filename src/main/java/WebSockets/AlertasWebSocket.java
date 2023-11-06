package WebSockets;

import Session.SessionManager;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;




@ServerEndpoint("/alertas")
public class AlertasWebSocket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("abriendo conexion");
    }

    @OnClose
    public void onClose(Session session) {
        // Se ejecuta cuando se cierra la conexión
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // No necesitamos manejar mensajes entrantes para este ejemplo
    }
    public static void enviarAlerta(String mensaje) {
        System.out.println("--------------enviando alerta--------------------");
        for (Session session : SessionManager.getSessions()) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(mensaje);
                    System.out.println("mensaje= "+ mensaje);
                } catch (IOException e) {   
                    System.out.println("error al enviar mensaje "+ e);
                }
            }
        }
    }

    // Método para enviar una alerta a todos los clientes conectados
   
}
