package app

import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random


//class domainTest extends AnyFlatSpec with should.Matchers {

class domainTest extends AnyFunSuite {

  test("1 PLAYER: 1 INITIALISATION: 1 player-properties") {
    var speler = new Player(0, 0, false, false, false)
    assert(speler.getLastMove == 0)
    assert(speler.getPosition == 0)
    assert(!speler.getTurn)
    assert(!speler.getSkipTurn)
    assert(!speler.getWaitForPlayersBehind)
  }

  test("1 PLAYER: 1 INITIALISATION: 2 player-properties") {
    var speler = new Player(4, 25, true, false, false)
    assert(speler.getLastMove == 4)
    assert(speler.getPosition == 25)
    assert(speler.getTurn)
    assert(!speler.getSkipTurn)
    assert(!speler.getWaitForPlayersBehind)
  }



  test("1 PLAYER: 2 INITIALISATION/FIRST PLAYER: 1 Setting the first player"){
    var speler = new Player
    assert(speler == speler.firstPlayer)
    assert(speler.getFirstPlayer.getLastMove == 0)
    assert(speler.getFirstPlayer.getPosition == 0)
    assert(speler.getFirstPlayer.getTurn == true)
    assert(speler.getFirstPlayer.getSkipTurn == false)
    assert(speler.getFirstPlayer.getWaitForPlayersBehind == false)
    assert(speler.getFirstPlayer.getNrOfPlayers == 2)
    assert(speler.getFirstPlayer.getLastMove == speler.getLastMove)
    assert(speler.getFirstPlayer.getPosition == speler.getPosition)
    assert(speler.getFirstPlayer.getTurn == speler.getTurn)
    assert(speler.getFirstPlayer.getSkipTurn == speler.getSkipTurn)
    assert(speler.getFirstPlayer.getWaitForPlayersBehind == speler.getWaitForPlayersBehind )
    assert(speler.getFirstPlayer.getNrOfPlayers == speler.getNrOfPlayers)
  }

  test("1 PLAYER: 2 INITIALISATION/FIRST PLAYER: 2 Setting the first player "){
    var speler = new Player(2,14,false,false,true,30)
    assert(speler == speler.firstPlayer)
    assert(speler.getFirstPlayer.getLastMove == 2)
    assert(speler.getFirstPlayer.getPosition == 14)
    assert(speler.getFirstPlayer.getTurn == false)
    assert(speler.getFirstPlayer.getSkipTurn == false)
    assert(speler.getFirstPlayer.getWaitForPlayersBehind == true)
    assert(speler.getFirstPlayer.getNrOfPlayers == 30)
    assert(speler.getFirstPlayer.getLastMove == speler.getLastMove)
    assert(speler.getFirstPlayer.getPosition == speler.getPosition)
    assert(speler.getFirstPlayer.getTurn == speler.getTurn)
    assert(speler.getFirstPlayer.getSkipTurn == speler.getSkipTurn)
    assert(speler.getFirstPlayer.getWaitForPlayersBehind == speler.getWaitForPlayersBehind )
    assert(speler.getFirstPlayer.getNrOfPlayers == speler.getNrOfPlayers)
  }



  test("1 PLAYER: 3 INITIALISATION/NEXT PLAYER: 1 Next Player Test: next player =/= player"){
    var speler = new Player
    assert(speler != speler.getNextPlayer)
  }

  test("1 PLAYER: 3 INITIALISATION/NEXT PLAYER: 2 Next Player Test: next next player == player (when using standard values)"){
    var speler = new Player
    assert(speler == speler.getNextPlayer.getNextPlayer)
  }

  test("1 PLAYER: 3 INITIALISATION/NEXT PLAYER: 3 Next Player Test: 10 players, first nine different"){
    var speler = new Player(nrOfPlayers = 10)
    var changingspeler = speler
    var bool = true
    for(i <- 1 to 9){
      changingspeler=changingspeler.nextPlayer
      if (changingspeler == speler){ bool = false}
    }
    assert(bool)
  }

  test("1 PLAYER: 3 INITIALISATION/NEXT PLAYER: 4 Next Player Test: 10 players, tenth is the same"){
    var speler = new Player(nrOfPlayers = 10)
    var changingspeler = speler
    for(i <- 1 to 10){
      changingspeler=changingspeler.nextPlayer
    }
    assert(changingspeler == speler)
  }

  test("1 PLAYER: 3 INITIALISATION/NEXT PLAYER: 5 Next Player Test: first has the turn"){
    var speler = new Player(nrOfPlayers = 10)
    var bool = speler.getTurn
    assert(bool)
    var changingspeler = speler
    for(i <- 1 to 9){
      changingspeler=changingspeler.nextPlayer
      bool = (!(changingspeler.getTurn))
      assert(bool)
    }
  }



