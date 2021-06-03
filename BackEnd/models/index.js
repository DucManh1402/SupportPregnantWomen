const Sequelize = require("sequelize");

const config = require("../config/db");

const sequelize = new Sequelize(config.db, config.username, config.password, {
  host: config.host,
  dialect: config.dialect,
  operatorsAliases: 0,
  dialectOptions: {
    ssl: { rejectUnauthorized: false },
  },

  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000,
  },
});

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.user = require("./user.model")(sequelize, Sequelize);
db.baby = require("./baby.model")(sequelize, Sequelize);
db.diary = require("./diary.model")(sequelize, Sequelize);
db.knowledge = require("./knowledge.model")(sequelize, Sequelize);
db.schedule = require("./schedule.model")(sequelize, Sequelize);

//Associate
db.user.hasOne(db.baby, { foreignKey: "user_id", as: "baby" });
db.baby.belongsTo(db.user, { foreignKey: "user_id" });

module.exports = db;
