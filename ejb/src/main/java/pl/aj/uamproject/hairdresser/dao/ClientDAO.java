package pl.aj.uamproject.hairdresser.dao;

import pl.aj.uamproject.hairdresser.model.Appointment;
import pl.aj.uamproject.hairdresser.model.Client;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Startup
@Singleton
public class ClientDAO {
    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    public ClientDAO() {
    }

    @PostConstruct
    void init() {
        em.persist(new Client("Jan", "Kowalski", "jankowal@vp.pl", "698488394"));
        em.persist(new Client("Robert", "Nowak", "robert@onet.pl", "123456789"));
        em.persist(new Client("Sebastian", "Chmielewski", "chmielewskipolska@vp.pl", "569845316"));
        em.persist(new Client("Piotr", "Gajewski", "GajPiotr@vp.pl", "745896321"));
        em.persist(new Client("Wiktoria", "Nowak", "nowak.wikotria@vp.pl", "986532147"));
    }

    public Optional<List<Client>> getClientByPhoneNumber(String phoneNumber) {
        List<Client> clients = em.
                createQuery(
                        "SELECT c FROM Client c WHERE c.phoneNumber=:phoneNumber", Client.class)
                .setParameter("phoneNumber", phoneNumber).getResultList();
        return Optional.of(clients);
    }

    public Optional<List<Client>> getClientByEmail(String email) {
        List<Client> clients = em.
                createQuery(
                        "SELECT c FROM Client c WHERE c.email=:email", Client.class)
                .setParameter("email", email).getResultList();
        return Optional.of(clients);
    }

    public Optional<List<Client>> getClientByLastName(String lastName) {
        List<Client> clients = em.
                createQuery(
                        "SELECT c FROM Client c WHERE c.lastname=:lastname", Client.class)
                .setParameter("lastName", lastName).getResultList();
        return Optional.of(clients);
    }

    public Optional<Client> getById(int id) {
        Client client = em.
                createQuery(
                        "SELECT c FROM Client c WHERE c.id=:id", Client.class)
                .setParameter("id", id)
                .setMaxResults(1).getSingleResult();
        return Optional.of(client);
    }

    public Optional<List<Client>> getAll() {
        List<Client> clients = em.
                createQuery(
                        "SELECT c FROM Client c", Client.class)
                .getResultList();
        return Optional.of(clients);
    }

    public Client add(Client client) {
        em.persist(client);
        return client;
    }

    public boolean remove(int id) {
        Optional<Client> userData = getById(id);
        if (userData.isPresent()) {
            em.remove(userData.get());
            return true;
        } else {
            return false;
        }
    }

    public Client update(Client item) {
        em.merge(item);
        return item;
    }
}
