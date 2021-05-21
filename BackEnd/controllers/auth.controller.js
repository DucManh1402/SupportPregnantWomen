const bcrypt = require("bcryptjs");

const db = require("../models");

const User = db.user;

module.exports.login = async (req, res) => {
  const { email, password } = req.body;

  const user = await User.findOne({ where: { email } });
  const passwordIsValid = bcrypt.compareSync(password, user.password);

  if (!passwordIsValid) {
    return res.status(400).send({ message: "Đăng nhập thất bại" });
  } else {
    const rawData = JSON.parse(JSON.stringify(user, null, 4));

    const { password, ...rest } = rawData;
    return res.status(200).send(rest);
  }
};
