package main

import java.time.LocalDateTime

/**
 * When instantiated, the Bill class takes
 * a table order and generates a bill.
 * Depending on whether the guest has a loyalty points,
 * it's happy hour, they order hot or cold food,
 * drinks or premium items, the total will change.
 *
 * @param order list of menu items
 * @param points number of loyalty points
 */
case class Bill(order: Seq[MenuItem], points: Int) {

  private val hr = LocalDateTime.now().getHour

  /**
   * Method for checking whether the order
   * occurred during happy hour
   * @return
   */
  private def isHappyHour = if (hr >= 6 && hr <= 13) true else false

  /**
   * Method for conducting the arithmetic for the bill
   */
  def calculateBill(): Unit = {

    val nonPremItems = order.filter {
      case Food(_, _, _, false) => true
      case Drink(_, _, _) => true
      case _ => false
    }
    val premItems = order.filter {
      case Food(_, _, _, true) => true
      case _ => false
    }

    val initialItemsTotal = order.map(_.cost).sum

    val discountedItemsTotal = if (isHappyHour) {
      loyaltyDiscount(happyHourDiscount(nonPremItems))
    } else loyaltyDiscount(nonPremItems)

    val premItemsTotal = premItems.map(_.cost).sum

    val finalItemsTotal = discountedItemsTotal + premItemsTotal

    val serviceCharge: BigDecimal = {
      if (onlyDrink) {
        calcServiceCharge(finalItemsTotal, 0)
      }
      else if (isPremium) {
        calcServiceCharge(finalItemsTotal, 25)
      }
      else if (hasHotFood) {
        calcServiceCharge(finalItemsTotal, 20)
      }
      else {
        calcServiceCharge(finalItemsTotal, 10)
      }
    }

    printBill(finalItemsTotal, serviceCharge, discountedItemsTotal, initialItemsTotal)
  }

  /**
   * Method for calculating the service charge
   *
   * @param itemsTotal sum of order items after discounts applied
   * @param serviceChargePercentage the percentage of service charge applied to order
   * @return service charge
   */
  private def calcServiceCharge(itemsTotal: BigDecimal, serviceChargePercentage: BigDecimal): BigDecimal = {
    if (((serviceChargePercentage / 100) * itemsTotal > 40) && isPremium) 40
    else if ((serviceChargePercentage / 100) * itemsTotal > 20) 20
    else (serviceChargePercentage / 100) * itemsTotal

  }

  /**
   * Method for calculating the sum of the non-premium
   * items after the loyalty discount is applied
   *
   * @param nonPrem list of non premium items
   * @return sum of the discounted non-premium items
   */
  private def loyaltyDiscount(nonPrem: Seq[MenuItem]) = {
    nonPrem.map { item =>
      item.cost - (((2.5 * points) / 100) * item.cost)
    }.sum
  }

  /**
   * Method to calculate the happy hour discount
   * if the time is between 9pm-6pm
   *
   * @param nonPrem list of non premium items
   * @return sum of the discounted items
   */
  private def happyHourDiscount(nonPrem: Seq[MenuItem]): Seq[MenuItem] = {
    nonPrem.map {
      case item@Drink(_, _, _) => item.copy(cost = item.cost / 2)
      case item => item
    }
  }

  /**
   * Method that checks whether the order only
   * consists of drinks
   *
   * @return true or false
   */
  private def onlyDrink = order.forall({
    case Drink(_, _, _) => true
    case _ => false
  })

  /**
   * Method that checks whether the order has
   * hot food
   *
   * @return true or false
   */
  private def hasHotFood = order.exists({
    case Food(_, _, true, _) => true
    case _ => false
  })

  /**
   * Method that checks whether the order
   * has premium items
   *
   * @return true or false
   */
  private def isPremium = order.exists({
    case Food(_, _, _, true) => true
    case _ => false
  })

  /**
   * Method that prints the bill summary to
   * the terminal
   *
   * @param finalTotal sum of order price before service charge is applied
   * @param totalServiceCharge total service charge cost
   * @param discountedTotal total cost after discounts are applied
   * @param initialItemsTotal total cost before any discount or service charge is applied
   */
  private def printBill(finalTotal: BigDecimal, totalServiceCharge: BigDecimal, discountedTotal: BigDecimal, initialItemsTotal: BigDecimal): Unit = {
    println("--------Bill--------")
    order.foreach(item => {
      print(f"${item.name}: £")
      println(f"${item.cost}%.2f")
    })

    println("\n-------------------\n")
    println(f"Total Before Discounts and Service Charge: £$initialItemsTotal%.2f")

    println("\n---------Discount----------\n")

    println(f"Loyalty Points: $points")
    println(f"Happy Hour?: ${if (isHappyHour) "Yes" else "no"}")
    println(f"Amount Deducted From Total: £${finalTotal - initialItemsTotal }%.2f\n")

    println("---------Summary----------\n")

    println(f"Service charge: £$totalServiceCharge%.2f")
    println(f"Total: £${finalTotal + totalServiceCharge}%.2f\n")

    println("--------------------")

  }

}
