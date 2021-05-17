module.exports = (sequelize, Sequelize) => {
  const Baby = sequelize.define(
    "baby",
    {
      user_id: {
        type: Sequelize.UUID,
        allowNull: false,
      },

      baby_id: {
        type: Sequelize.UUID,
        allowNull: false,
        unique: true,
        primaryKey: true,
      },

      tinh_trang_suc_khoe: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      name: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      gender: {
        type: Sequelize.STRING,
        allowNull: false,
      },
    },
    {
      timestamps: false,
      tableName: "baby",
    }
  );

  return Baby;
};
