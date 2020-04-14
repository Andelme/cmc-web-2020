package spring.controller;

import dao.WorkerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonnelController {
    @Autowired
    private WorkerDAO workerDAO;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listEmployees(ModelMap map)
    {
        map.addAttribute("workerList", workerDAO.getAll());
        return "index";
    }
}

