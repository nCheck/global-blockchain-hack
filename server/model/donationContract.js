var web3 = require('./web3'),
    donabi = require('./donabi');

const contractAddress = '0xc204722e55c8a1589618f478d02e8502193e7fbd';
module.exports = ()=> {
    return new web3.eth.Contract(donabi, contractAddress)
}