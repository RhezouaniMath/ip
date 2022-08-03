import {React, Component } from "react";
import Client from "../Client";
import { GooseGame } from "./GooseGame";

export class StartGooseGame extends Component {
    
  constructor(props) {
    super(props);
    this.state = {nrOfPlayers: 2}
  }

  async initializeGame(){
    Client.getGame(
      Number(this.state.nrOfPlayers)
      )
    .then( (response) => {
        this.setState(
          {
            nrOfPlayers: this.state.nrOfPlayers,
            dice: response.dice,
            gooses: response.gooses,
            finished: response.finished,
            lastMove: response.lastMove,
            position: response.position,
            turn: response.turn,
            skipTurn: response.skipTurn,
            waitForPlayersBehind: response.waitForPlayersBehind
          }
        )
      }
    )
  }

  
  render() {
    if(this.state.gooses != undefined){
      console.log("We gaan de game laten zien!")
      return <GooseGame game={this.state} setState={s=>this.setState(s)}/>
    }
    return (
      <div>
        <div>   
          Let us start Game of the Goose:
        </div>
        <input 
          placeholder="number of players" 
          onChange={
            (input) => {
              console.log(input.target.value)
              this.setState({nrOfPlayers: input.target.value})
            }
          }
          defaultValue={this.state.nrOfPlayers}
        >
        </input>
        <button onClick={()=>{this.initializeGame()}}>Start a game!</button>
      </div>
    );
  }
}

export default StartGooseGame;
//<button className = "button" > Dice: {this.state.dice} </button>
  

























/*
export function StartGooseGame() {
*/

  /*async getNewCount(){
    Client.getCount(
      Number(this.state.counter)
    )
    .then( (response) => {
        this.setState(
          {
            counter: response.counter        
          }
        )
      }
    )
  }

  async getDice(){

    Client.getDice().then( (response) => {
        this.setState({
            dice: response.dice        
          })
      })
  }*/