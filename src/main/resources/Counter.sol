pragma solidity ^0.4.23;
// We have to specify what version of compiler this code will compile with

contract Counter {

    uint8 public counts;

    constructor() public payable {

    }

    function getCounts() public view returns (uint8) {
        return counts;
    }

    function increment() public {
        counts += 1;
    }

    function decrement() public {
        counts -= 1;
    }

}