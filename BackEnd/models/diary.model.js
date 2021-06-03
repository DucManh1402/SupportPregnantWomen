module.exports = (sequelize, Sequelize) => {
  const Diary = sequelize.define(
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
        type: Sequelize.DATEONLY,
        allowNull: false,
      },
    },
    {
      timestamps: false,
      tableName: "diary",
    }
  );
  return Diary;
};
