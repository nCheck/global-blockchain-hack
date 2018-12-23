require('./model/db');
var mongoose = require('mongoose');

var Ngo = mongoose.model('Ngo'),
    Counter = mongoose.model('Counter'),
    Request = mongoose.model('Request');



Counter.create({});

// Ngo.create({
//     name : "Smile Baby",
//     address : "Chakala, Andheri",
//     location : {
//         lat : 18,
//         lon : 56
//     }
// }, (err, res)=>{
//     console.log(res);
// })

// Request.create({

//     reqType : "Partik",
    

// } , (err, res)=>{
//     console.log(res);
//     res.save();
// });

// Request.findById('5c1cd1b4a7f3aa3799c45675', (err,res)=>{
//     res.save();
//     console.log(res);
// })