  test("1 PLAYER: 4 MOVE METHOD: 1 move-test"){
    var speler1 = new Player(0, 0, false, false, false)
    speler1.move(5)
    assert(speler1.getPosition == 5)
    speler1.move(3)
    assert(speler1.getPosition == 8)
    speler1.move(6)
    assert(speler1.getPosition == 14)
    speler1.move(6)
    assert(speler1.getPosition == 20)
    speler1.move(6)
    assert(speler1.getPosition == 26)
    speler1.move(6)
    assert(speler1.getPosition == 32)
    speler1.move(1)
    assert(speler1.getPosition == 33)
    speler1.move(5)
    assert(speler1.getPosition == 38)
    speler1.move(5)
    assert(speler1.getPosition == 43)
    speler1.move(5)
    assert(speler1.getPosition == 48)
    speler1.move(5)
    assert(speler1.getPosition == 53)
    speler1.move(5)
    assert(speler1.getPosition == 58)
    speler1.move(6)
    assert(speler1.getPosition == 62)
    speler1.move(5)
    assert(speler1.getPosition == 59)
    speler1.move(5)
    assert(speler1.getPosition == 62)
    speler1.move(1)
    assert(speler1.getPosition == 63)
  }

  test("1 PLAYER: 4 MOVE METHOD: 2 move-test"){
    var speler1 = new Player(0, 0, false, false, false)
    speler1.move(5)
    assert(speler1.getPosition == 5)
    speler1.move(3)
    assert(speler1.getPosition == 8)
    speler1.move(6)
    assert(speler1.getPosition == 14)
    speler1.move(-6)
    assert(speler1.getPosition == 8)
    speler1.move(-6)
    assert(speler1.getPosition == 2)
    speler1.move(-6)
    assert(speler1.getPosition == 4)
    speler1.move(-1)
    assert(speler1.getPosition == 3)
    speler1.move(-5)
    assert(speler1.getPosition == 2)
    speler1.move(-5)
    assert(speler1.getPosition == 3)
  }

  test("1 PLAYER: 4 MOVE METHOD: 3 move-test"){
    var speler1 = new Player(0, 0, false, false, false)
    var bool = true
    var nr = 0
    for( i <- 1 to 1000) {
      var number = Random.nextInt(6)
      var nr = number + 1
      speler1.move(nr)
      if (speler1.getPosition < 0){
        bool = false
      }
      if (speler1.getPosition > 63){
        bool = false
      }
    }
    assert(bool)
  }

  test("1 PLAYER: 4 MOVE METHOD: 4 move-test"){
    var speler1 = new Player(0, 0, false, false, false)
    var bool = true
    var nr = 0
    for( i <- 1 to 1000) {
      var number = Random.nextInt(6)
      var nr = number + 1
      nr = -1*nr
      speler1.move(nr)
      if (speler1.getPosition < 0){
        bool = false
      }
      if (speler1.getPosition > 63){
        bool = false
      }
    }
    assert(bool)
  }

  test("1 PLAYER: 4 MOVE METHOD: 5 move-test"){
    var speler2 = new Player(4, 25, true, false, false)
    speler2.move(5)
    assert(speler2.getPosition == 30)
    speler2.move(3)
    assert(speler2.getPosition == 33)
    speler2.move(6)
    assert(speler2.getPosition == 39)
    speler2.move(6)
    assert(speler2.getPosition == 45)
    speler2.move(6)
    assert(speler2.getPosition == 51)
    speler2.move(6)
    assert(speler2.getPosition == 57)
    speler2.move(1)
    assert(speler2.getPosition == 58)
    speler2.move(5)
    assert(speler2.getPosition == 63)
    speler2.move(5)
    assert(speler2.getPosition == 58)
    speler2.move(5)
    assert(speler2.getPosition == 63)
    speler2.move(5)
    assert(speler2.getPosition == 58)
    speler2.move(5)
    assert(speler2.getPosition == 63)
    speler2.move(6)
    assert(speler2.getPosition == 57)
    speler2.move(5)
    assert(speler2.getPosition == 62)
    speler2.move(5)
    assert(speler2.getPosition == 59)
    speler2.move(1)
    assert(speler2.getPosition == 60)
  }

