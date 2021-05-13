const db = require("../models/index");

const Baby = db.baby;

//1. Tạo em bé
module.exports.createBaby = async (req, res) => {
  const {
    user_id,
    baby_id,
    tinh_trang_suc_khoe,
    age,
    gender,
  } = req.body;

  await Baby.create({
    user_id: user_id,
    baby_id: baby_id,
    tinh_trang_suc_khoe: tinh_trang_suc_khoe,
    age: age,
    gender: gender,
  });

  return res.status(200).json({ message: "Tạo em bé thành công" });
};

//2. Lấy em bé
module.exports.getBabyById = async (req, res) => {
  const { user_id } = req.body;

  const baby = await Baby.findOne({
    where: { user_id },
  });
  
  const rawData = JSON.parse(JSON.stringify(baby, null, 4));

  return res.status(200).send(rawData);
};
