package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.entity.Department;
import spring.entity.PositionType;
import spring.entity.WorkPosition;
import spring.entity.Worker;
import spring.service.DepartmentService;
import spring.service.PositionTypeService;
import spring.service.WorkPositionService;
import spring.service.WorkerService;

import java.sql.Date;

@Controller
public class MainController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionTypeService positionTypeService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorkPositionService workPositionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listDepartment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("departmentList", departmentService.getHeadDepartments());
        return modelAndView;
    }
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ModelAndView departmentPage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("department");
        modelAndView.addObject("department", departmentService.getByID(id));
        modelAndView.addObject("workerList", departmentService.getDepartmentWorkers(id));
        modelAndView.addObject("vacancyList", departmentService.getDepartmentVacancy(id));
        modelAndView.addObject("spareWorkerList", workerService.getAll());
        modelAndView.addObject("postypeList", positionTypeService.getAll());
        modelAndView.addObject("newPos", new WorkPosition());
        return modelAndView;
    }
    @RequestMapping(value = "/department_add", method = RequestMethod.GET)
    public ModelAndView addDepartmentPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editDepartment");
        modelAndView.addObject("department", new Department());
        modelAndView.addObject("departmentList", departmentService.getAll());
        return modelAndView;
    }
    @RequestMapping(value = "/department_add", method = RequestMethod.POST)
    public ModelAndView addDepartment(@ModelAttribute("department") Department department, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        departmentService.save(department);
        modelAndView.setViewName("redirect:department?id=" + department.getDepartment_id());
        return modelAndView;
    }
    @RequestMapping(value = "/department_edit", method = RequestMethod.GET)
    public ModelAndView editDepartmentPage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editDepartment");
        modelAndView.addObject("department", departmentService.getByID(id));
        modelAndView.addObject("departmentList", departmentService.getAll());
        return modelAndView;
    }
    @RequestMapping(value = "/department_edit", method = RequestMethod.POST)
    public ModelAndView editDepartment(@ModelAttribute("department") Department department, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        departmentService.update(department);
        modelAndView.setViewName("redirect:department?id=" + department.getDepartment_id());
        return modelAndView;
    }
    @RequestMapping(value = "/department_delete", method = RequestMethod.GET)
    public ModelAndView deleteDepartment(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:");
        departmentService.delete(departmentService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/appoint", method = RequestMethod.POST)
    public ModelAndView appointWorker(@RequestParam(value="dep", required = false) Long department_id,
                                      @ModelAttribute("workPosition") WorkPosition workPosition, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (workPosition.getWorker_id() == null) {
            if (department_id != null) modelAndView.setViewName("redirect:department?id=" + department_id);
            else modelAndView.setViewName("redirect:vacancy");
            return modelAndView;
        }
        workPositionService.unbindWorker(workPosition.getWorker_id().getWorker_id());
        workPosition.setAppointment_date(new Date(System.currentTimeMillis()));
        workPositionService.update(workPosition);
        if (department_id != null) modelAndView.setViewName("redirect:department?id=" + department_id);
        else modelAndView.setViewName("redirect:vacancy");
        return modelAndView;
    }
    @RequestMapping(value = "/position_add", method = RequestMethod.POST)
    public ModelAndView addDepartment(@RequestParam(value="dep", required = false) Long department_id,
                                      @ModelAttribute("newPos") WorkPosition newPos, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (newPos.getWorker_id() != null) {
            workPositionService.unbindWorker(newPos.getWorker_id().getWorker_id());
            newPos.setAppointment_date(new Date(System.currentTimeMillis()));
        }
        workPositionService.save(newPos);
        if (department_id != null) modelAndView.setViewName("redirect:department?id=" + department_id);
        else modelAndView.setViewName("redirect:vacancy");
        return modelAndView;
    }
    @RequestMapping(value = "/position_delete", method = RequestMethod.GET)
    public ModelAndView deleteWorkPosition(@RequestParam(value="dep", required = false) Long department_id,
                                         @RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        workPositionService.delete(workPositionService.getByID(id));
        if (department_id != null) modelAndView.setViewName("redirect:department?id=" + department_id);
        else modelAndView.setViewName("redirect:vacancy");
        return modelAndView;
    }
    @RequestMapping(value = "/retire", method = RequestMethod.GET)
    public ModelAndView retireWorker(@RequestParam(value="dep", required = false) Long department_id,
                                     @RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        workPositionService.unbindWorker(workPositionService.getByID(id).getWorker_id().getWorker_id());
        if (department_id != null) modelAndView.setViewName("redirect:department?id=" + department_id);
        else modelAndView.setViewName("redirect:vacancy");
        return modelAndView;
    }
    @RequestMapping(value = "/postypes", method = RequestMethod.GET)
    public ModelAndView allPostypes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postypes");
        modelAndView.addObject("postypeList", positionTypeService.getAll());
        return modelAndView;
    }
    @RequestMapping(value = "/postype", method = RequestMethod.GET)
    public ModelAndView postypePage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postype");
        modelAndView.addObject("positionType", positionTypeService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/postype_add", method = RequestMethod.GET)
    public ModelAndView addPostypePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPostype");
        modelAndView.addObject("positionType", new PositionType());
        return modelAndView;
    }
    @RequestMapping(value = "/postype_add", method = RequestMethod.POST)
    public ModelAndView addPostype(@ModelAttribute("positionType") PositionType positionType, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        positionTypeService.save(positionType);
        modelAndView.setViewName("redirect:postype?id=" + positionType.getPostype_id());
        return modelAndView;
    }
    @RequestMapping(value = "/postype_edit", method = RequestMethod.GET)
    public ModelAndView editPostypePage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPostype");
        modelAndView.addObject("positionType", positionTypeService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/postype_edit", method = RequestMethod.POST)
    public ModelAndView editPostype(@ModelAttribute("positionType") PositionType positionType, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        positionTypeService.update(positionType);
        modelAndView.setViewName("redirect:postype?id=" + positionType.getPostype_id());
        return modelAndView;
    }
    @RequestMapping(value = "/postype_delete", method = RequestMethod.GET)
    public ModelAndView deletePostype(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:postypes");
        positionTypeService.delete(positionTypeService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    public ModelAndView allWorkers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("workers");
        modelAndView.addObject("workerList", workPositionService.getCurrentPositions());
        modelAndView.addObject("spareWorkerList", workerService.getSpareWorkers());
        return modelAndView;
    }
    @RequestMapping(value = "/worker", method = RequestMethod.GET)
    public ModelAndView workerPage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("worker");
        Worker worker = workerService.getByID(id);
        modelAndView.addObject("worker", worker);
        modelAndView.addObject("currentPosition", workPositionService.getWorkerCurrent(worker.getWorker_id()));
        modelAndView.addObject("positionHistory", workPositionService.getWorkerHistory(worker.getWorker_id()));
        return modelAndView;
    }
    @RequestMapping(value = "/worker_add", method = RequestMethod.GET)
    public ModelAndView addWorkerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editWorker");
        modelAndView.addObject("worker", new Worker());
        return modelAndView;
    }
    @RequestMapping(value = "/worker_add", method = RequestMethod.POST)
    public ModelAndView addWorker(@ModelAttribute("worker") Worker worker, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        worker.setHire_date(new Date(System.currentTimeMillis()));
        workerService.save(worker);
        modelAndView.setViewName("redirect:worker?id=" + worker.getWorker_id());
        return modelAndView;
    }
    @RequestMapping(value = "/worker_edit", method = RequestMethod.GET)
    public ModelAndView editWorkerPage(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editWorker");
        modelAndView.addObject("worker", workerService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/worker_edit", method = RequestMethod.POST)
    public ModelAndView editWorker(@ModelAttribute("worker") Worker worker, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        workerService.update(worker);
        modelAndView.setViewName("redirect:worker?id=" + worker.getWorker_id());
        return modelAndView;
    }
    @RequestMapping(value = "/worker_delete", method = RequestMethod.GET)
    public ModelAndView deleteWorker(@RequestParam(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:workers");
        workerService.delete(workerService.getByID(id));
        return modelAndView;
    }
    @RequestMapping(value = "/vacancy", method = RequestMethod.GET)
    public ModelAndView vacancyList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancy");
        modelAndView.addObject("vacancyList", workPositionService.getVacancy());
        modelAndView.addObject("departmentList", departmentService.getAll());
        modelAndView.addObject("postypeList", positionTypeService.getAll());
        modelAndView.addObject("newVacancy", new WorkPosition());
        return modelAndView;
    }
}
