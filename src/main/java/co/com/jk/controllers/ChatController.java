/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.controllers;

import co.com.jk.models.documents.Mensaje;
import co.com.jk.models.service.ChatService;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author CTA-PROGRAMADOR
 */

@Controller
public class ChatController {
    
    @Autowired 
    private ChatService chatService;
    
    @Autowired
    private SimpMessagingTemplate webSocket;
    
    private final String[] colores = {"red", "blue", "green", "magenta", "purple", "orange"};
    
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        
        if(mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(this.colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");
        }else{
            this.chatService.guardar(mensaje);
        }
        
        return mensaje;
    }
    
    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username){
        return username.concat(" esta escribiendo ...");
    }
    
    @MessageMapping("/historial")
    public void historial(String clienteId){
        webSocket.convertAndSend("/chat/historial/"+clienteId, chatService.obtenerUltimos10Mensajes());
    }
}
