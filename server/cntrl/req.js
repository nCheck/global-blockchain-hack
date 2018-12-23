var mongoose              = require('mongoose');

var Ngo = mongoose.model('Ngo'),
    Counter = mongoose.model('Counter'),
    Request = mongoose.model('Request');

var web3 = require('../model/web3'),
createContract = require('../model/donationContract');

module.exports.addRequest = async (req, res)=>{

    var reqType = req.body.type,
        from = req.body.from,
        to = req.body.to,
        location = req.body.location

    Request.create({
        reqType : reqType,
        from : from,
        to : to,
        location : location
    }, async (err, doc)=>{

        if(!err){
            
        // Blockchain Updation
        const contract = createContract();

        // registering request
        await contract.methods.userDonate(doc.reqId).send({from : '0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E' });

        res.send({status : "Done" , id : doc.reqId})
    

        }
        else{
            res.send("Error")
        }
    })
}

module.exports.getRequestOfNgo = async (req, res)=>{

    var ngoName = req.params.name;
    var ngo = await Ngo.findOne({name : ngoName});

    Request.find({to:ngoName} , (err , doc)=>{
        
        if( !err){
            res.send({requests : doc , id : ngo.ngoId});
        }
        else{
            res.send("Error")
        }
    });
}

module.exports.getRequests = (req, res)=>{

    Request.find({}, (err, doc)=>{
        if(!err){
            res.send(doc)
        }
        else{
            res.send({err : err});
        }
    })
}

