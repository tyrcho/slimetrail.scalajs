package slimetrail.web

import org.scalajs.dom.{Element, Event, Node, document}

import scala.scalajs.js

/** Tree strucure to represent the DOM */
sealed trait Html[+A] {
  def render: Node
}

final case class ATextNode(value: String) extends Html[Nothing] {
  override def render: Node = document.createTextNode(value)
}

final case class AnElement[+A](
    namespace: String,
    tag: String,
    attributes: Map[(Option[String], String), String],
    eventListeners: List[(String, js.Function1[_ <: Event, A])],
    children: List[Html[A]]
) extends Html[A] {

  override def render: Node = {
    val element = document.createElementNS(namespace, tag)
    attributes.foreach(a => setToNode(element, a))
    eventListeners.foreach {
      case (name, el) =>
        element.addEventListener(name, el)
    }
    children.map(_.render).foreach(element.appendChild)
    element
  }

  private def setToNode(element: Element,
                        attribute: ((Option[String], String), String)): Unit = {
    attribute match {
      case ((Some(ns), name), value) => element.setAttributeNS(ns, name, value)
      case ((None, name), value)     => element.setAttribute(name, value)
    }
  }

}
