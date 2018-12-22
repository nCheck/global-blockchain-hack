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

    var rating = await contract.methods.getRating(70).call();
    res.send({rating : rating});


}

module.exports.acceptRequest = async (req, res)=>{

    var reqId = req.body.reqid,
        ngoId = req.body.ngoid;
    
    await Request.findOneAndUpdate({reqId : reqId}, { $set: { status: 'Accepted' }}, (err, doc)=>{
        console.log("updated!")
    })

    const contract = createContract();


    await contract.methods.ngoReact(reqId,ngoId).send({from : '0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E' });

    //get the updated rating to update in db
    var rating = await contract.methods.getRating(70).call();

    await Ngo.findById("5c1cd74a505ae43b03e227f5", (err, doc)=>{

        if(!err){

            doc.rating = rating; //TODO
            doc.save();
            res.send({status:"Done" , rating : rating});
        }
        else{
            res.send({err : err})
        }
    } )


}

//////

module.exports.rejectRequest = (req , res)=>{

    var id = req.params.reqid;

    Request.findOneAndUpdate({reqId : id}, { $set: { status: 'Rejected' }} , (err, doc)=>{
        console.log("Rejected");
        res.send({status : "Rejected"});
    } )
}


module.exports.addNgo = async (req, res)=>{

    //Do update in blockchain too

    var name = req.body.name,
        address = req.body.address,
        location = req.body.location;
    
    Ngo.create({
        name : name, address : address, location : location
    }, async (err, doc)=>{

        const contract = createContract();

        // Dummy NGO Address
        var dummyNgo = "0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E";

        await contract.methods.addNgo(ngoId,dummyNgo)
            .send({from : '0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E' });

        console.log(doc);

        res.send("Done")
    })
}

module.exports.getNgos = (req, res)=>{

    Ngo.find({}, (err, doc)=>{

        res.send(doc);
        
    })
}


module.exports.interact = async (req, res)=>{
    
    var ngoId = req.params.ngoid;

    const contract = createContract();


    await contract.methods.interact(ngoId).send({from : '0xb1d04265d4f578fc7c38161FeA26a1F0D7d83C2E' });

    var rating = await contract.methods.getRating(ngoId).call();

    res.send({updatedRating : rating});

}