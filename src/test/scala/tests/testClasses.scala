package tests

object Main extends App {
  val guest1 = Guest(8)

  val order1 = Seq(
    Menu.cola,
    Menu.coffee,
    Menu.cheeseSandwich,
    Menu.steakSandwich,
    Menu.lobster
  )


  val table1 = TableTest(order1, guest1)
}

/**
 * A sealed trait, defining the characteristics
 * of a menu item
 */
sealed trait MenuItem{
  val cost: BigDecimal
  val name: String
  val isHot: Boolean
}

/**
 * When instantiated, the Drink class creates
 * a drink menu item
 * @param name name of the item
 * @param cost cost of the item
 * @param isHot temperature of the item
 */
case class Drink(name: String, cost: BigDecimal, isHot: Boolean ) extends MenuItem {

}

/**
 * When instantiated, the Food class creates
 * a food menu item
 * @param name name of the item
 * @param cost cost of the item
 * @param isHot temperature of the item
 * @param isPremium value of the item
 */
case class Food(name: String, cost: BigDecimal, isHot: Boolean, isPremium: Boolean ) extends MenuItem {
}



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

/**
 * When instantiated, the guest class creates
 * a guest with x number of loyalty points
 * @param loyaltyPoints number of loyalty points a guest has
 */
case class Guest(loyaltyPoints: Int)


