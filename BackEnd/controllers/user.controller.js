const bcrypt = require("bcryptjs");

const db = require("../models");

const User = db.user;
const date = new Date();

//1. Tạo tài khoản
module.exports.createUser = async (req, res) => {
  const { name, user_id, email, password } = req.body;

  await User.create({
    user_id: user_id,
    email: email,
    password: bcrypt.hashSync(password, 12),
    name: name,
    phone: "",
    address: "",
    chu_ki_kinh: 0,
    thoi_ki_cuoi_cung: date.toISOString().substring(0, 10),
    ngay_lao_dong: date.toISOString().substring(0, 10),
    thoi_ki_thai_nghen: date.toISOString().substring(0, 10),
    ngay_thu_thai: date.toISOString().substring(0, 10),
  });

  return res.status(200).json({ message: "Tạo tài khoản thành công" });
};

//2. Lấy thông tin user
module.exports.getUser = async (req, res) => {
  const { user_id } = req.body;

  const user = await User.findOne({
    where: { user_id },
  });
  const rawData = JSON.parse(JSON.stringify(user, null, 4));

  const { password, ...rest } = rawData;
  return res.status(200).send(rest);
};
