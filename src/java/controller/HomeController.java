/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.users.Users;
import model.users.UsersDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author thanhpham
 */
@Controller()
public class HomeController {

    UsersDAO usersDemo = new UsersDAO();

    @RequestMapping(value = "/userslist", method = RequestMethod.GET)
    public ModelAndView userslist(Model model) {
        List<Users> listUsers = usersDemo.listUsers();
        model.addAttribute("greeting", "Users list");
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("size", listUsers.size());
        return new ModelAndView("home", "employee", new Users());

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("employee") Users user, Model model) {
        usersDemo.updateUsers(user);
        return "redirect:userslist.html";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("employee") Users user,
            ModelMap model) {
        // xu li logic add fail
        usersDemo.addUsers(user);
        return "redirect:userslist.html";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int orderId, ModelMap mm) {
        if (!usersDemo.removeUsers(orderId)) {
            // xu li logic delete fail
        }
        return "redirect:/userslist.html";
    }

    @RequestMapping(value = "/userslist/search", method = RequestMethod.GET)
    public ModelAndView findALL(String key, Model model) {
        List<Users> listUsers;
        if (key.equals("")) {
            listUsers = usersDemo.listUsers();
        } else {
            listUsers = usersDemo.findAll(key);
        }

        model.addAttribute("greeting", "Users list");
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("size", listUsers.size());
        return new ModelAndView("home", "employee", new Users());

    }
}
