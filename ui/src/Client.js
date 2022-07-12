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

const Client = { getSummary, getCount };
export default Client;
