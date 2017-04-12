var config 		= require('../config');
var Sequelize 	= require('sequelize');
var fs			= require('fs');
var glob 		= require("glob-all");

var db			= {};
var sequelize 	= new Sequelize(config.database.database, config.database.user, config.database.password, config.database.config);

glob.sync([__dirname + '/**/*.model.js']).forEach(function(file) {
	var model = sequelize["import"](file);
	db[model.name] = model;
});

Object.keys(db).forEach(function(modelName) {
	if ("associate" in db[modelName]) {
		db[modelName].associate(db);
	}
});

db.sequelize = sequelize;
db.Sequelize = Sequelize;

module.exports = db;