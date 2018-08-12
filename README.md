# ethereum-counter-web3j-sample
Sample Counter Ethereum Smart Contract Web3j API

## Purpose and Motivation
The purpose of this project is to enable a Java server side REST API to interact with Ethereum Transaction node.
<br />
The project demonstrates a Counter Smart Contract which has the following methods: increment, decrement and counts.
<br />
The REST API exposes the above Smart Contract methods and also the following methods: deployContract and loadContract.

## Build
Update the Wallet Private Key in CounterHandler.java class
<br />
In Case of Existing Smart Contract - Update the Contract Address in CounterHandler.java class
*****
mvn clean
<br />
mvn package - this will start the embedded Apache Tomcat server.
*****
Deploy Contract using: http://localhost:8080/network/deployContract
<br />
Load Existing Contract using: http://localhost:8080/network/loadContract
*****
Interact with the Ethereum Smart Contract using:
<br />http://localhost:8080/counter/increment
<br />http://localhost:8080/counter/decrement
<br />http://localhost:8080/counter/counts
