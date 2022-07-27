package app
import scala.util.Random

  class Game(var dice: Int, var player: Player, var gooses: List[Int], var finished: Boolean) {

    def scatterGooses(): Unit = {
      var goose1 = randomChoiceGoose(0, 0, 0, 0)
      var goose2 = randomChoiceGoose(goose1, 0, 0, 0)
      var goose3 = randomChoiceGoose(goose1, goose2, 0, 0)
      var goose4 = randomChoiceGoose(goose1, goose2, goose3, 0)
      var goose5 = randomChoiceGoose(goose1, goose2, goose3, goose4)
      this.gooses = List(goose1, goose2, goose3, goose4, goose5)
    }

    def randomChoiceGoose(a: Int, b: Int, c: Int, d: Int): Int = {
      var choice = randomGooseSpot()
      while (choice == a || choice == b || choice == c || choice == d) {
        choice = randomGooseSpot()
      }
      choice
    }

    def randomGooseSpot(): Int = {
      var goose = Random.nextInt(55)
      goose = goose + 1
      if (goose > 5) {
        goose = goose + 1
      }
      if (goose > 18) {
        goose = goose + 1
      }
      if (goose > 30) {
        goose = goose + 1
      }
      if (goose > 41) {
        goose = goose + 1
      }
      if (goose > 51) {
        goose = goose + 1
      }
      if (goose > 57) {
        goose = goose + 1
      }
      goose
    }

    def checkRules(player: Player): Unit = {
      this.bridge(player)
      this.inn(player)
      this.well(player)
      this.bush(player)
      this.prison(player)
      this.death(player)
      this.goose(player)
    }

    def bridge(player: Player): Unit = {
      if (player.getPosition == 6) {
        player.move(6)
      }
    }

    def inn(player: Player): Unit={
      if (player.getPosition == 19) {
        player.setSkipTurn(true)
      }
    }

    def bush(player: Player): Unit={
      if (player.getPosition == 42) {
        player.move(-5)
      }
    }

    def death(player: Player): Unit={
      if (player.getPosition == 58){
        player.move(-58)
      }
    }

    def goose(player: Player): Unit={
      for( i <- 0 to 4){
        if ( player.getPosition == gooses(i)){
          player.move(player.getLastMove)
        }
      }
    }

    def well(player: Player): Unit = wellPrison(player, 31)

    def prison(player: Player): Unit = wellPrison(player, 52)

    def wellPrison(player: Player, spot: Int): Unit={
      if (player.getPosition == spot){
        if (player.playersBehind()){
          player.setWaitForPlayerBehind(true)
        }
        else{
          player.setSkipTurn(true)
        }
      }
    }

    def ThrowDice: Int = {
      val number = Random.nextInt(6)
      val newNumber = number + 1
      newNumber
    }

    def setThrowDice(): Unit = {
      this.dice = this.ThrowDice
    }

    def play(player: Player): Unit={
      if (player.getTurn){
        if (player.getSkipTurn){
          player.switchSkipTurn()
        }
        else{
          if ( player.getWaitForPlayersBehind && (!player.playersBehind()) ){
              player.switchGetWaitForPlayersBehind()
              playAnyway(player)
          }
          else if (!player.getWaitForPlayersBehind) {
            playAnyway(player)
          }
        }
        player.switchTurn()
        player.getNextPlayer.switchTurn()
      }
      else{
        play(player.getNextPlayer)
      }
    }

    def playAnyway(player: Player): Unit ={
      setThrowDice()
      player.move(this.dice)
      checkRules(player)
    }

    def getDice: Int={ this.dice}
    def getPlayer: Player={this.player}
    def getGooses: List[Int]={this.gooses}
    def getFinished: Boolean={this.finished}

  }