package kz.bitlab.javaproeetwo.springdata.controllers;

import kz.bitlab.javaproeetwo.springdata.entities.Course;
import kz.bitlab.javaproeetwo.springdata.entities.Operator;
import kz.bitlab.javaproeetwo.springdata.entities.User;
import kz.bitlab.javaproeetwo.springdata.repositories.CourseRepository;
import kz.bitlab.javaproeetwo.springdata.repositories.OperatorRepository;
import kz.bitlab.javaproeetwo.springdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @GetMapping(value = "/")
    public String mainPage(Model model){
        List <Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        List<Operator> operators = operatorRepository.findAll();
        model.addAttribute("operators",operators);

        List <User> users = userRepository.findAll();
        model.addAttribute("adamdar", users);
        return "main";
    }

    @PostMapping(value = "/addOperator")
        public String addOperator(@RequestParam(name = "user_id") Long userId,
                                  @RequestParam(name = "operator_id") Long operatorId){
        User user = userRepository.findById(userId).orElseThrow();
        Operator operator = operatorRepository.findById(operatorId).orElseThrow();
        List <Operator> operators = user.getOperators();
        if (operators==null){
            operators = new ArrayList<>();
        }
        operators.add(operator);
        user.setOperators(operators);

        userRepository.save(user);
        return "redirect:/details/" + userId;
    }
    @GetMapping(value = "/addPage")
    public String addPage(Model model){
        List <Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        List<Operator> operators = operatorRepository.findAll();
        model.addAttribute("operators",operators);

        return "addPage";
    }
    @PostMapping(value = "/addPage")
    public String addPage(User user){
        userRepository.save(user);
        return "addPage";
    }
    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model){
        List <Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("polzovatel", user);
        List<Operator> operators = operatorRepository.findAll();


        if (user!=null){
            operators.removeAll(user.getOperators());
        }

        model.addAttribute("operators",operators);

        return "details";
    }
    @PostMapping(value = "/handle/{id}")
    public String handled(@PathVariable(name = "id") Long id){
        User user = userRepository.findById(id).orElse(null);
        user.setHandled(true);
        userRepository.save(user);
        return "redirect:/details/" + id;
    }
    @PostMapping(value = "/updatePage")
    public String updatePage(User user){
        User checkUser = userRepository.findById(user.getId()).orElse(null);
        Course course = courseRepository.findById(user.getCourse().getId()).orElse(null);

        if (checkUser!=null && course!=null){
            checkUser.setUserName(user.getUserName());
            checkUser.setComment(user.getComment());
            checkUser.setPhoneNumber(user.getPhoneNumber());
            checkUser.setHandled(true);
            checkUser.setCourse(course);
            userRepository.save(checkUser);
            return "redirect:/details/"+user.getId();
        }
        return "redirect:/";
    }
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "id") Long id){
        userRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping(value = "/handle")
    public String handle(Model model){
       List<User> users = userRepository.findByHandledIsTrue();
       model.addAttribute("handleUsers", users);
       return "/handle";
    }
    @GetMapping(value = "/notHandle")
    public String notHandle(Model model){
        List<User> users = userRepository.findByHandledFalse();
        model.addAttribute("notHandle",users);
        return "/nothandle";
    }
    @PostMapping(value = "/deleteOperator")
    public String deleteOperator(@RequestParam(name = "user_id") Long userId,
                              @RequestParam(name = "operator_id") Long operatorId){
        User user = userRepository.findById(userId).orElseThrow();
        Operator operator = operatorRepository.findById(operatorId).orElseThrow();
        List <Operator> operators = user.getOperators();
        if (operators==null){
            operators = new ArrayList<>();
        }
        operators.remove(operator);
        user.setOperators(operators);

        userRepository.save(user);
        return "redirect:/details/" + userId;
    }
}
