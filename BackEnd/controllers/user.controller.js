const uuid = require("uuid");
const { Op } = require("sequelize");
const bcrypt = require("bcryptjs");

const User = require("../models/user.model");

const date = new Date();

//1. Tạo tài khoản
module.exports.createUser = async (req, res) => {
  const { email, password } = req.body;

  await User.create({
    user_id: uuid.v4(),
    email: email,
    password: bcrypt.hashSync(password, 12),
    name: "",
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
  const user = await User.findOne({
    where: { email: req.body.email },
    // where: {
    //   [Op.or]: [
    //     {
    //       email: req.body.email,
    //     },
    //     {
    //       user_id: req.body.user_id,
    //     },
    //   ],
    // },
  });
  const rawData = JSON.parse(JSON.stringify(user, null, 4));

  const { password, ...rest } = rawData;
  return res.status(400).send(rest);
};
