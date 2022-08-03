package controllers

import javax.inject._

import play.api.Configuration
import play.api.http.HttpErrorHandler
import play.api.libs.json._
import play.api.mvc._

@Singleton
class CountController @Inject() (
    assets: Assets,
    errorHandler: HttpErrorHandler,
    config: Configuration,
    cc: ControllerComponents
) extends AbstractController(cc) {

  def counterUp = Action {
    request => {
      val json = request.body.asJson.get
      val count = (json \ "counter").as[Int]
      val newCount = count + 1
      Ok(
        Json.obj(
          "counter" -> String.valueOf(newCount),
          "henk" -> "Wow dit werkt misschien"
        )
      )
    }
  }
}