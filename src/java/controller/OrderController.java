/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.orders.Orders;
import model.orders.OrdersDAO;
import model.users.Users;
import model.users.UsersDAO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author thanhpham
 */
//@Controller("/orders")
@Controller()
public class OrderController {

    OrdersDAO orderDao = new OrdersDAO();

    @RequestMapping(value = "/orderslist", method = RequestMethod.GET)
    public ModelAndView userslist(Model model) {
        List<Orders> listOrders = orderDao.listOrders();
        model.addAttribute("greeting", "orders list");
        model.addAttribute("listOrders", listOrders);
        return new ModelAndView("order", "employee", new Orders());

    }

    @RequestMapping(value = "/orderslist/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        orderDao.removeOrders(id);
        return "redirect:/orderslist.html";
    }

    @RequestMapping(value = "/orderslist/edit", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute("employee") Orders order, Model model) {
        System.out.println("thanh");
        orderDao.updateOrders(order);
        return "redirect:/orderslist.html";

    }

    @RequestMapping(value = "/orderslist/add", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("employee") Orders order, Model model) {
        try {
            Users user = (Users) orderDao.session.load(Users.class, new Integer(order.getUser().getId()));
            order.setUser(user);
        } catch (ObjectNotFoundException e) {
            System.out.println(e);
            return null;
        }
        orderDao.addOrder(order);
        return "redirect:/orderslist.html";

    }

    @RequestMapping(value = "/orderslist/search", method = RequestMethod.GET)
    public ModelAndView findALL(String key, Model model) {
        List<Orders> listOrders;
        if (key.equals("")) {
            listOrders = orderDao.listOrders();
        } else {
            listOrders = orderDao.findAll(key);
        }
        model.addAttribute("greeting", "orders list");
        model.addAttribute("listOrders", listOrders);
        return new ModelAndView("order", "employee", new Orders());
    }
}
