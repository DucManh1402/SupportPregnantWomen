const Sequelize = require("sequelize");

module.exports.db = new Sequelize(
  "d7u29u0thnabgl",
  "rbirrrlcgxqapi",
  "7351898dd270e97719a64e35163faeaba8f31f667f3d4121e95e187dee7665bc",
  {
    host: "ec2-3-217-219-146.compute-1.amazonaws.com",
    dialect: "postgres",
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
  }
);
