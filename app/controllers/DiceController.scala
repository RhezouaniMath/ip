package controllers

import app._

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

import util.Random

@Singleton
class DiceController @Inject() (cc: ControllerComponents)
  extends AbstractController(cc) {

  def ThrowDice = Action {
      val speler = new Player()
      val game = new Game(player = speler)
      val number = Random.nextInt(6)
      val newnumber = number + 1
      Ok(Json.obj("dice" -> String.valueOf(newnumber)))
  }
}