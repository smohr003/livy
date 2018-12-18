package sample

import org.apache.livy._


class LivyTest2(ar: Double) extends Job[Double]{

  @Override
  def call(ctx: JobContext): Double = ar * ar;


}
