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

export function destringifyListToArray(arraylist){
    var arraylist1 = arraylist.split("List(").pop()
    var arraylist2 = arraylist1.split(")")
    var arraylist3 = arraylist2[0]
    var arraylist4 = arraylist3.split(',')
    var length = arraylist4.length
    var finalarraylist = arraylist4.map(function(item) {return parseInt(item, length + 1);});
    return finalarraylist
}

export function describingPlayers(i, arraylist){
    var finalarraylist = destringifyListToArray(arraylist)
    var length = finalarraylist.length
    for (let n=0; n < length; n++){
        if (i==finalarraylist[n]){
            <div> Player {n+1} </div>
        }
    }
}

/* (gooses, lastMove, position, turn, skipTurn, waitForPlayersBehind) 
"dice" -> String.valueOf(dice),
          "gooses" -> String.valueOf(goosesList),
          "finished" -> String.valueOf(finished),
          "lastMove" -> String.valueOf(lastMove2),
          "position" -> String.valueOf(position2),
          "turn" -> String.valueOf(turn2),
          "skipTurn" -> String.valueOf(skipTurn2),
          "waitForPlayersBehind" -> String.valueOf(waitForPlayersBehind2)*/

export function describingSpot(i, arraylist){
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

    //console.log(game);

    return  <div>
        <h2> 
            Game of the Goose
        </h2>
        <h4>
            Number of players
        </h4>
        <label>
            Number of players: {game.game.nrOfPlayers}
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
            Dice: {game.game.dice}
        </label>
        <h4>
            Play Game
        </h4>
            <div>
            <button onClick={()=>{play()}}> Make a move. </button>
            </div>
        <h4>
            Game board
        </h4>
        <div  className = "spotsBox">
           {array1.map(     (i) => (<div key={i} className ="spot"> {i} {describingSpot(i, game.game.position)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje1">
                Game of the Goose
            </div>
        </div>
        <div  className = "spotsBox">
            {array2.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje2">
                Game of the Goose
            </div>
        </div>
        <div   className = "spotsBox">
            {array3.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
        <div className="box">
            <div className="boxje1">
                Game of the Goose
            </div>
        </div>
        <div    className = "spotsBox">
            {array4.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>

        <div  className="box">
            <div className="boxje2">
                Game of the Goose
            </div>
        </div>
        <div  className = "spotsBox">
            {array5.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
        <div className="box">
            <div  className="boxje1">
                Game of the Goose 
            </div>
        </div>
        <div  className = "spotsBox">
            {array6.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
        <div  className="box">
            <div  className="boxje2">
                Game of the Goose
            </div>
        </div>
        <div    className = "spotsBox">
            {array7.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
        <div  className="box">
            <div  className="boxje1">
                Game of the Goose
            </div>
        </div>
        <div    className = "spotsBox">
            {array8.map(     (i) => (<div key={i}  className ="spot"> {i} {describingSpot(i)} </div>)        )}
        </div>
    </div>
}