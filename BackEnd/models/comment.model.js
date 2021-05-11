const Sequelize = require("sequelize");
const { db } = require("../config/db");

const Comment = db.define(
  "comment",
  {
    comment_id: {
      type: Sequelize.UUID,
      allowNull: false,
      unique: true,
      primaryKey: true,
    },

    user_id: {
      type: Sequelize.UUID,
      allowNull: false,
    },

    post_id: {
      type: Sequelize.UUID,
      allowNull: false,
    },

    content: {
      type: Sequelize.STRING,
      allowNull: false,
    },

    date: {
      type: Sequelize.DATE,
      allowNull: false,
    },
  },
  {
    timestamps: false,
  }
);

module.exports = Comment;
