const Sequelize = require("sequelize");
const { db } = require("../config/db");

const Schedule = db.define(
  "schedule",
  {
    user_id: {
      type: Sequelize.UUID,
      allowNull: false,
    },

    schedule_id: {
      type: Sequelize.UUID,
      allowNull: false,
      unique: true,
      primaryKey: true,
    },

    ngay_kham: {
      type: Sequelize.DATE,
      allowNull: false,
    },

    ngay_tai_kham: {
      type: Sequelize.DATE,
      allowNull: false,
    },

    ghi_chu: {
      type: Sequelize.STRING,
      allowNull: false,
    },
  },
  {
    timestamps: false,
  }
);

module.exports = Schedule;
