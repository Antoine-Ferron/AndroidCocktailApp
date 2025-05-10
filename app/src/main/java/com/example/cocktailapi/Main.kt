package com.example.cocktailapi

import com.example.cocktailapi.model.CocktailRepository.getCocktailByAlcoholic
import com.example.cocktailapi.model.CocktailRepository.getCocktailByCategory
import com.example.cocktailapi.model.CocktailRepository.getCocktailByFirstLetter
import com.example.cocktailapi.model.CocktailRepository.getCocktailByGlass
import com.example.cocktailapi.model.CocktailRepository.getCocktailById
import com.example.cocktailapi.model.CocktailRepository.getCocktailByIngredient
import com.example.cocktailapi.model.CocktailRepository.getCocktailByName
import com.example.cocktailapi.model.CocktailRepository.getRandomCocktail
import com.example.cocktailapi.model.DrinksBean
import com.example.cocktailapi.model.SmallDrinksBean

fun main() {
    println("######################################################")
    println("######################################################\n")
    println("Cocktails par nom\n")
    println("######################################################\n")
    val cocktailsByName: DrinksBean? = getCocktailByName("Margarita")

    if (cocktailsByName != null && !cocktailsByName.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByName.drinks.size}")
        println(".....................................................................\n")
        cocktailsByName.drinks
            .forEach { cocktail ->
                println("Cocktail : ${cocktail.strDrink}")
                println("Description : ${cocktail.strInstructionsFR}")
                println("\n--------------------------------------------------------------------------\n")

            }
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par lettre\n")
    println("######################################################\n")

    val cocktailsByLetter: DrinksBean? = getCocktailByFirstLetter('a')

    if (cocktailsByLetter != null && !cocktailsByLetter.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByLetter?.drinks?.size}")
        println(".....................................................................\n")
        cocktailsByLetter?.drinks
            ?.forEach { cocktail ->
                println("Cocktail : ${cocktail.strDrink}")
                println("Description : ${cocktail.strInstructionsFR}")
                println("\n--------------------------------------------------------------------------\n")
            }
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par id\n")
    println("######################################################\n")
    val cocktailsById: DrinksBean? = getCocktailById("11007")
    if (cocktailsById != null && !cocktailsById.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsById.drinks.size}")
        println(".....................................................................\n")
        cocktailsById.drinks
            .forEach { cocktail ->
                println("Cocktail : ${cocktail.strDrink}")
                println("Description : ${cocktail.strInstructionsFR}")
                println("\n--------------------------------------------------------------------------\n")
            }
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails au hasard\n")
    println("######################################################\n")
    val cocktailsRandom: DrinksBean? = getRandomCocktail()
    if (cocktailsRandom != null && !cocktailsRandom.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsRandom.drinks.size}")
        println(".....................................................................\n")
        cocktailsRandom.drinks
            .forEach { cocktail ->
                println("Cocktail : ${cocktail.strDrink}")
                println("Description : ${cocktail.strInstructionsFR}")
                println("\n--------------------------------------------------------------------------\n")
            }
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par ingrédient\n")
    println("######################################################\n")
    val cocktailsByIngredient: SmallDrinksBean? = getCocktailByIngredient("Margarita")
    if (cocktailsByIngredient != null && !cocktailsByIngredient.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByIngredient.drinks.size}")
        println(".....................................................................\n")
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par catégorie\n")
    println("######################################################\n")
    val cocktailsByCategory: SmallDrinksBean? = getCocktailByCategory("Ordinary Drink")
    if (cocktailsByCategory != null && !cocktailsByCategory.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByCategory.drinks.size}")
        println(".....................................................................\n")
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par verre\n")
    println("######################################################\n")
    val cocktailsByGlass: SmallDrinksBean? = getCocktailByGlass("Cocktail glass")
    if (cocktailsByGlass != null && !cocktailsByGlass.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByGlass.drinks.size}")
        println(".....................................................................\n")
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

    println("######################################################")
    println("######################################################\n")
    println("Cocktails par alcool\n")
    println("######################################################\n")
    val cocktailsByAlcoholic: SmallDrinksBean? = getCocktailByAlcoholic("Alcoholic")
    if (cocktailsByAlcoholic != null && !cocktailsByAlcoholic.drinks.isNullOrEmpty()) {
        println(".....................................................................")
        println(". Nombre de cocktails : ${cocktailsByAlcoholic.drinks.size}")
        println(".....................................................................\n")
    } else {
        println("Aucun cocktail trouvé")
        println(".....................................................................")
    }

}