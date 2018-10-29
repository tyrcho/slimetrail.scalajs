package slimetrail.web

import slimetrail.{Cell, GameState, SlimetrailApp}

/** The Slimetrail Web Application */
final class SlimetrailWebApp(size: Int)
    extends SlimetrailApp(size)
    with WebApplication {

  def cellToSvg(cell: Cell): Html[Msg] =
    AnElement(
      tag = "use",
      attributes = Map(
        (Some("xlink"), "href") -> "#hexagon",
        (None, "x") -> cell.,

      )
    )

  //<use xlink:href="#hexagon" width="2" x="0" class="empty-cell" height="2" y="0"></use>

  val hexagon: Html[Nothing] = AnElement(
    tag = "symbol",
    attributes = Map(
      (None, "width") -> "2",
      (None, "height") -> "2",
      (None, "x") -> "-1",
      (None, "y") -> "-1",
      (None, "viewBox") -> "-1 -1 2 2",
      (None, "id") -> "hexagon"
    ),
    children = List(
      AnElement(
        tag = "polygon",
        attributes = Map(
          (None, "width") -> "2",
          (None, "height") -> "2",
          (None, "x") -> "-1",
          (None, "y") -> "-1",
          (None, "viewBox") -> "-1 -1 2 2",
          (None, "id") -> "hexagon"
        )
      )
    )
  )

  def view(m: GameState): Html[Msg] =
    AnElement(
      tag = "svg",
      children = List(
        hexagon,
        AnElement(
          tag = "g",
          children = List(
            AnElement(tag = "text", children = List(ATextNode(m.toString)))
          )
        )
      ) ++ m.grid.map(cellToSvg).toVector.toList
    )
}
