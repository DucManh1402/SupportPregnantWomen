const bcrypt = require("bcryptjs");

const db = require("../models");

const User = db.user;

module.exports.login = async (req, res) => {
  try {
    const { email, password } = req.body;

    const user = await User.findOne({ where: { email } });
    const passwordIsValid = bcrypt.compareSync(password, user.password);

    if (!passwordIsValid)
      return res.status(400).send({ message: "Mật khẩu không đúng" });
    else {
      return res.status(200).send({ message: "Đăng nhập thành công" });
    }
  } catch (e) {
    console.log(e);
  }
};
