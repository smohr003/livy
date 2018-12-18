package sample

class FactoryOutside {

  def f1(s: String): Int = {
    println("Hello from factory" + s)
    return 1
  }

}
