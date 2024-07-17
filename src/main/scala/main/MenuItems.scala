package main

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
 * When instatiated, the Food class creates
 * a food menu item
 * @param name name of the item
 * @param cost cost of the item
 * @param isHot temperature of the item
 * @param isPremium value of the item
 */
case class Food(name: String, cost: BigDecimal, isHot: Boolean, isPremium: Boolean ) extends MenuItem {

}

