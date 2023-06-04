package org.chesspuzzlesbot.dao;

import org.chesspuzzlesbot.entity.Puzzle;
import org.chesspuzzlesbot.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class PuzzleDao {

    public String getPuzzleLichessIdById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            Puzzle puzzle = session.byId(Puzzle.class).getReference(id);
            // commit transaction
            transaction.commit();

            return puzzle.getLichessId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public String getSolutionById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            Puzzle puzzle = session.byId(Puzzle.class).getReference(id);
            // commit transaction
            transaction.commit();

            return puzzle.getSolution();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public void savePuzzle(Puzzle puzzle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(puzzle);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePuzzle(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String query = "delete from puzzles where id=" + id;

            session.createNativeQuery(query, Puzzle.class).executeUpdate();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
