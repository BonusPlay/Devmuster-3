var express = require('express');
var io 		= require('../io');

var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
	res.render('index', { title: 'IT FUCKING WORKS' });
});

router.use('/event', require('../models/event'));

module.exports = router;