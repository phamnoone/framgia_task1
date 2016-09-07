/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.orders;

import config.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.users.Users;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author thanhpham
 */
public class OrdersDAO {

    public Session session = HibernateUtil.getSessionFactory().openSession();

    public List<Orders> listOrders() {

        List<Orders> results = null;
        try {
            if (session.isConnected()) {
                String hql = "FROM Orders E";
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

    public boolean addOrder(Orders p) {
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

    public boolean updateOrders(Orders p) {
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

    public Orders getOrdersById(int id) {
        try {
            Orders user = (Orders) session.load(Orders.class, new Integer(id));
            return user;
        } catch (ObjectNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean removeOrders(int id) {
        try {
            Orders p = (Orders) session.load(Orders.class, new Integer(id));
            System.out.println(p.getId());
            if (null != p) {
                session.getTransaction().begin();
                Query query = session.createQuery("delete Orders where id = :ID");
                query.setParameter("ID", id);
                int result = query.executeUpdate();
                session.getTransaction().commit();
                return true;
            }
        } catch (ObjectNotFoundException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public List<Orders> findAll(String key) {
        List<Orders> lst = new ArrayList<Orders>();
        try {
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Orders.class);
            Disjunction disCriteria = Restrictions.disjunction();
            disCriteria.add(Restrictions.eq("id", Integer.getInteger(key)));
            disCriteria.add(Restrictions.like("name", "%" + key + "%"));
             disCriteria.add(Restrictions.eq("price",Integer.getInteger(key)));
            criteria.add(disCriteria);
            session.getTransaction().commit();
            lst = criteria.list();
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return lst; 
        }
    }

    public static void main(String[] args) {

        OrdersDAO ordersDAO = new OrdersDAO();
//        ordersDAO.listOrders();
//        for (Orders orders : ordersDAO.listOrders()) {
//            System.out.println(orders.getName());
//        }
//        ordersDAO.removeOrders(4);

    }
}
