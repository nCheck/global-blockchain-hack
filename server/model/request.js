var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var Counter = mongoose.model('Counter')

var reqSchema = new Schema({
    reqId : {
        type : Number,
        default : 0
    },

    reqType : String,

    from : {
        type : String,
        default : "Anonymous"
    },

    to : {
        type : String,
        default : "Anonymous"
    },

    timeStamp : {
        type : Date,
        default : Date.now()
    },

    status :{
        type : String,
        enum : ['Accepted', 'Rejected', 'Pending'],
        default : 'Pending'
    },

    location: {
        type : String,
        default : "Unknown"
    }



})






reqSchema.pre('save', function (next){

    var corp = this;


    if (this.isNew){

        this.reqId = -1;
        Counter.find({}, function (err, doc) {
            var counter = doc[0];
            counter.seq += 1;
            corp.reqId = counter.seq;
            counter.save();
            next(); 
        })

    }


})


module.exports = mongoose.model('Request' , reqSchema)