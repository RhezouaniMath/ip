import {React, useState, Component } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Redirect,
  Switch,
  Link,
  useParams,
} from "react-router-dom";
//import { Header } from "./Header/Header";
import Client from "./Client";
import "./App.css";
import { Dominoes } from "./Dominoes/Dominoes"
import {StartGooseGame} from "./GooseGame/StartGooseGame"




class App extends Component {

  constructor(props) {
    super(props);
    this.state = { title: " ", counter: "1", dice: " "};
  }

  async componentDidMount() {
    Client.getSummary(
      (summary) => {this.setState({title: summary.content,});}
    );
  }

  async getNewCount(){
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
  }

  render() {
    if(this.state.gooseGameStarted){
      return <StartGooseGame game={this.state.gooseGame}/>
    }
    return (
      <Router>
        <div className="App">
          <nav>
            <button onClick={()=> this.getNewCount() } className = "button "> Teller: {this.state.counter} </button>
            <button onClick={()=> this.getDice() } className = "button" > Dice: {this.state.dice} </button>
            <button onClick={()=> this.setState({gooseGameStarted: true})} className = "button">Start a Goose Game</button>
          </nav>
          <Routes>
            <Route path="/Dominoes/Dominoes" element={<Dominoes/>} />
            <Route path="/GooseGame/StartGooseGame" element={<StartGooseGame game={this.state.gooseGame}/>}/>
          </Routes>
        </div>
      </Router>
    );
  }
}

export default App;


/*
<button className="button"> 
                <Link to="/StartGooseGame">Start Game of the Goose</Link>
          </button>
          <button className="button"> 
                <Link to="/Dominoes">Dominoes </Link>
          </button>
//<Header />
//<Route path="/PlayGooseGame" element={<GooseGame game={this.state.gooseGame}/>}/>
//<button className = "button" > Dice: {this.state.dice} </button>
  
  async getNewPlayer(){
    Client.getNewPlayer(
      this.state.player.name
    )
    .then(
      (response)=> {
        this.setState(
          {
            player1: response.player
          }
        )
      }
    )
  }
  
  */