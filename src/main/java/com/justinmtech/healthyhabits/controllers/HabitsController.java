package com.justinmtech.healthyhabits.controllers;

import com.justinmtech.healthyhabits.services.CustomUserDetailsService;
import com.justinmtech.healthyhabits.services.DataValidation;
import com.justinmtech.healthyhabits.services.UserService;
import com.justinmtech.healthyhabits.user.Habit;
import com.justinmtech.healthyhabits.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class HabitsController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService userd;

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/habits")
    public String habitForm(Model model) {
        model.addAttribute("habit", new Habit());
        model.addAttribute("user", getUser());
        return "habitspage";
    }

    /**
     * @param habit The Habit containing the model attributes
     * @param model The model which attributes are added to
     * @return The page ID the response should return
     */
    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habit habit, Model model) {
        habit.setName(habit.getName().trim());

        model.addAttribute("habit", habit);
        model.addAttribute("user", getUser());

        String habitSubmissionType = habit.getHabitType();
        boolean habitExists = getUser().hasHabit(habit.getName());

        return getResponse(habit, habitSubmissionType, habitExists);
    }

    private String getResponse(@NotNull Habit habit, @NotNull String habitSubmissionType, boolean habitExists) {
        try {
            if (isHabitInvalid(habit)) return "errorpage";

            if (addingSubmission(habitSubmissionType)) {
                if (habitExists) {
                    getUser().saveHabit(habit);
                } else {
                    getUser().addHabit(habit);
                }
            } else if (removingSubmission(habitSubmissionType)) {
                getUser().removeHabit(habit.getName());
            }
            getUserService().saveUser(getUser());
        } catch (NullPointerException | NoSuchElementException e) {
            return "errorpage";
        }
        return "habitspage";
    }

    private boolean isHabitInvalid(@NotNull Habit habit) {
        return !DataValidation.isHabitValid(habit);
    }

    private boolean addingSubmission(@NotNull String habitSubmissionType) {
        return habitSubmissionType.equals("add");
    }

    private boolean removingSubmission(@NotNull String habitSubmissionType) {
        return habitSubmissionType.equals("remove");
    }

    private UserService getUserService() {
        return userService;
    }

    private CustomUserDetailsService getUserd() {
        return userd;
    }

    private User getUser() {
        return getUserd().getUser();
    }
}