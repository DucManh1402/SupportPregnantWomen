module.exports = (sequelize, Sequelize) => {
  const User = sequelize.define(
    "users",
    {
      user_id: {
        type: Sequelize.UUID,
        allowNull: false,
        unique: true,
        primaryKey: true,
      },

      email: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      password: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      name: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      phone: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      address: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      chu_ki_kinh: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },

      thoi_ki_cuoi_cung: {
        type: Sequelize.DATE,
        allowNull: false,
      },

      ngay_lao_dong: {
        type: Sequelize.DATE,
        allowNull: false,
      },

      thoi_ki_thai_nghen: {
        type: Sequelize.DATE,
        allowNull: false,
      },

      ngay_thu_thai: {
        type: Sequelize.DATE,
        allowNull: false,
      },
    },
    {
      timestamps: false,
      tableName: "users",
    }
  );

  return User;
};
