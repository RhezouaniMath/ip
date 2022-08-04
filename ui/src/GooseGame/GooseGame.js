import React, { useState }  from "react";
import "./GooseGame.css";
import Client from "../Client";

const array1 = [];
const array2 = [];
const array3 = [];
const array4 = [];
const array5 = [];
const array6 = [];
const array7 = [];
const array8 = [];

for (let i = 0; i < 8; i++) {
    if (i==0){
        let text = "START"
        array1[i] = text;
    }
    else{ 
        let number = i;
        let text = number.toString();
        array1[i] = text;
    }
}

for (let i = 0; i < 8; i++) {
        let number = 15-i;
        let text = number.toString();
        array2[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = i+16;
        let text = number.toString();
        array3[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = 31-i;
        let text = number.toString();
        array4[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = i+32;
        let text = number.toString();
        array5[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = 47-i;
        let text = number.toString();
        array6[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = i+48;
        let text = number.toString();
        array7[i] = text;
}

for (let i = 0; i < 8; i++) {
        let number = 63-i;
        let text = number.toString();
        array8[i] = text;
}

export function destringifyListToArray(array){
    var length = array.length
    var finalarraylist = array.map(function(item) {return parseInt(item, length + 1);});
    return finalarraylist
}

export function describingSpot(i){
    if (i == 6){
        return <div id="bridge" className = "rule"> <div className = "titleRule"> Bridge </div> <img src="./bridge.jpg" width ="100" height="auto" /> <div className="text">Go to 12</div>  </div>
    }
    else if (i == 19){
        return <div id="inn" className = "rule"> <div className = "titleRule" > Inn </div> <img src="./inn.jpg" width ="60" height="auto" /> <div className="text"> Stay the night. Skip a turn. </div> </div>
    }
    else if (i == 31){
        return <div id="well" className = "rule"> <div className = "titleRule" > Well </div> <img src="./well.jpg" width ="40" height="40" /> <div className="text"> In de put... Rescue me </div> </div>
    }
    else if (i == 42){
        return <div id="bush" className = "rule"> <div className = "titleRule" > Bush </div> <img src="./bush.jpg" width ="80" height="60" /> <div className="text"> Go back to 37. </div> </div>
    }
    else if (i == 52){
        return <div id="prison" className = "rule"> <div className = "titleRule" > Prison </div> <img src="./prison.jpg" width ="auto" height="60" /> <div className="text"> "Lock her up." </div> </div>
    }
    else if (i == 58){
        return <div id="death" className = "rule"> <div className = "titleRule" > Death </div> <img src="./skull.jpg" width ="40" height="50" /> <div className="text"> Start all over again. </div> </div>
    }
    else if (i == 63){
        return <div id = "FINISH" className = "FINISH">  FINISH!!! </div>
    }
    else{
        return <div className = "noDescr"> </div>
    }
}

export function diceDiv(dice){
    if (dice == -1){
        return <div>
            We haven't throw a dice yet.
        </div>
    }
    else if (dice == 0){
        return <div>
            We have skipped a turn.
        </div>
    }
    else{
        return <div>
            Dice: {dice}
        </div>
    }
}

export function wieHeeftErGewonnen(array){
    var length = array.length
    for (let i=0; i<length; i++){
        if(array[i]==63){
            return <div>
                Player {i+1} won the game.
            </div>
        }
    }
    return <div>
        We are still playing.
    </div>
}

export function GooseGame(state) {

    const [game,setGame]=useState(state)

    function play(){
        Client.playGame(
          game.game.gooses,
          game.game.lastMove,
          game.game.position,
          game.game.turn,
          game.game.skipTurn,
          game.game.waitForPlayersBehind
          )
        .then( (response) => {
            console.log(game)
            setGame({game:
              {
                nrOfPlayers: game.game.nrOfPlayers,
                dice: response.dice,
                gooses: response.gooses,
                finished: response.finished,
                lastMove: response.lastMove,
                position: response.position,
                turn: response.turn,
                skipTurn: response.skipTurn,
                waitForPlayersBehind: response.waitForPlayersBehind
              }
            }
            )
          }
        )
      }

      function toShowOrNotToShowButton(array){
        var length = array.length
        for (let i=0; i<length; i++){
            if(array[i]==63){
                return <div>
                    The game has ended. It is not possible to make a move anymore.
                </div>
            }
        }
        return <div>
                <button onClick={()=>{play()}}> Make a move. </button>
                </div>
    }

    function showGoose(i){
        for (let index=0; index<5; index++){
            if (game.game.gooses[index]==i){
                return <div> <img src="./goose.jpg" width ="auto" height="60" /> </div>
            }
        }
    }

    function showPlayer(i){
        //var array = new Array(game.game.nrOfPlayers)
        //var counter = 0
        var str = ""
        for (let index=0; index<game.game.nrOfPlayers; index++){
            if (game.game.position[index]==i){
                var nr = index + 1
                if (str == ""){
                    str = str + nr
                }
                else{
                    str = str + "," + nr
                }
            }
        }
        if (str != "")
            return <div> Player(s) {str} </div>
        }

    //console.log(game);

    return  <div>

        <h2> 
            Game of the Goose
        </h2>

        <h4>
            Number of players
        </h4>
        <label>
            <div>
            Number of players: {Number(game.game.nrOfPlayers)}
            </div>
        </label>

        <h4>
        Position of players
        </h4>
        <label>
            Position of players: {JSON.stringify(game.game.position)}
        </label>  

        <h4>
            Dice
        </h4>
        <label>
            {diceDiv(Number(game.game.dice))}
        </label>

        <h4>
            Game status
        </h4>
        <label>
            {wieHeeftErGewonnen(game.game.position)}
        </label>

        <h4>
            Play Game
        </h4>
        <label>
                {toShowOrNotToShowButton(game.game.position)}
        </label>



        <h4>
            Game board
        </h4>

        <div  className = "spotsBox">
           {array1.map(     (i) => (<div key={i} className ="spot"> {i} {describingSpot(i, game.game.position)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje1">
                Game of the Goose
            </div>
        </div>

        <div  className = "spotsBox">
            {array2.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje2">
                Game of the Goose
            </div>
        </div>

        <div   className = "spotsBox">
            {array3.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje1">
                Game of the Goose
            </div>
        </div>

        <div    className = "spotsBox">
            {array4.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>

        <div  className="box">
            <div className="boxje2">
                Game of the Goose
            </div>
        </div>

        <div  className = "spotsBox">
            {array5.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
        <div className="box">
            <div  className="boxje1">
                Game of the Goose 
            </div>
        </div>

        <div  className = "spotsBox">
            {array6.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)}  {showPlayer(i)} </div>)        )}
        </div>
        <div  className="box">
            <div  className="boxje2">
                Game of the Goose
            </div>
        </div>

        <div    className = "spotsBox">
            {array7.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
        <div  className="box">
            <div  className="boxje1">
                Game of the Goose
            </div>

        </div>
        <div    className = "spotsBox">
            {array8.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} {showGoose(i)} {showPlayer(i)} </div>)        )}
        </div>
    </div>
}