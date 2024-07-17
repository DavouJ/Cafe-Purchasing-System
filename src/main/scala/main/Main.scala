package main

/**
 * Main driver object
 */
object Main extends App{


  val guest1 = Guest(8)

  val order1 = Seq(
    Menu.cola,
    Menu.coffee,
    Menu.cheeseSandwich,
    Menu.steakSandwich,
    Menu.lobster
  )


  val table1 = Table(order1, guest1)


}
