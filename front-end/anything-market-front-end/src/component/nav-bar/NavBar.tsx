import axios from "axios";
import React, { Component } from "react";

function NavBar() {
  return (
    <div>
      <button onClick={test}>Test</button>
    </div>
  );
}

function test(){
  for(let i=0; i<100; i++){
    axios
      .get(`http://localhost:8080/api/customer-order/set-queue/${i}`)
      .then(response => {})
      .catch(error => {
        console.log(error);
      });
  }
  axios
    .get("http://localhost:8080/api/customer-order/show")
    .then((response) => {});
}

export default NavBar;
