const http = require('http');
var express = require('express');
var app = express();
var bodyParser = require('body-parser');

app.use(bodyParser.json());//使用body parser用于解析post的body
app.use(bodyParser.urlencoded({ extended: true }));//使用body parser用于解析post的body
app.all('*', function(req, res, next) {
res.header("Access-Control-Allow-Origin", "*");
res.header("Access-Control-Allow-Headers", "*");
res.header("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
res.header("X-Powered-By",' 3.2.1');
res.header("Content-Type", "application/json;charset=utf-8");
next();
});
app.get('/token', function(req, res) {
	res.send('xxxxxxxxxx');
})

app.get('/json', function(req, res){
	let myjson = {
		name: 'gyt',
		age: '20',
		success: 'fail'
	}
	res.send(myjson)
})
app.post('/login', function(req, res){
	let data = req.body;
	console.log(data);
	const tmp=JSON.stringify(req.headers);
	console.log('token'+tmp);
	console.log('token'+tmp['access-token']);

	let message1 = {success: true}
	let message2 = {success: false}

	if (data.username=="gyt" && data.password=='123456') {
		res.send(message1);
	}
	else {
		res.send(message2);
	}
})
var server = app.listen(8081, function() {
	var host = server.address().address
	var port = server.address().port
	console.log("应用实例，访问地址为 http://%s:%s", host, port)
})