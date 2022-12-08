package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.UsersDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UsersRepository extends JpaRepository<UsersDao, UUID> {
    List<UsersDao> findByLastnameLikeIgnoreCase(String lastname);
    List<UsersDao> findByEmailLikeIgnoreCase(String email);


//    private final HibernateProvider manager;
//
//    @Override
//    public UsersDao save(UsersDao entity) {
//        try (Session session = manager.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return entity;
//    }
//
//    @Override
//    public void delete(UsersDao entity) {
//        try (Session session = manager.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.delete(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Optional<UsersDao> findByName(String lastname) {
//        try (Session session = manager.openSession()) {
//            return Optional.ofNullable(session.createQuery("FROM UsersDao as users WHERE users.lastname = :lastname",
//                            UsersDao.class)
//                    .setParameter("lastname", lastname).getSingleResult());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<UsersDao> findById(UUID id) {
//        try (Session session = manager.openSession()) {
//            return Optional.ofNullable(session.createQuery("FROM UsersDao as users WHERE users.id = :id",
//                            UsersDao.class)
//                    .setParameter("id", id).getSingleResult());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Set<UsersDao> findAll() {
//        try (final Session session = manager.openSession()) {
//            return session.createQuery("SELECT users FROM UsersDao as users", UsersDao.class)
//                    .stream().collect(Collectors.toSet());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Collections.emptySet();
//    }
//
//    @Override
//    public void update(UsersDao entity) {
//        try (Session session = manager.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
