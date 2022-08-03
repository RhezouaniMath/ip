/* eslint-disable no-undef */
function getSummary(cb) {
  return fetch("/api/summary", {
    accept: "application/json",
  })
    .then(checkStatus)
    .then(parseJSON)
    .then(cb);
}

function getCount(count){
  return fetch("/count", {
    method: 'POST',
    accept: "application/json",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({counter: count})
  })
    .then(checkStatus)
    .then(parseJSON)
}

function getDice(){
    return fetch("/dice", {
      method: 'POST',
      headers: {
      'Content-Type': 'application/json', 'Accept': "application/json"
    },
    })
      .then(checkStatus)
      .then(parseJSON)
}

function getGame(nrOfPlayers){
  return fetch("/start", {
    method: 'POST',
    accept: "application/json",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({nrOfPlayers: nrOfPlayers})
  })
  .then(checkStatus)
  .then(parseJSON)
}

function playGame(gooses, lastMove, position, turn, skipTurn, waitForPlayersBehind){
  return fetch("/play", {
    method: 'POST',
    accept: "application/json",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      gooses: gooses,
      lastMove: lastMove,
      position: position,
      turn: turn,
      skipTurn: skipTurn,
      waitForPlayersBehind
    })
  })
  .then(checkStatus)
  .then(parseJSON)
}

/*
val gooses = (json \ "nrOfPlayers").as[Array[Int]]
      var lastMove = (json \ "lastMove").as[Array[Int]]
      var position = (json \ "position").as[Array[Int]]
      var turn = (json \ "turn").as[Array[Boolean]]
      var skipTurn = (json \ "skipTurn").as[Array[Boolean]]
      var waitForPlayersBehind = (json \ "waitForPlayersBehind").as[Array[Boolean]]
*/

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  console.log(error); // eslint-disable-line no-console
  throw error;
}

function parseJSON(response) {
  return response.json();
}

//const Client = { getSummary, getCount };
const Client = { getSummary, getCount, getDice, getGame, playGame};
export default Client;
