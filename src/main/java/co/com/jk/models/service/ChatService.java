/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.models.service;

import co.com.jk.models.dao.IChatRepository;
import co.com.jk.models.documents.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CTA-PROGRAMADOR
 */
@Service
public class ChatService {
    
    @Autowired
    private IChatRepository chatDao;
    
    public List<Mensaje> obtenerUltimos10Mensajes(){
        return chatDao.findFirst10ByOrderByFechaDesc();
    }
    
    public Mensaje guardar(Mensaje mensaje){
        return chatDao.save(mensaje);
    }
    
}