  test("1 PLAYER: 4 MOVE METHOD: 6 move-test"){
    var speler2 = new Player(4, 25, true, false, false)
    speler2.move(5)
    assert(speler2.getPosition == 30)
    speler2.move(3)
    assert(speler2.getPosition == 33)
    speler2.move(6)
    assert(speler2.getPosition == 39)
    speler2.move(-6)
    assert(speler2.getPosition == 33)
    speler2.move(-6)
    assert(speler2.getPosition == 27)
    speler2.move(-6)
    assert(speler2.getPosition == 21)
    speler2.move(-1)
    assert(speler2.getPosition == 20)
    speler2.move(-5)
    assert(speler2.getPosition == 15)
    speler2.move(-5)
    assert(speler2.getPosition == 10)
    speler2.move(-6)
    assert(speler2.getPosition == 4)
    speler2.move(-1)
    assert(speler2.getPosition == 3)
    speler2.move(-5)
    assert(speler2.getPosition == 2)
    speler2.move(-5)
    assert(speler2.getPosition == 3)
  }

  test("1 PLAYER: 4 MOVE METHOD: 7 move-test"){
    var speler2 = new Player(4, 25, true, false, false)
    var bool = true
    var nr = 0
    for( i <- 1 to 1000) {
      var number = Random.nextInt(6)
      var nr = number + 1
      speler2.move(nr)
      if (speler2.getPosition < 0){
        bool = false
      }
      if (speler2.getPosition > 63){
        bool = false
      }
    }
    assert(bool)
  }

  test("1 PLAYER: 4 MOVE METHOD: 8 move-test"){
    var speler2 = new Player(4, 25, true, false, false)
    var bool = true
    var nr = 0
    for( i <- 1 to 1000) {
      var number = Random.nextInt(6)
      var nr = number + 1
      nr = -1*nr
      speler2.move(nr)
      if (speler2.getPosition < 0){
        bool = false
      }
      if (speler2.getPosition > 63){
        bool = false
      }
    }
    assert(bool)
  }



  test("1 PLAYER: 5 SWITCH TURN METHOD: 1 switch-turn-test"){
    var speler1 = new Player(0, 0, false, false, false)
    var speler2 = new Player(4, 25, true, false, false)
    speler1.switchTurn()
    assert(speler1.getTurn)
    speler1.switchTurn()
    assert(!speler1.getTurn)
    speler1.switchTurn()
    assert(speler1.getTurn)
    speler2.switchTurn()
    assert(!speler2.getTurn)
    speler2.switchTurn()
    assert(speler2.getTurn)
    speler2.switchTurn()
    assert(!speler2.getTurn)
  }



  test("1 PLAYER: 6 SWITCH SKIP-TURN METHOD: 1 switch-skipturn-test"){
    var speler1 = new Player(0, 0, false, false, false)
    var speler3 = new Player(4, 25, true, true, false)
    speler1.switchSkipTurn()
    assert(speler1.getSkipTurn)
    speler1.switchSkipTurn()
    assert(!speler1.getSkipTurn)
    speler1.switchSkipTurn()
    assert(speler1.getSkipTurn)
    speler3.switchSkipTurn()
    assert(!speler3.getSkipTurn)
    speler3.switchSkipTurn()
    assert(speler3.getSkipTurn)
    speler3.switchSkipTurn()
    assert(!speler3.getSkipTurn)
  }



  test("1 PLAYER: 7 SET SKIP-TURN: setSkipTurn test"){
    var speler1 = new Player(0, 0, false, false, false)
    var speler2 = new Player(4, 25, true, false, false)
    speler1.setSkipTurn(true)
    assert(speler1.getSkipTurn)
    speler1.setSkipTurn(false)
    assert(!speler1.getSkipTurn)
    speler2.setSkipTurn(true)
    assert(speler2.getSkipTurn)
    speler2.setSkipTurn(false)
    assert(!speler2.getSkipTurn)
  }



  test("1 PLAYER: 8 PLAYERS BEHIND CHECKER: Players behind test 1") {
    var speler1 = new Player(position = 12, nrOfPlayers = 5)
    var speler2 = speler1.nextPlayer
    speler2.setPosition(14)
    var speler3 = speler2.nextPlayer
    speler3.setPosition(18)
    var speler4 = speler3.nextPlayer
    speler4.setPosition(11)
    var speler5 = speler4.nextPlayer
    speler5.setPosition(21)
    assert(speler1.playersBehind)
    assert(speler2.playersBehind)
    assert(speler3.playersBehind)
    assert(!(speler4.playersBehind))
    assert(speler5.playersBehind)
  }

  test("1 PLAYER: 8 PLAYERS BEHIND CHECKER: Players behind test 2") {
    var speler1 = new Player(position = 3, nrOfPlayers = 5)
    var speler2 = speler1.nextPlayer
    speler2.setPosition(14)
    var speler3 = speler2.nextPlayer
    speler3.setPosition(18)
    var speler4 = speler3.nextPlayer
    speler4.setPosition(11)
    var speler5 = speler4.nextPlayer
    speler5.setPosition(21)
    assert(!(speler1.playersBehind))
    assert(speler2.playersBehind)
    assert(speler3.playersBehind)
    assert(speler4.playersBehind)
    assert(speler5.playersBehind)
  }






