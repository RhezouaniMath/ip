/*package controllers

import javax.inject._

import play.api.Configuration
import play.api.http.HttpErrorHandler
import play.api.libs.json._
import play.api.mvc._*/


package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._
import util.Random

@Singleton
class DiceController @Inject() (cc: ControllerComponents)
  extends AbstractController(cc) {

  def ThrowDice = Action {
      val number = Random.nextInt(6)
      val newnumber = number + 1
      Ok(Json.obj("dice" -> String.valueOf(newnumber)))
    }
  }