package part1recap

object GeneralRecap extends App{
  val aCondition: Boolean = false

  //expressions
  val aConditionedVal = if(aCondition) 42 else 65

  //code block
  val aCodeBlock = {
    if(aCondition) 74
    56
  }

  //types
  //Unit - Don't return anything
  val theUnit = println("Hello, Scala")
  def aFunction(x:Int) = x+1

  // recursion - TAIL recursion

  //OOP
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore{
    override def eat(a: Animal): Unit = println("crunch!")
  }


  //method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  //
}
