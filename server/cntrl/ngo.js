var mongoose              = require('mongoose');
var Ngo = mongoose.model('Ngo'),
    Counter = mongoose.model('Counter'),
    Request = mongoose.model('Request');

var web3 = require('../model/web3'),
    createContract = require('../model/donationContract');


module.exports.getRating = async (req, res)=>{

    var ngoName = req.params.name;
    //Actually get from Blockchain
    const contract = createContract();

    var rating = await contract.methods.getRating(1).call();
    res.send({rating : rating});



    // Ngo.findOne({name : ngoName}, (err, ngo)=>{

    //     if (!err)        
    //     res.send({rating : ngo.rating});
    //     else
    //     res.send("Error");
        
    // })
}

module.exports.acceptRequest = async (req, res)=>{

    var reqId = req.body.reqid,
        ngoId = req.body.ngoid;
    
    const contract = createContract();

    // await web3.eth.ngoReact(reqId, ngoId);
    await contract.methods.ngoReact(reqId,ngoId).send({from : '0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E' });
    res.send({d:"Done"});


}



module.exports.addNgo = (req, res)=>{

    //Do update in blockchain too

    var name = req.body.name,
        address = req.body.address,
        location = {
            lat : req.body.lat,
            lon : req.body.lon
        }
    
    Ngo.create({
        name : name, address : address, location : location
    }, (err, doc)=>{

        //TODO: adding in blockchain
        console.log(doc);

        res.send("Done")
    })
}

module.exports.getNgos = (req, res)=>{

    Ngo.find({}, (err, doc)=>{

        res.send(doc);
        
    })
}