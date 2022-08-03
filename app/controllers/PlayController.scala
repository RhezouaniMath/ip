package controllers

import app._
import play.api.Configuration
import play.api.http.HttpErrorHandler
import play.api.libs.json._
import play.api.mvc._

import javax.inject._

@Singleton
class PlayController @Inject() (
                                  assets: Assets,
                                  errorHandler: HttpErrorHandler,
                                  config: Configuration,
                                  cc: ControllerComponents
                                ) extends AbstractController(cc) {

  def playGame = Action {

    request => {

      val json = request.body.asJson.get

      val goosesList = (json \ "gooses").as[List[Int]]
      val lastMove0 = (json \ "lastMove").as[List[Int]]
      val position0 = (json \ "position").as[List[Int]]
      val turn0 = (json \ "turn").as[List[Boolean]]
      val skipTurn0 = (json \ "skipTurn").as[List[Boolean]]
      val waitForPlayersBehind0 = (json \ "waitForPlayersBehind").as[List[Boolean]]

      var lastMove = lastMove0.toArray
      var position = position0.toArray
      var turn = turn0.toArray
      var skipTurn = skipTurn0.toArray
      var waitForPlayersBehind = waitForPlayersBehind0.toArray

      val aantalSpelers = lastMove.length

      var speler = new Player(nrOfPlayers = aantalSpelers)
      var spel = new Game(player = speler, gooses = goosesList)

      var players = new Array[Player](aantalSpelers)
      var player = speler
      for (i <- 1 to aantalSpelers) {
        players(i-1) = player
        player = player.getNextPlayer
      }

      for(i<- 1 to aantalSpelers){
        players(i-1).setLastMove(lastMove(i-1))
        players(i-1).setPosition(position(i-1))
        players(i-1).setTurn(turn(i-1))
        players(i-1).setSkipTurn(skipTurn(i-1))
        players(i-1).setWaitForPlayerBehind(waitForPlayersBehind(i-1))
      }

      spel.play(spel.getPlayer)

      val dice = spel.getDice
      val finished = spel.getFinished


      for (i <- 1 to aantalSpelers) {
        lastMove(i-1) = players(i-1).getLastMove
        position(i-1) = players(i-1).getPosition
        turn(i-1) = players(i-1).getTurn
        skipTurn(i-1) = players(i-1).getSkipTurn
        waitForPlayersBehind(i-1) = players(i-1).getWaitForPlayersBehind
      }

      val lastMove2 = lastMove.toList
      val position2 =position.toList
      val turn2 = turn.toList
      val skipTurn2 = skipTurn.toList
      val waitForPlayersBehind2 = waitForPlayersBehind.toList

      Ok(
        Json.obj(
          "dice" -> dice,
          "gooses" -> goosesList,
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