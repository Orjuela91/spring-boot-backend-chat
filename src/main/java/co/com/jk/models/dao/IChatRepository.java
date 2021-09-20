/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jk.models.dao;

import co.com.jk.models.documents.Mensaje;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author CTA-PROGRAMADOR
 */
public interface IChatRepository extends MongoRepository<Mensaje, String>{
    
    public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
