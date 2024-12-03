package repositoryImplementation;

import java.util.List;
import java.util.Optional;

import Models.Etudiant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import repository.EtudiantRepository;
import myproject.java.HibernateUtil;

public class RepositoryServise implements EtudiantRepository {

    

    @Override
    public List<Etudiant> GetAllEtudiant() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Etudiant> etudiants = null;
        try {
            String hql = "SELECT DISTINCT e FROM Etudiant e LEFT JOIN FETCH e.roles LEFT JOIN FETCH e.adresses";
            Query<Etudiant> query = session.createQuery(hql, Etudiant.class);
            etudiants = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return etudiants;
    }


    @Override
    public Etudiant getEudiantById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Etudiant etudiant = session.get(Etudiant.class, id);
        session.close();
        return etudiant;
    }

    @Override
    public void DeleteEtudiant(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Etudiant etudiant = session.get(Etudiant.class, id);
            if (etudiant != null) {
                session.delete(etudiant);
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void AddEtudiant(Etudiant etud) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(etud);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean modifEtudiant(Long id, Etudiant newEtud) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Etudiant etudiant = session.get(Etudiant.class, id);
            if (etudiant != null) {
                etudiant.setFirstName(newEtud.getFirstName());
                etudiant.setLastName(newEtud.getLastName());
                etudiant.setAge(newEtud.getAge());
                etudiant.setEmail(newEtud.getEmail());
                session.update(etudiant);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
