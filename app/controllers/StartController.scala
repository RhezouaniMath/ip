package controllers

import app._
import play.api.Configuration
import play.api.http.HttpErrorHandler
import play.api.libs.json._
import play.api.mvc._

import javax.inject._

@Singleton
class StartController @Inject() (
                                  assets: Assets,
                                  errorHandler: HttpErrorHandler,
                                  config: Configuration,
                                  cc: ControllerComponents
                                ) extends AbstractController(cc) {

  def startGame = Action {
    request => {

      val json = request.body.asJson.get

      val aantalSpelers = (json \ "nrOfPlayers").as[Int]
      var speler = new Player(nrOfPlayers = aantalSpelers)
      var spel = new Game(player = speler)
      spel.scatterGooses()

      val gooses = spel.getGooses
      val finished = spel.getFinished
      val dice = spel.getDice

      var players = new Array[Player](aantalSpelers)
      var player = speler
      for (i <- 1 to aantalSpelers) {
        players(i - 1) = player
        player = player.getNextPlayer
      }

      var lastMove = new Array[Int](aantalSpelers)
      var position = new Array[Int](aantalSpelers)
      var turn = new Array[Boolean](aantalSpelers)
      var skipTurn = new Array[Boolean](aantalSpelers)
      var waitForPlayersBehind = new Array[Boolean](aantalSpelers)
      for (i <- 1 to aantalSpelers) {
        lastMove(i - 1) = players(i - 1).getLastMove
        position(i - 1) = players(i - 1).getPosition
        turn(i - 1) = players(i - 1).getTurn
        skipTurn(i - 1) = players(i - 1).getSkipTurn
        waitForPlayersBehind(i - 1) = players(i - 1).getWaitForPlayersBehind
      }

      val lastMove2 = lastMove.toList
      val position2 =position.toList
      val turn2 = turn.toList
      val skipTurn2 = skipTurn.toList
      val waitForPlayersBehind2 = waitForPlayersBehind.toList


      Ok(
        Json.obj(
          "dice" -> dice,
          "gooses" -> gooses,
          "finished" -> finished,
          "lastMove" -> lastMove2,
          "position" -> position2,
          "turn" -> turn2,
          "skipTurn" -> skipTurn2,
          "waitForPlayersBehind" -> waitForPlayersBehind2
        )
      )

    }

  }

}