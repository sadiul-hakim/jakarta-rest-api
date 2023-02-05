package com.hakim.entities.client;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hakim
 */
@RequestScoped
@Transactional
public class ClientBean {

    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(Client.class);

    public boolean addClient(Client dto) {
        boolean isSaved = false;
        try {
            entityManager.persist(dto);
            isSaved = true;
            logger.info(String.format("User : %s is saved!", dto.getEmail()));
        } catch (Exception ex) {
            logger.info(String.format("Could not save client.Error : ", ex.getMessage()));
            ex.printStackTrace();
        }
        return isSaved;
    }

    public boolean updateClient(UpdateDTO dto, long id) {
        boolean isUpdated = false;
        try {
            Client client = entityManager.find(Client.class, id);
            client.setFullname(dto.getFullname());
            client.setCountry(dto.getCountry());

            isUpdated = true;
            logger.info(String.format("Use: %s is updated.",client.getEmail()));
        } catch (Exception ex) {
            logger.info(String.format("Could not update client.Error: ", ex.getMessage()));
            ex.printStackTrace();
        }

        return isUpdated;
    }

    public boolean deleteClient(long id) {
        boolean isDeleted = false;
        try {
            Client client = entityManager.find(Client.class, id);
            entityManager.remove(client);

            isDeleted = true;
            logger.warn(String.format("Client %s is deleted.",client.getEmail()));
        } catch (Exception ex) {
            logger.info(String.format("Could not delete client.Error: ", ex.getMessage()));
            ex.printStackTrace();
        }

        return isDeleted;
    }

    public Client getClient(long id) {
        Client client = null;
        try {
            Query createNamedQuery = entityManager.createQuery("select c from Client c where c.id=:id");
            createNamedQuery.setParameter("id", id);
            client = (Client) createNamedQuery.getSingleResult();
        } catch (Exception ex) {
            logger.info(String.format("Could not get user %d.Error: %s", id, ex.getMessage()));

            ex.printStackTrace();
        }

        return client;
    }

    public Client getClient(String email) {
        Client client = null;
        try {
            Query createNamedQuery = entityManager.createQuery("select c from Client c where c.email=:email");
            createNamedQuery.setParameter("email", email);
            client = (Client) createNamedQuery.getSingleResult();
        } catch (Exception ex) {
            logger.info(String.format("Could not get user %s.Error: %s",email, ex.getMessage()));

           
            ex.printStackTrace();
        }

        return client;
    }

    public List<Client> getAllClient() {
        List<Client> clients = new ArrayList<>();
        try {
            Query createNamedQuery = entityManager.createQuery("select c from Client c");

            clients = createNamedQuery.getResultList();
        } catch (Exception ex) {
            logger.info(String.format("Could not get user.Error: %s",ex.getMessage()));
            ex.printStackTrace();
        }

        return clients;
    }

    public Client login(LoginDTO dto) {
        Client client = null;
        try {
            client = getClient(dto.getEmail());
            if(client==null) return null;
            if(!client.getPassword().equals(dto.getPassword())) return null;
        } catch (Exception ex) {
            logger.info(String.format("Could not login user.Error : ",ex.getMessage()));
            
            ex.printStackTrace();
        }

        return client;
    }
}
