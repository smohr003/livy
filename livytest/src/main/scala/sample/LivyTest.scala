package sample


import org.apache.livy._


class LivyTest extends Job[Double]{

  def main(args: Array[String]): Unit = {

  }

  @Override
  def call(ctx: JobContext): Double = 3.1415

}
