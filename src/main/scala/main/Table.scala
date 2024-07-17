package main

/**
 * When instantiated, the Table class creates
 * a table which can be assigned a bill
 * @param order a list of menu items ordered
 * @param guest the guest who booked the table
 */
case class Table(order: Seq[MenuItem], guest: Guest){

  val points: Int = if(guest.loyaltyPoints > 8 || guest.loyaltyPoints < 0) throw new IllegalArgumentException("Invalid number of points")
  else if(guest.loyaltyPoints < 3)  0
  else guest.loyaltyPoints

  val bill1 = Bill(order: Seq[MenuItem], points )

  bill1.calculateBill()

}

