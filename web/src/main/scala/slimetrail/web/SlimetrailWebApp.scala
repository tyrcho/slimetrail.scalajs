package slimetrail.web

import slimetrail.{GameState, SlimetrailApp}

/** The Slimetrail Web Application */
final class SlimetrailWebApp(size: Int)
    extends SlimetrailApp(size)
    with WebApplication {

  def view(m: GameState): Html[Msg] =
    ??? // Replace by actual code
}
