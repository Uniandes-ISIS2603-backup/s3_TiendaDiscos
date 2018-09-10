/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;

import co.edu.uniandes.csw.tiendadiscos.persistence.EnvioPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@RunWith(Arquillian.class)
public class EnvioPersistenceTest {
    @Inject
    private EnvioPersistence envioPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
}
