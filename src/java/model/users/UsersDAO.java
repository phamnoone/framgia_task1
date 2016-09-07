/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.users;

import java.util.List;
import config.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.Order;

/**
 *
 * @author thanhpham
 */
public class UsersDAO {

    public Session session = HibernateUtil.getSessionFactory().openSession();

    public List<Users> listUsers() {

        List<Users> results = null;
        try {
            if (session.isConnected()) {
                String hql = "FROM Users E";
                Query query = session.createQuery(hql);
                results = query.list();
            } else {
                System.out.println("Connection failed");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }

    public boolean addUsers(Users p) {
        try {
            session.getTransaction().begin();
            session.save(p);
            session.getTransaction().commit();
            return true;
        } catch (ConstraintViolationException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateUsers(Users p) {
        try {
            session.getTransaction().begin();
            session.merge(p);
            session.getTransaction().commit();
            return true;
        } catch (StaleStateException e) {
            System.out.println(e);
            return false;
        } catch (RuntimeException e) {
            System.out.println(e);
            return false;
        }
    }

    public Users getUsersById(int id) {
        try {
            Users user = (Users) session.load(Users.class, new Integer(id));
            return user;
        } catch (ObjectNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean removeUsers(int id) {
        try {
            Users p = (Users) session.load(Users.class, new Integer(id));
            if (null != p) {
                session.getTransaction().begin();
                session.delete(p);
                session.getTransaction().commit();
                return true;
            }
        } catch (ObjectNotFoundException e) {
            System.out.println(e);
            return false;
        } catch (ConstraintViolationException e) {
            System.out.println(e);
            return false;
        } finally {
            session.getTransaction().rollback();
        }
        return false;
    }

    public List<Users> findAll(String key) {
        List<Users> lst = new ArrayList<Users>();
        try {
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Users.class);
            Disjunction disCriteria = Restrictions.disjunction();
            disCriteria.add(Restrictions.eq("id", Integer.getInteger(key)));
            disCriteria.add(Restrictions.like("name", "%" + key + "%"));
            criteria.add(disCriteria);
            session.getTransaction().commit();
            lst = criteria.list();
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return lst;
        }
    }

    // TEST func
    public static void main(String[] args) {
        UsersDAO user = new UsersDAO();
//                    System.out.println("thanh");
//                    for (Users tem : user.findAll("2")) {
//                        System.out.println(tem.getName());
//                    }
//                    System.out.println("end");
        ////        user.updateUsers(new Users(113, "thanh4"));
        //        // user.addUsers(new Users(111, "thanh3"));
        ////        System.out.println(user.getUsersById(1));
        //

        user.removeUsers(1);
    }

}
