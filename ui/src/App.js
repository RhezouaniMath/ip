import {React, useState, Component } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  useParams,
} from "react-router-dom";

//import reactLogo from "./images/react.svg";
//import playLogo from "./images/play.svg";
//import scalaLogo from "./images/scala.svg";
import Client from "./Client";

import "./App.css";

const Tech = () => {
  const params = useParams();
  return <div>Current Route: {params.tech}</div>;
};

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { title: "Game of the Goose & Dominoes", counter: "1"};
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

  render() {
    return (
      <Router>
        <div className="App">
          <h1> Game of the Goose & Dominoes </h1>
          <nav>
            <button onClick={()=> this.getNewCount() } className = "button "> Teller: {this.state.counter} </button>
            <button id = "Game of the Goose" className = "button" > Game of the Goose </button>
            <button id = "Dominoes" className = "button" > Dominoes </button>
          </nav>
          <Routes>
            <Route path="/:tech" element={<Tech />} />
          </Routes>
        </div>
      </Router>
    );
  }
}

export default App;
