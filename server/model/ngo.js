var mongoose = require('mongoose');
var Schema = mongoose.Schema
var Counter = mongoose.model('Counter')

var ngoSchema = new Schema({
    name : String,
    ngoId : {
        type : Number,
        default : 0
    } , 

    rating : {
        type : Number,
        default : 0.0
    },

    address : String,

    location: {
        lat:{
            type:Number

        },
        lon:{
            type:Number
        }
    }

})



ngoSchema.pre('save', function (next){

    var corp = this;
    this.reqId = -1;
    Counter.find({}, function (err, doc) {
        var counter = doc[0];
        counter.seq += 1;
        corp.ngoId = counter.seq;
        counter.save();
        next(); 
    })


})





module.exports = mongoose.model('Ngo' , ngoSchema);