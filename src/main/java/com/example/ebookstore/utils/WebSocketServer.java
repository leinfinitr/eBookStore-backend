package com.example.ebookstore.utils;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/transfer/{userName}")
@Component
public class WebSocketServer {
    private static final ConcurrentHashMap<String, Session> SessionPool = new ConcurrentHashMap<>();

    public WebSocketServer() {
        System.out.println("WebSocketServer constructor");
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        SessionPool.put(userName.trim(), session);
    }

    @OnClose
    public void onClose(@PathParam("userName") String userName) {
        SessionPool.remove(userName.trim());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("onMessage: " + message);
    }

    public void sendMessage(Session toSession, String message) {
        if (toSession != null && toSession.isOpen()) {
            try {
                toSession.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToUser(String userName, String message) {
        Session toSession = SessionPool.get(userName);
        sendMessage(toSession, message);
    }
}
