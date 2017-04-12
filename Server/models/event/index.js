var express 	= require('express');
var controller 	= require('./event.controller');

var router = express.Router();

router.get('/all', 	controller.getAll);
router.get('/:id', 	controller.get);
router.post('/', 	controller.post);
router.put('/:id', 	controller.put);
router.delete('/all', controller.delete);

module.exports = router;