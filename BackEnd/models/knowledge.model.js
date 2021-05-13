module.exports = (sequelize, Sequelize) => {
  const Knowledge = sequelize.define(
    "knowledge",
    {
      knowledge_id: {
        type: Sequelize.UUID,
        allowNull: false,
        unique: true,
        primaryKey: true,
      },

      giai_doan: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      kien_thuc: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      dich_vu: {
        type: Sequelize.STRING,
        allowNull: false,
      },

      thiet_bi_vat_dung: {
        type: Sequelize.STRING,
        allowNull: false,
      },
    },
    {
      timestamps: false,
      tableName: "knowledge",
    }
  );
  return Knowledge;
};
