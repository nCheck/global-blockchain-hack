var web3 = require('./web3'),
    donabi = require('./donabi');

const contractAddress = '0x9360ef1d15587f406c9f3dfb21e81ea3599235c7';
module.exports = ()=> {
    return new web3.eth.Contract(donabi, contractAddress)
}