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

      ngay_sinh: {
        type: Sequelize.DATE,
        allowNull: false,
      },

      chieu_cao: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      can_nang: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      tinh_trang_suc_khoe: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      age: {
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
