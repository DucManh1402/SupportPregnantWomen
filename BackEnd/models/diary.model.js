const Sequelize = require("sequelize");
const { db } = require("../config/db");

const Diary = db.define(
  "diary",
  {
    user_id: {
      type: Sequelize.UUID,
      allowNull: false,
    },

    diary_id: {
      type: Sequelize.UUID,
      allowNull: false,
      unique: true,
      primaryKey: true,
    },

    content: {
      type: Sequelize.STRING,
      allowNull: false,
    },

    date_diary: {
      type: Sequelize.DATE,
      allowNull: false,
    },
  },
  {
    timestamps: false,
  }
);

module.exports = Diary;
