package com.example.lab20192733v4.controller;


import com.example.lab20192733v4.entity.FavoriteMeal;
import com.example.lab20192733v4.repository.FavoriteMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    MealController mealController;

    @Autowired
    FavoriteMealRepository favoriteMealRepository;

    public MainController() {
    }

    @GetMapping({"/mealCategories"})
    public String showCocktails(Model model) {
        List<List<String>> categories = this.mealController.listar();
        model.addAttribute("categories", categories);
        return "categories";
    }
    @GetMapping({"/favsList"})
    public String showFavs(Model model){
        List<FavoriteMeal> favMeals = favoriteMealRepository.findAll();
        model.addAttribute("FavMeals", favMeals);
        return "myFavs";
    }


    @GetMapping({"/buscar"})
    public String searchMeals(Model model, @RequestParam("query") String query) {
        List<List<String>> meals = this.mealController.buscar(query);
        model.addAttribute("meals", meals);
        return "meals";
    }


    @PostMapping({"/addFav"})
    public String addFav(@RequestParam List<String> favmeal, RedirectAttributes redirectAttributes, Model model){
        FavoriteMeal comidafavorita = new FavoriteMeal();
        comidafavorita.setStrMeal(favmeal.get(0));
        comidafavorita.setStrMealThumb(favmeal.get(1));
        comidafavorita.setStrCategory(favmeal.get(2));
        comidafavorita.setStrArea(favmeal.get(3));
        comidafavorita.setStrTags(favmeal.get(4));
        comidafavorita.setStrInstructions(favmeal.get(5));
        comidafavorita.setStrIngredient1(favmeal.get(6));
        comidafavorita.setStrMeasure1(favmeal.get(7));
        comidafavorita.setStrIngredient2(favmeal.get(8));
        comidafavorita.setStrMeasure2(favmeal.get(9));
        comidafavorita.setStrIngredient3(favmeal.get(10));
        comidafavorita.setStrMeasure3(favmeal.get(11));
        comidafavorita.setStrIngredient4(favmeal.get(12));
        comidafavorita.setIdMeal(favmeal.get(13));
        System.out.println(favmeal.get(11));
        System.out.println(favmeal.get(12));
        System.out.println(favmeal.get(13));

        favoriteMealRepository.save(comidafavorita);

        redirectAttributes.addFlashAttribute("showModal", true);
        model.addAttribute("isFavorite", true);

        return "redirect:/info?id=" + favmeal.get(13);
    }

}