var web3 = require('./web3'),
    donabi = require('./donabi');

const contractAddress = '0x6f62A96Fc857Cd6b49513AabC696ff2dC047a92A';
module.exports = ()=> {
    return new web3.eth.Contract(donabi, contractAddress)
}