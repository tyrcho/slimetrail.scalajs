package slimetrail.web

import org.scalajs.dom.Node
import slimetrail.Application

/** Generic Web Application */
trait WebApplication extends Application {

  def view(model: Model): Html[Msg]

  final def run(initialNode: Node): Unit = {
    initialNode.appendChild(view(initialModel).render)
    ()
  }
}
