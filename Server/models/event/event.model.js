module.exports = function(sequelize, DataTypes) {
	var Event = sequelize.define('Event', {
		id: {
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true,
			allowNull: false
		},
		date: {
			type: DataTypes.STRING
		},
		eventType: {
			type: DataTypes.STRING
		},
		location: {
			type: DataTypes.STRING
		},
		number: {
			type: DataTypes.STRING
		},
		username: {
			type: DataTypes.STRING
		},
		people: {
			type: DataTypes.STRING
		},
		status: {
			type: DataTypes.STRING
		},
		street: {
			type: DataTypes.STRING
		}
	});
	return Event;
};