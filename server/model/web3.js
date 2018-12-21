// import { Web3 } from 'web3';
var Web3 = require('web3');
var web3 = new Web3(new Web3.providers.HttpProvider('http://127.0.0.1:7545'));

module.exports = web3;