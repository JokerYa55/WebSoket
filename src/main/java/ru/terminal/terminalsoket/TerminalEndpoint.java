/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.terminal.terminalsoket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author vasiliy.andricov
 */
@ServerEndpoint("/endpoint")
public class TerminalEndpoint {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message, Session session) throws IOException {
        System.out.println("onMessage => " + message);
        session.getAsyncRemote().sendText("55_" + message + "_" + session.getId());
        return null;
    }

    @OnOpen
    public void onOpen(Session peer) {
        System.out.println("onOpen");
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        System.out.println("onClose");
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
    }

}
