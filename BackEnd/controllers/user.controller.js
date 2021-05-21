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
    thoi_ki_thai_nghen: "0, 0",
    ngay_thu_thai: date.toISOString().substring(0, 10),
    can_nang: "0",
    chieu_cao: "0",
  });

  return res.status(200).json({ user_id, message: "Tạo tài khoản thành công" });
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

//3. Sửa thông tin user
module.exports.updateUser = (req, res) => {
  const { user_id } = req.body;

  User.findOne({ where: { user_id } })
    .then((user) => {
      Object.keys(req.body).forEach((key) => {
        user[key] = req.body[key];
      });
      return user.save();
    })
    .then(() => res.status(200).send({ message: "Thông tin đã được cập nhật" }))
    .catch((e) => {
      res.status(500).send(e.message);
    });
};

//4. Đổi mật khẩu
module.exports.updatePassword = async (req, res) => {
  const { user_id, password, newPassword } = req.body;

  const getOldPassword = await User.findOne({
    attributes: ["password"],
    where: { user_id },
  });

  const oldPassword = getOldPassword.dataValues.password;

  const passwordIsValid = bcrypt.compareSync(password, oldPassword);
  const matchPassword = bcrypt.compareSync(newPassword, oldPassword);

  if (!passwordIsValid) {
    return res.status(401).send({
      message: "Nhập sai mật khẩu cũ",
    });
  }

  if (matchPassword) {
    return res.status(401).send({
      message: "Mật khẩu mới không trùng với mật khẩu cũ",
    });
  }

  return await User.update(
    { password: bcrypt.hashSync(newPassword, 12) },
    { where: { user_id } }
  ).then(() => {
    res.status(200).send({ message: "Thay đổi mật khẩu thành công" });
  });
};
