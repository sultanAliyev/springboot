package kz.bitlab.springboot.controllers;


import kz.bitlab.springboot.db.DBManager;
import kz.bitlab.springboot.db.Tasks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model){

        ArrayList<Tasks> tasks = DBManager.getAllItems();
        model.addAttribute("tovary", tasks);

        return "index";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }

    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "name")String name,
                          @RequestParam(name = "completed") String completed,
                          @RequestParam(name = "date") String date){

        Tasks it = new Tasks();
        it.setName(name);
        it.setDate(date);
        it.setCompleted(completed);

        DBManager.addItems(it);

        return "redirect:/?success";

    }

    @GetMapping(value = "/details/{itemId}")
    public String getItem(Model model, @PathVariable(name = "itemId") Long id){

        Tasks tasks = DBManager.getItem(id);
        model.addAttribute("tasks", tasks);

        return "details";
    }

    @PostMapping(value = "/deleteitem/{itemId}")
    public String delete(Model model, @PathVariable(name = "itemId") Long id){
        try{
            DBManager.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }



        return "redirect:/?success";
    }


    @PostMapping(value = "/saveitem/{itemId}")
    public String saveitem(@RequestParam(name = "name")String name,
                            @RequestParam(name = "completed") String completed,
                            @RequestParam(name = "date") String date){

        Tasks tasks = new Tasks();
        tasks.setName(name);
        tasks.setDate(date);
        tasks.setCompleted(completed);



        return "redirect:/?success";
    }


    @PostMapping(value = "/savetask", params = "action=save")
    public String saveTask(@RequestParam(name = "id") Long id,
                           @RequestParam(name="name") String name_,
                           @RequestParam(name="deadlineDate") String deadlineDateString,
                           @RequestParam(name="isCompleted") String isCompleted){

            Tasks task  = new Tasks(id, name_, deadlineDateString, isCompleted);
            DBManager.saveTask(task);


        return "redirect:/?success";
    }

    @PostMapping(value = "/savetask", params = "action=delete")
    public String deleteTask(@RequestParam(name = "id") Long id){
            try {
                DBManager.delete(id);
            }catch (Exception e){
                e.printStackTrace();
            }

        return "redirect:/?success";

    }


}
