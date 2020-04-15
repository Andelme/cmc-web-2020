package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.service.DepartmentService;
import spring.service.PositionTypeService;
import spring.service.WorkPositionService;
import spring.service.WorkerService;

@Controller
public class PersonnelController {

    private DepartmentService departmentService;
    private PositionTypeService positionTypeService;
    private WorkerService workerService;
    private WorkPositionService workPositionService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setPositionTypeService(PositionTypeService positionTypeService) {
        this.positionTypeService = positionTypeService;
    }

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Autowired
    public void setWorkPositionService(WorkPositionService workPositionService) {
        this.workPositionService = workPositionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listEmployees() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("workerList", workerService.getAll());
        return modelAndView;
    }
}

