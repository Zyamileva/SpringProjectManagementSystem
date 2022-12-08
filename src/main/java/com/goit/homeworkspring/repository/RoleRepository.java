package com.goit.homeworkspring.repository;

import com.goit.homeworkspring.model.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, UUID> {
    List<RoleDao> findByNameLikeIgnoreCase(String name);
}
//    private final HibernateProvider manager;
//
//    @Override
//    public RoleDao save(RoleDao entity) {
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
//    public void delete(RoleDao entity) {
//        try (Session session = manager.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.delete(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Set<RoleDao> findByNameSet(String name) {
//        try (Session session = manager.openSession()) {
//            return session.createQuery("FROM RoleDao as role WHERE role.name = :name",
//                            RoleDao.class)
//                    .setParameter("name", name).stream().collect(Collectors.toSet());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Collections.emptySet();
//    }
//
//    @Override
//    public Optional<RoleDao> findByName(String name) {
//        try (Session session = manager.openSession()) {
//            return Optional.ofNullable(session.createQuery("FROM RoleDao as role WHERE role.name = :name",
//                            RoleDao.class)
//                    .setParameter("name", name).stream().findFirst().get());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<RoleDao> findById(UUID id) {
//        try (Session session = manager.openSession()) {
//            return Optional.ofNullable(session.createQuery("FROM RoleDao as role WHERE role.id = :id",
//                            RoleDao.class)
//                    .setParameter("id", id).getSingleResult());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Set<RoleDao> findAll() {
//        try (final Session session = manager.openSession()) {
//            return session.createQuery("FROM RoleDao as role", RoleDao.class)
//                    .stream().collect(Collectors.toSet());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Collections.emptySet();
//    }
//
//    @Override
//    public void update(RoleDao entity) {
//        try (Session session = manager.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.update(entity);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
