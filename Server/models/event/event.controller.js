var models 	= require('../');
var myIo 	= require('../../io');

exports.get = function(req, res, next) {
	if(!req.params.id)
		return res.status(422).json({error: {message: 'Must provide id'}});
		
	models.Event.findOne({
		where: {
			id: req.params.id
		}
	}).then(function (event, err) {
		if (err)
			return next(err);
		if (event)
		{
			res.status(200).json(event);
		}
		else
		{
			return res.status(422).json('{"err":"invalid id"}');
		}
	}).error(function(err) {
		console.log(err);
		res.status(403).send('Forbidden');
	});
};

exports.getAll = function(req, res, next) {
	models.Event.findAll().then(function (events, err) {
		if(err)
			return next(err);
		
		res.send(events);
	}).error(function(err) {
		console.log(err);
		res.status(403).send('Forbidden');
	});
};

exports.post = function(req, res, next) {
	console.log(req.body);
	if (req.body.calling)
	{
		myIo.io.emit('calling', req.body.number);
		res.status(200).json(req.body);
		return;
	}
		
	var today = new Date();
	var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
	var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	var dateTime = date + ' ' + time;

	models.Event.create({
		date: dateTime,
		eventType: req.body.eventType,
		location: JSON.stringify(req.body.location),
		number: req.body.number,
		username: JSON.stringify(req.body.username),
		people: JSON.stringify(req.body.people),
		status: 'pending'
	}).then(function(event) {
		myIo.io.emit('addPending', event);
		return res.status(200).json(event);
	}).catch(function (err) {
		if(err)
			return res.status(422).json(err);
	});
};

exports.put = function(req, res, next) {
	if(!req.params.id)
		return res.status(422).json({error: {message: 'Must provide id'}});
		
	models.Event.findOne({
		where: {
			id: req.params.id
		}
	}).then(function (event, err) {
		if (err)
			return next(err);
		if (event)
		{
			event.date = req.body.date;
			event.eventType = req.body.eventType;
			event.location = JSON.stringify(req.body.location);
			event.number = req.body.number;
			event.username = JSON.stringify(req.body.username);
			event.people = JSON.stringify(req.body.people);
			event.status = req.body.status;
			event.street = req.body.street;
			
			event.save().then(function(event, err) {
				if (err)
					return res.status(422).json(err);
				res.status(200).json(event);
			});
		}
		else
		{
			return res.status(422).json('{"err":"invalid id"}');
		}
	}).error(function(err) {
		console.log(err);
		res.status(403).send('Forbidden');
	});
};

exports.delete = function(req, res, next) {
	models.sequelize.query('DROP TABLE Events');
};