package main

//trait menu with regional menus

/**
 * An object instantiated once, creating a collection of
 * menu items
 */
case object Menu {

    val cola: Drink = Drink("Cola", 0.50, isHot =  false )
    val coffee: Drink = Drink("Coffee", 1.00, isHot =  true )
    val cheeseSandwich: Food = Food("Cheese Sandwich", 5.00, isHot =  false, isPremium = false )
    val steakSandwich: Food = Food("Steak Sandwich", 4.50,  isHot = true, isPremium = false )
    val lobster: Food = Food("Lobster", 250.00, isHot = true, isPremium = true)


}