  test("2 GAME/PLAYER: 1 INITIALISATION: 1 game-properties"){
    var speler1 = new Player(0, 0, false, false, false)
    var speler2 = new Player(4, 25, true, false, false)
    var spel11 = new Game(0, speler1, List(0,0,0,0,0),false)
    var spel12 = new Game(0, speler2, List(0,0,0,0,0),false)
    var spel21 = new Game(3, speler1, List(1,2,3,4,5),true)
    var spel22 = new Game(3, speler2, List(1,2,3,4,5),true)
    assert(spel11.getDice == 0)
    assert(spel11.getPlayer == speler1)
    assert(spel11.getGooses.equals(List(0,0,0,0,0)))
    assert(!spel11.getFinished)
    assert(spel12.getDice == 0)
    assert(spel12.getPlayer == speler2)
    assert(spel12.getGooses.equals(List(0,0,0,0,0)))
    assert(!spel12.getFinished)
    assert(spel21.getDice == 3)
    assert(spel21.getPlayer == speler1)
    assert(spel21.getGooses.equals(List(1,2,3,4,5)))
    assert(spel21.getFinished)
    assert(spel22.getDice == 3)
    assert(spel22.getPlayer == speler2)
    assert(spel22.getGooses.equals(List(1,2,3,4,5)))
    assert(spel22.getFinished)
  }


  test("2 GAME/PLAYER: 1 INITIALISATION: 1 SET GOOSES METHOD: 1 scatter-gooses test"){
    var speler1 = new Player(0, 0, false, false, false)
    var spel11 = new Game(0, speler1, List(0,0,0,0,0),false)
    spel11.scatterGooses()
    var list = spel11.getGooses
    assert(list.length == 5)
    assert( list(1) != list(0) )
    assert( (list(2) != list(1)) && (list(2) != list(0)))
    assert( (list(3) != list(2) ) && (list(3) != list(1)) && (list(4) != list(0)))
    assert( (list(4) != list(3)) && (list(4) != list(2)) && (list(4) != list(1)) && (list(4) != list(0)))
  }


  test("2 GAME/PLAYER: 1 INITIALISATION: 2 SET GOOSES METHOD - AUXILARY METHOD: 1 random-choice-goose test"){
    var speler1 = new Player(0, 0, false, false, false)
    var spel11 = new Game(0, speler1, List(0,0,0,0,0),false)
    var nr = spel11.randomChoiceGoose(0,1,2,3)
    assert(!(nr==0 || nr==1 || nr==2 || nr==3))
    var sum = 0
    var bool = true
    for( i <- 1 to 1000) {
      nr = spel11.randomChoiceGoose(0,1,2,3)
      sum = sum + nr
      if (nr < 4){
        bool = false
      }
    }
    assert(sum > 3999)
    assert(bool)
  }


  test("2 GAME/PLAYER: 1 INITIALISATION: 3 SET GOODES METHOD - AUXILARY OF AUXILARY: 1 random-goose-spot-test"){
    var speler1 = new Player(0, 0, false, false, false)
    var spel11 = new Game(0, speler1, List(0,0,0,0,0),false)
    var bool = true
    var nr = 0
    for( i <- 1 to 1000) {
      nr = spel11.randomGooseSpot()
      if ( (nr<1) || (nr > 63) ){
        bool = false
      }
      if ( (nr==6) || (nr==19) || (nr==31) || (nr==42) || (nr==52) || (nr==58)){
        bool = false
      }
    }
    assert(bool)
  }



