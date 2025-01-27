package com.malpishon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {

    private final List<Expense> expenseList = new ArrayList<>();

    @GetMapping("/expenses")
    public String home(Model model) {
        model.addAttribute("expenses", expenseList);
        return "index";
    }

    @PostMapping("/addExpense")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseList.add(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String name) {
        expenseList.removeIf(expense -> expense.getName().equalsIgnoreCase(name));
        return "redirect:/expenses";
    }

    @GetMapping("/editExpense")
    public String editExpense(@RequestParam String name, Model model) {
        Expense expenseToEdit = expenseList.stream()
                .filter(expense -> expense.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        model.addAttribute("expense", expenseToEdit);
        return "editExpense";
    }

    @PostMapping("/updateExpense")
    public String updateExpense(@ModelAttribute Expense updatedExpense) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getName().equalsIgnoreCase(updatedExpense.getName())) {
                expenseList.set(i, updatedExpense);
                break;
            }
        }
        return "redirect:/expenses";
    }
}
