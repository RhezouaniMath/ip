package app

class Player(
              var lastMove: Int = 0,
              var position: Int = 0,
              var turn: Boolean = true,
              var skipTurn: Boolean = false,
              var waitForPlayersBehind: Boolean = false,
              var nrOfPlayers: Int = 2
            ) {

    var firstPlayer: Player = this

    var nextPlayer: Player = {
      if (this.nrOfPlayers == 1){
        this
      }
      else{
        new Player(this, this.nrOfPlayers-1)
      }
    }





  def this(player: Player , nrOf: Int) {
    this(0,0,false,false,false,nrOf)
    if(nrOf==1){
      this.nextPlayer = player;
    } else {
      this.nextPlayer = new Player(player, nrOf-1)
    }
    this.firstPlayer = player
  }





  def getFirstPlayer: Player={this.firstPlayer}

  def getNextPlayer: Player={this.nextPlayer}

  def getPosition: Int={this.position}

  def getLastMove: Int={this.lastMove}

  def getTurn: Boolean={this.turn}

  def getWaitForPlayersBehind: Boolean={this.waitForPlayersBehind}

  def getSkipTurn: Boolean={this.skipTurn}

  def getNrOfPlayers: Int={this.nrOfPlayers}


  def playersBehind(): Boolean={
    val first = this
    val ownPosition = first.position
    var player = first.nextPlayer
    while(player != first){
      if (player.position < ownPosition){
        return true
      }
      player = player.nextPlayer
    }
    return false
  }




  def setSkipTurn(bool: Boolean): Unit={this.skipTurn = bool }

  def setFirstPlayer(player: Player): Unit={this.firstPlayer = player}

  def setPosition(pos: Int): Unit={this.position = pos}

  def setWaitForPlayerBehind(bool: Boolean) : Unit={this.waitForPlayersBehind = bool}


  def move(nrofsteps:Int): Unit ={
    var pos = this.position
    if ( pos + nrofsteps < 64 && pos + nrofsteps > -1 ){
      this.position = pos + nrofsteps
    }
    else if (pos + nrofsteps > 63) {
      var stepsBack = pos + nrofsteps - 63
      this.position = 63 - stepsBack
    }
    else{
      var stepsForward = -1*nrofsteps - pos
      this.position = stepsForward
    }
    this.lastMove = this.position - pos
  }


  def switchTurn(): Unit={
    if(this.turn){
      this.turn = false
    }
    else {
      this.turn = true
    }
  }


  def switchSkipTurn(): Unit={
    if(this.skipTurn) {
      this.skipTurn = false
    }
    else {
      this.skipTurn = true
    }
  }

  def switchGetWaitForPlayersBehind(): Unit={
    if(this.waitForPlayersBehind){
      this.waitForPlayersBehind = false
    }
    else{
      this.waitForPlayersBehind = true
    }
  }

}