  test("2 GAME/PLAYER: 2 BRIDGE METHOD: 1 bridge test"){
    var speler6 = new Player(0, 6, false, false, false)
    var spel6 = new Game(0, speler6, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, false, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel6.bridge(spel6.getPlayer)
    assert(speler6.getPosition == 12)
    assert(spel6.getPlayer.getPosition == 12)
    spel7.bridge(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 3 INN METHOD: 1 inn test"){
    var speler19 = new Player(0, 19, true, false, false)
    var spel19 = new Game(0, speler19, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel19.inn(spel19.getPlayer)
    assert(speler19.getSkipTurn)
    assert(spel19.getPlayer.getSkipTurn)
    spel7.inn(spel7.getPlayer)
    assert(!(speler7.getSkipTurn))
    assert(!(spel7.getPlayer.getSkipTurn))
  }

  test("2 GAME/PLAYER: 4 WELL METHOD: 1 well test") {
    var speler = new Player (position = 31)
    speler.nextPlayer.setPosition(24)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.well(speler)
    assert(!(speler.getSkipTurn))
    assert(speler.getWaitForPlayersBehind)
  }

  test("2 GAME/PLAYER: 4 WELL METHOD: 2 well test") {
    var speler = new Player (position = 31)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.well(speler)
    assert(speler.getSkipTurn)
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 4 WELL METHOD: 3 well test") {
    var speler = new Player (position = 24)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.well(speler)
    assert(!(speler.getSkipTurn))
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 5 BUSH METHOD: 1 bush-test"){
    var speler42 = new Player(0, 42, true, false, false)
    var spel42 = new Game(0, speler42, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel42.bush(spel42.getPlayer)
    assert(speler42.getPosition == 37)
    assert(spel42.getPlayer.getPosition == 37)
    spel7.bush(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 5 PRISON METHOD: 1 prison test") {
    var speler = new Player (position = 52)
    speler.nextPlayer.setPosition(24)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.prison(speler)
    assert(!(speler.getSkipTurn))
    assert(speler.getWaitForPlayersBehind)
  }

  test("2 GAME/PLAYER: 5 PRISON METHOD: 2 prison test") {
    var speler = new Player (position = 52)
    speler.nextPlayer.setPosition(53)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.prison(speler)
    assert(speler.getSkipTurn)
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 5 PRISON METHOD: 3 prison test") {
    var speler = new Player (position = 24)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.prison(speler)
    assert(!(speler.getSkipTurn))
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 5 DEATH METHOD: 1 death-test"){
    var speler58 = new Player(0, 58, true, false, false)
    var spel58 = new Game(0, speler58, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel58.death(spel58.getPlayer)
    assert(speler58.getPosition == 0)
    assert(spel58.getPlayer.getPosition == 0)
    spel7.death(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 6 GOOSE METHOD: 1 goose-test") {
    var speler = new Player(5, 50, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 55)
  }

  test( "2 GAME/PLAYER: 6 GOOSE METHOD: 2 goose-test"){
    var speler = new Player(4, 45, true, false, false)
    var spel = new Game(0, speler, List(2,3,35,45,50),false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 49)
  }

  test("2 GAME/PLAYER: 6 GOOSE METHOD: 3 goose-test") {
    var speler = new Player(5, 35, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 40)

  }

  test("2 GAME/PLAYER: 6 GOOSE METHOD: 4 goose-test") {
    var speler = new Player(5, 3, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 8)
  }

  test("2 GAME/PLAYER: 6 GOOSE METHOD: 5 goose-test") {
    var speler = new Player(5, 2, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 6 GOOSE METHOD: 6 goose-test"){
    var speler = new Player(5, 1, true, false, false)
    var spel = new Game(0, speler, List(2,3,35,45,50),false)
    spel.goose(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 1)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 1 check rules bridge-test"){
    var speler6 = new Player(0, 6, false, false, false)
    var spel6 = new Game(0, speler6, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, false, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel6.checkRules(spel6.getPlayer)
    assert(speler6.getPosition == 12)
    assert(spel6.getPlayer.getPosition == 12)
    spel7.checkRules(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 2 check rules inn-test"){
    var speler19 = new Player(0, 19, true, false, false)
    var spel19 = new Game(0, speler19, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel19.checkRules(spel19.getPlayer)
    assert(speler19.getSkipTurn)
    assert(spel19.getPlayer.getSkipTurn)
    spel7.checkRules(spel7.getPlayer)
    assert(!(speler7.getSkipTurn))
    assert(!(spel7.getPlayer.getSkipTurn))
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 3 check rules: well test 1") {
    var speler = new Player (position = 31)
    speler.nextPlayer.setPosition(24)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(!(speler.getSkipTurn))
    assert(speler.getWaitForPlayersBehind)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 4 check rules: well test 2") {
    var speler = new Player (position = 31)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(speler.getSkipTurn)
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 5 check rules: well test 3") {
    var speler = new Player (position = 24)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(!(speler.getSkipTurn))
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 6 check rules bush-test"){
    var speler42 = new Player(0, 42, true, false, false)
    var spel42 = new Game(0, speler42, List(0,0,0,0,0),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(0,0,0,0,0),false)
    spel42.checkRules(spel42.getPlayer)
    assert(speler42.getPosition == 37)
    assert(spel42.getPlayer.getPosition == 37)
    spel7.checkRules(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 7 check rules: prison test 1") {
    var speler = new Player (position = 52)
    speler.nextPlayer.setPosition(24)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(!(speler.getSkipTurn))
    assert(speler.getWaitForPlayersBehind)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 8 check rules: prison test 2") {
    var speler = new Player (position = 52)
    speler.nextPlayer.setPosition(53)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(speler.getSkipTurn)
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 9 check rules: prison test 3") {
    var speler = new Player (position = 24)
    speler.nextPlayer.setPosition(33)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(speler)
    assert(!(speler.getSkipTurn))
    assert(!(speler.getWaitForPlayersBehind))
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 10 check rules death-test"){
    var speler58 = new Player(0, 58, true, false, false)
    var spel58 = new Game(0, speler58, List(1,2,3,4,5),false)
    var speler7 = new Player(0, 7, true, false, false)
    var spel7 = new Game(7, speler7, List(1,2,3,4,5),false)
    spel58.checkRules(spel58.getPlayer)
    assert(speler58.getPosition == 0)
    assert(spel58.getPlayer.getPosition == 0)
    spel7.checkRules(spel7.getPlayer)
    assert(speler7.getPosition == 7)
    assert(spel7.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 11 check rules goose-test 1") {
    var speler = new Player(5, 50, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 55)

  }

  test( "2 GAME/PLAYER: 7 CHECK RULES METHOD: 12 check rules goose-test 2"){
    var speler = new Player(4, 45, true, false, false)
    var spel = new Game(0, speler, List(2,3,35,45,50),false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 49)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 13 check rules goose-test 3") {
    var speler = new Player(5, 35, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 40)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 14 check rules goose-test 4") {
    var speler = new Player(5, 3, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 8)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 15 check rules goose-test 5") {
    var speler = new Player(5, 2, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 7)
  }

  test("2 GAME/PLAYER: 7 CHECK RULES METHOD: 16 check rules goose-test 6") {
    var speler = new Player(5, 1, true, false, false)
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.checkRules(spel.getPlayer)
    assert(spel.getPlayer.getPosition == 1)
  }



  test("2 GAME/PLAYER: 8 DICE OUTCOME: throw dice test 1") {
    var speler = new Player
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    var nr = spel.ThrowDice
    assert(nr <= 6)
    assert(nr >= 1)
  }

  test("2 GAME/PLAYER: 8 DICE OUTCOME: throw dice test 2") {
    var speler = new Player
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    var nr = 0
    var dummy = 0
    var count = Array(0,0,0,0,0,0)
    for (i <- 1 to 10000){
      nr = spel.ThrowDice
      for (k <- 1 to 6){
        if (nr == k){
          dummy = count(k-1)
          dummy = dummy + 1
          count(k-1) = dummy
        }
      }
    }
    for (k <- 1 to 6){
      assert(count(k-1) >= 1400)
      assert(count(k-1) <= 1900)
    }
  }



  test ("2 GAME/PLAYER: 9 DICE REGISTERING: set throw dice test 1"){
    var speler = new Player
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    spel.setThrowDice()
    assert(spel.getDice <= 6)
    assert(spel.getDice >= 1)
  }

  test("2 GAME/PLAYER: 9 DICE REGISTERING: set throw dice test 2") {
    var speler = new Player
    var spel = new Game(0, speler, List(2, 3, 35, 45, 50), false)
    var nr = 0
    var dummy = 0
    var count = Array(0,0,0,0,0,0)
    for (i <- 1 to 10000){
      spel.setThrowDice()
      for (k <- 1 to 6){
        if (spel.getDice == k){
          dummy = count(k-1)
          dummy = dummy + 1
          count(k-1) = dummy
        }
      }
    }
    for (k <- 1 to 6){
      assert(count(k-1) >= 1400)
      assert(count(k-1) <= 1900)
    }
  }

  test("2 GAME/PLAYER: 10 PLAY: 1 the player must move") {
    var speler = new Player
    var spel = new Game(0, speler, List(10, 14, 35, 45, 50), false)
    spel.play(spel.getPlayer)
    assert( (speler.getPosition >= 1) && (speler.getPosition <= 6) )
  }

  test("2 GAME/PLAYER: 10 PLAY: 2 the player must switch turn") {
    var speler = new Player
    var spel = new Game(0, speler, List(10, 14, 35, 45, 50), false)
    spel.play(spel.getPlayer)
    assert(!speler.getTurn)
  }

  test("2 GAME/PLAYER: 10 PLAY: 3 next player must get turn") {
    var speler = new Player
    var spel = new Game(0, speler, List(10, 14, 35, 45, 50), false)
    spel.play(spel.getPlayer)
    assert(speler.getNextPlayer.getTurn)
  }

  test ("2 GAME/PLAYER 10 PLAY: 4 FIRST BRANCH - skipTurn==true"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setSkipTurn(true)
    spel.play(spel.getPlayer)
    assert(!spel.getPlayer.getSkipTurn)
    assert(!spel.getPlayer.getTurn)
    assert(!spel.getPlayer.getNextPlayer.getSkipTurn)
    assert(spel.getPlayer.getNextPlayer.getTurn)
  }

  test ("2 GAME/PLAYER 10 PLAY: 5 SECOND BRANCH - wait for players behind"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setWaitForPlayerBehind(true)
    spel.getPlayer.setPosition(39)
    spel.getPlayer.getNextPlayer.setPosition(7)
    val pos1 = spel.getPlayer.getPosition
    val turn1 = spel.getPlayer.getTurn
    val skipTurn1 = spel.getPlayer.getSkipTurn
    val wait1 = spel.getPlayer.getWaitForPlayersBehind
    val nr1 = spel.getPlayer.getNrOfPlayers
    spel.play(spel.getPlayer)
    assert(spel.getPlayer.getPosition == pos1)
    assert(spel.getPlayer.getTurn != turn1)
    assert(spel.getPlayer.getSkipTurn == skipTurn1)
    assert(spel.getPlayer.getWaitForPlayersBehind == wait1)
    assert(spel.getPlayer.getNrOfPlayers == nr1)
  }

  test ("2 GAME/PLAYER 10 PLAY: 6 THIRD BRANCH - no one to wait for"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setWaitForPlayerBehind(true)
    spel.getPlayer.setPosition(7)
    spel.getPlayer.getNextPlayer.setPosition(39)
    val pos1 = spel.getPlayer.getPosition
    val turn1 = spel.getPlayer.getTurn
    val skipTurn1 = spel.getPlayer.getSkipTurn
    val wait1 = spel.getPlayer.getWaitForPlayersBehind
    val nr1 = spel.getPlayer.getNrOfPlayers
    spel.play(spel.getPlayer)
    assert(spel.getPlayer.getPosition == pos1)
    assert(spel.getPlayer.getTurn != turn1)
    assert(spel.getPlayer.getSkipTurn == skipTurn1)
    assert(spel.getPlayer.getWaitForPlayersBehind != wait1)
    assert(spel.getPlayer.getNrOfPlayers == nr1)
  }

  test("2 GAME/PLAYER: 10 PLAY: 7 FOURTH BRANCH - nothing stops us"){
    var speler = new Player
    var spel = new Game(player = speler)
    spel.play(spel.getPlayer)
    if (spel.getDice == 6){
      assert(spel.getPlayer.getLastMove == 6)
      assert(spel.getPlayer.getPosition == 12)
    }
    else{
      assert(spel.getPlayer.getLastMove == spel.getDice)
      assert(spel.getPlayer.getPosition == spel.getDice)
    }
    assert(!spel.getPlayer.getTurn)
    assert(spel.getPlayer.getNextPlayer.getTurn)
    assert(!spel.getPlayer.getWaitForPlayersBehind)
    assert(spel.getPlayer.getNrOfPlayers == 2)
  }

  test("2 GAME/PLAYER: 10 PLAY: 8 FOURTH BRANCH - nothing stops us - multiple"){
    for (i <- 1 to 100){
      var speler = new Player
      var spel = new Game(player = speler)
      spel.play(spel.getPlayer)
      if (spel.getDice == 6){
        assert(spel.getPlayer.getLastMove == 6)
        assert(spel.getPlayer.getPosition == 12)
      }
      else{
        assert(spel.getPlayer.getLastMove == spel.getDice)
        assert(spel.getPlayer.getPosition == spel.getDice)
      }
      assert(!spel.getPlayer.getTurn)
      assert(spel.getPlayer.getNextPlayer.getTurn)
      assert(!spel.getPlayer.getWaitForPlayersBehind)
      assert(spel.getPlayer.getNrOfPlayers == 2)
    }
  }

  test ("2 GAME/PLAYER 10 PLAY: 9 FIRST BRANCH - skipTurn==true / NEXT PLAYER"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setTurn(false)
    spel.getPlayer.getNextPlayer.setTurn(true)
    spel.getPlayer.getNextPlayer.setSkipTurn(true)
    spel.play(spel.getPlayer)
    assert(!spel.getPlayer.getNextPlayer.getSkipTurn)
    assert(!spel.getPlayer.getNextPlayer.getTurn)
    assert(!spel.getPlayer.getSkipTurn)
    assert(spel.getPlayer.getTurn)
  }

  test ("2 GAME/PLAYER 10 PLAY: 10 SECOND BRANCH - wait for players behind / NEXT PLAYER"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setTurn(false)
    spel.getPlayer.getNextPlayer.setTurn(true)
    spel.getPlayer.getNextPlayer.setWaitForPlayerBehind(true)
    spel.getPlayer.setPosition(7)
    spel.getPlayer.getNextPlayer.setPosition(39)
    val pos1 = spel.getPlayer.getNextPlayer.getPosition
    val turn1 = spel.getPlayer.getNextPlayer.getTurn
    val skipTurn1 = spel.getPlayer.getNextPlayer.getSkipTurn
    val wait1 = spel.getPlayer.getNextPlayer.getWaitForPlayersBehind
    val nr1 = spel.getPlayer.getNextPlayer.getNrOfPlayers
    spel.play(spel.getPlayer)
    assert(spel.getPlayer.getNextPlayer.getPosition == pos1)
    assert(spel.getPlayer.getNextPlayer.getTurn != turn1)
    assert(spel.getPlayer.getNextPlayer.getSkipTurn == skipTurn1)
    assert(spel.getPlayer.getNextPlayer.getWaitForPlayersBehind == wait1)
    assert(spel.getPlayer.getNextPlayer.getNrOfPlayers == nr1)
  }

  test ("2 GAME/PLAYER 10 PLAY: 11 THIRD BRANCH - no one to wait for / NEXT PLAYER"){
    var speler = new Player
    var spel = new Game(player=speler)
    spel.getPlayer.setTurn(false)
    spel.getPlayer.getNextPlayer.setTurn(true)
    spel.getPlayer.getNextPlayer.setWaitForPlayerBehind(true)
    spel.getPlayer.setPosition(39)
    spel.getPlayer.getNextPlayer.setPosition(7)
    val pos1 = spel.getPlayer.getNextPlayer.getPosition
    val turn1 = spel.getPlayer.getNextPlayer.getTurn
    val skipTurn1 = spel.getPlayer.getNextPlayer.getSkipTurn
    val wait1 = spel.getPlayer.getNextPlayer.getWaitForPlayersBehind
    val nr1 = spel.getPlayer.getNextPlayer.getNrOfPlayers
    spel.play(spel.getPlayer)
    assert(spel.getPlayer.getNextPlayer.getPosition == pos1)
    assert(spel.getPlayer.getNextPlayer.getTurn != turn1)
    assert(spel.getPlayer.getNextPlayer.getSkipTurn == skipTurn1)
    assert(spel.getPlayer.getNextPlayer.getWaitForPlayersBehind != wait1)
    assert(spel.getPlayer.getNextPlayer.getNrOfPlayers == nr1)
  }

  test("2 GAME/PLAYER: 10 PLAY: 12 FOURTH BRANCH - nothing stops us / NEXT PLAYER"){
    var speler = new Player
    var spel = new Game(player = speler)
    spel.getPlayer.setTurn(false)
    spel.getPlayer.getNextPlayer.setTurn(true)
    spel.play(spel.getPlayer)
    if (spel.getDice == 6){
      assert(spel.getPlayer.getNextPlayer.getLastMove == 6)
      assert(spel.getPlayer.getNextPlayer.getPosition == 12)
    }
    else{
      assert(spel.getPlayer.getNextPlayer.getLastMove == spel.getDice)
      assert(spel.getPlayer.getNextPlayer.getPosition == spel.getDice)
    }
    assert(!spel.getPlayer.getNextPlayer.getTurn)
    assert(spel.getPlayer.getTurn)
    assert(!spel.getPlayer.getNextPlayer.getWaitForPlayersBehind)
    assert(spel.getPlayer.getNextPlayer.getNrOfPlayers == 1)
  }

  test("2 GAME/PLAYER: 10 PLAY: 13 FOURTH BRANCH - nothing stops us - multiple / NEXT PLAYER"){
    for (i <- 1 to 100){
      var speler = new Player
      var spel = new Game(player = speler)
      spel.getPlayer.setTurn(false)
      spel.getPlayer.getNextPlayer.setTurn(true)
      spel.play(spel.getPlayer)
      if (spel.getDice == 6){
        assert(spel.getPlayer.getNextPlayer.getLastMove == 6)
        assert(spel.getPlayer.getNextPlayer.getPosition == 12)
      }
      else{
        assert(spel.getPlayer.getNextPlayer.getLastMove == spel.getDice)
        assert(spel.getPlayer.getNextPlayer.getPosition == spel.getDice)
      }
      assert(!spel.getPlayer.getNextPlayer.getTurn)
      assert(spel.getPlayer.getTurn)
      assert(!spel.getPlayer.getNextPlayer.getWaitForPlayersBehind)
      assert(spel.getPlayer.getNextPlayer.getNrOfPlayers == 1)
    }
  }

  test("2 GAME/PLAYER: 11 AUXILARY PLAY METHOD: playAnyway lets you move"){
    var speler = new Player
    var spel = new Game(0, speler, List(10, 14, 35, 45, 50), false)
    spel.playAnyway(spel.getPlayer)
    assert(spel.getDice >= 1)
    assert(spel.getDice <= 6)
    assert(speler.getPosition >= 1)
    assert(speler.getPosition <= 6 || speler.getPosition == 12)
    assert(speler.getPosition == spel.getDice || speler.getPosition == spel.getDice + 6)
  }
}