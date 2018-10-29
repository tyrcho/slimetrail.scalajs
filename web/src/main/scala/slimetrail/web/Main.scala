package slimetrail.web

import org.scalajs.dom.{Event, document}

object Main {
  def onLoading(a: => Unit): Unit =
    document.addEventListener("DOMContentLoaded", (_: Event) => a)

  def main(args: Array[String]): Unit =
    onLoading {
      new SlimetrailWebApp(10)
        .run(document.getElementById("scalajs-controlled-node"))
    }
}
