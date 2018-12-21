var mongoose = require('mongoose'),
	database	 = 'mongodb://sanes4:sanes4ever@ds147487.mlab.com:47487/dhelfy'
	
mongoose.connect(database , { useNewUrlParser: true });
mongoose.connection.on('connected' , () =>{
	console.log('connected')
});

require('./counter');
require('./request');
require('./ngo');