var mongoose              = require('mongoose');

var Ngo = mongoose.model('Ngo'),
    Counter = mongoose.model('Counter'),
    Request = mongoose.model('Request');


module.exports.addRequest = (req, res)=>{

    var reqType = req.body.type,
        from = req.body.from,
        to = req.body.to,
        location = {
            lat : req.body.lat,
            lon : req.body.lon
        }
    
    Request.create({
        reqType : reqType,
        from : from,
        to : to,
        location : location
    }, (err, doc)=>{

        if(!err){
            
            // Blockchain Updation

        }
        else{
            res.send("Error")
        }
    })
}

module.exports.getRequestOfNgo = (req, res)=>{

    var ngoName = req.params.name;

    Request.find({to:ngoName} , (err , doc)=>{
        
        if( !err){
            res.send({requests : doc});
        }
        else{
            res.send("Error")
        }
    });
}

