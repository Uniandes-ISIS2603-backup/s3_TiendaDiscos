package co.edu.uniandes.csw.tiendadiscos.persistance;


import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author estudiante
 */
@Stateless
public class BillingInformationPersistence {

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

    public BillingInformationEntity create(BillingInformationEntity billingInformationEntity) {

        em.persist(billingInformationEntity);
        return billingInformationEntity;
    }

}
