module.exports = (sequelize, Sequelize) => {
  const Schedule = sequelize.define(
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
        type: Sequelize.DATEONLY,
        allowNull: false,
      },

      ngay_tai_kham: {
        type: Sequelize.DATEONLY,
        allowNull: false,
      },

      ghi_chu: {
        type: Sequelize.STRING,
        allowNull: false,
      },
    },
    {
      timestamps: false,
      tableName: "schedule",
    }
  );
  return Schedule;
};
