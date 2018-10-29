package slimetrail.web

import org.scalajs.dom._
import scala.scalajs.js
import slimetrail._

/** Tree strucure to represent the DOM */
sealed trait Html[+A] {

  def setToNode(element: Element,
                attribute: ((Option[String], String), String)): Unit = {
    attribute match {
      case ((Some(ns), name), value) => element.setAttributeNS(ns, name, value)
      case ((None, name), value)     => element.setAttribute(name, value)
    }
  }

  final def render: Node =
    this match {
      case ATextNode(value) => document.createTextNode(value)
      case AnElement(namespace, tag, attributes, eventListeners, children) =>
        val element = document.createElementNS(namespace, tag)
        attributes.foreach(a => setToNode(element, a))
        eventListeners.foreach {
          case (name, el) =>
            element.addEventListener(name, el)
        }
        children.map(_.render).foreach(element.appendChild)
        element
    }

  final def map[B](f: A => B): Html[B] =
    ??? // Replace by actual code
}

final case class ATextNode(value: String) extends Html[Nothing]

final case class AnElement[+A](
    namespace: String,
    tag: String,
    attributes: Map[(Option[String], String), String],
    eventListeners: List[(String, js.Function1[_ <: Event, A])],
    children: List[Html[A]]
) extends Html[A]

/** Generic Web Application */
trait WebApplication extends Application {

  def view(model: Model): Html[Msg]

  final def run(initialNode: Node): Unit =
    ??? // Replace by actual code
}

/** The Slimetrail Web Application */
final class SlimetrailWebApp(size: Int)
    extends SlimetrailApp(size)
    with WebApplication {

  def view(m: GameState): Html[Msg] =
    ??? // Replace by actual code
}

object Main {
  def onLoading(a: => Unit): Unit =
    document.addEventListener("DOMContentLoaded", (_: Event) => a)

  def main(args: Array[String]): Unit =
    onLoading {
      new SlimetrailWebApp(10)
        .run(document.getElementById("scalajs-controlled-node"))
    }
}
