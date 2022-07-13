import {React, useState, Component } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Switch,
  Link,
  useParams,
} from "react-router-dom";
import { Header } from "./Header/Header";

//import reactLogo from "./images/react.svg";
//import playLogo from "./images/play.svg";
//import scalaLogo from "./images/scala.svg";
import Client from "./Client";

import "./App.css";
import { GooseGame } from "./GooseGame/GooseGame";
import { Dominoes } from "./Dominoes/Dominoes"

const Tech = () => {
  const params = useParams();
  return <div>Current Route: {params.tech}</div>;
};

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { title: "Game of the Goose & Dominoes", counter: "1", dice: " "};
  }

  async componentDidMount() {
    Client.getSummary((summary) => {
      this.setState({
        title: summary.content,
      });
    });
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
    return (
      <Router>
        <Header />
        
        <div className="App">
        <Routes>
          <Route exact path="/GooseGame" element={<GooseGame/>}/>
          <Route exact path="/Dominoes" element={<Dominoes/>} />
        </Routes>
        <nav>
          <button onClick={()=> this.getNewCount() } className = "button "> Teller: {this.state.counter} </button>
          <button onClick={()=> this.getDice() } className = "button" > Dice: {this.state.dice} </button>
          </nav>
        </div>
      </Router>
    );
  }
}

export default App;
//<button className = "button" > Dice: {this.state.dice} </button>