const db = require("../models/index");

const Baby = db.baby;

//1. Tạo em bé
module.exports.createBaby = async (req, res) => {
  const {
    user_id,
    baby_id,
    ngay_sinh,
    chieu_cao,
    can_nang,
    tinh_trang_suc_khoe,
    age,
    gender,
  } = req.body;

  await Baby.create({
    user_id: user_id,
    baby_id: baby_id,
    ngay_sinh: ngay_sinh,
    chieu_cao: chieu_cao,
    can_nang: can_nang,
    tinh_trang_suc_khoe: tinh_trang_suc_khoe,
    age: age,
    gender: gender,
  });

  return res.status(200).json({ message: "Tạo em bé thành công" });
};