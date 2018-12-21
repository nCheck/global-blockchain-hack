var web3 = require('./web3'),
    donabi = require('./donabi');

const contractAddress = '0x52b19b11e1397825f54F39FEF77851A8ca047362';
module.exports = ()=> {
    return new web3.eth.Contract(donabi, contractAddress)
}