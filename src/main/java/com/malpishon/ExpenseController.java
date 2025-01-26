package com.malpishon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExpenseController {

    private final List<Expense> expenseList = new ArrayList<>();

    @GetMapping("/expenses")
    public String home(Model model) {
        model.addAttribute("expenses", expenseList.stream()
                .map(expense -> new Expense(expense.getName(), expense.getAmount(), expense.getType().toUpperCase()))
                .collect(Collectors.toList()));
        return "index";
    }

    @PostMapping("/addExpense")
    public String addExpense(@ModelAttribute Expense expense) {
        expenseList.add(expense);
        return "redirect:/expenses";
    }


}
