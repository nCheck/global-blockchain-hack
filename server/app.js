var express=require('express');
var app=express();
var parser                = require('body-parser');
const dir                 = __dirname;
const port                = 9966;
require('./model/db');


app.set('view engine', 'ejs');
app.use(parser.urlencoded({extended:true}));
app.use(parser.json());


///Require Routes
var apiRoute = require('./route/api');

app.use('/api', apiRoute)


app.listen(port , function () {
	console.log('Site is active on localhost:' + port+'/');
});