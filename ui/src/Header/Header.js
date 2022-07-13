import React from "react";
import { Link } from "react-router-dom";
//import "./Header.css";
//import urlLogo from "./logo.jpg";

/**
 * A Header component with a Sogyo logo, the name of the application and several links to different pages
 */
export function Header() {
    return <header className="main-header">

        <div className="main-navigation">
            <h1> Game of the Goose & Dominoes </h1>
        </div>

        <div className="main-navigation">

            <button className="button"> 
                <Link to="/GooseGame">Game of the Goose</Link>
            </button>

            <button className="button"> 
                <Link to="/Dominoes">Dominoes </Link>
            </button>

        </div>

    </header>
}