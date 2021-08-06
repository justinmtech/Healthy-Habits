package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.LoggerService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("ALL")
@Controller
public class GraphController {
    private List<String> habitList;
    private List<List> habitDataContainer;
    private List<ArrayList<Integer>> habitDataList2;
    //private List<List> habitData;
    //private Map<Long, Integer> habitDataList = new HashMap<Long, Integer>();

    @Autowired
    LoggerService logger;

    @Autowired
    SessionService sessionService;

    @GetMapping("/graph")
    public String graph(Model model) throws ParseException {
        habitList = new ArrayList<>();
        habitDataContainer = new ArrayList<>();
        habitDataList2 = new ArrayList<>();
        getUserHabitInfo();
        addModelAttributes(model);
        return "graph";
    }

    private void getUserHabitInfo() {
        try {
            User user = getSiteUser();
            for (int i = 0; i < user.getHabits().size(); i++) {
                habitList.add(user.getHabits().get(i).getName());
                for (int j = 0; j < user.getHabits().get(i).getDates().size(); j++) {
                    ArrayList habitData = new ArrayList();
                    long date = user.getHabits().get(i).getDates().get(j);
                    int rating = user.getHabits().get(i).getRatings().get(j);
                    habitData.add(date);
                    habitData.add(rating);
                //ArrayList dataTest = new ArrayList();
                //dataTest.add(habitData);
                habitDataList2.add(habitData);
                }
                habitDataContainer.add(habitDataList2);
            }
                System.out.println("habitDataList2: " + habitDataContainer);
            //habitData.add(habitDataList2);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    private User getSiteUser() throws NoSuchElementException {
        User user = new User();
        try {
            user = sessionService.getAllSessions().stream().findFirst().orElseThrow().getSiteUser();
        }
        catch (NoSuchElementException e) {
            logger.addToLog("User not found", true);
        }
        return user;
    }

    private void addModelAttributes(Model model) {
        model.addAttribute("habitList", habitList);
        model.addAttribute("habitDataList", habitDataList2);
        model.addAttribute("userHabits", getSiteUser().getHabits());

        //One if I just used one model, the site user? And all of it was
        //organized and separated into objects in Javascript.

        //model.addAttribute("habitList", getSiteUser().getHabits());
    }

    private long convertDateToMilliseconds(String date) throws ParseException {
        Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return newDate.getTime();
        }
}
