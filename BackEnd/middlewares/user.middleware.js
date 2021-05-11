const User = require("../models/user.model");

module.exports.checkEmailDuplicate = async (req, res, next) => {
  const { email } = req.body;

  const user = await User.findOne({ where: { email } });

  if (user)
    return res.status(400).send({ message: "Tên đăng nhập đã tồn tại" });
  else {
    next();
  }
};

module.exports.checkEmailExist = async (req, res, next) => {
  const { email } = req.body;

  const user = await User.findOne({ where: { email } });

  if (!user) return res.status(404).send({ message: "Email không tồn tại" });
  else {
    next();
  }
};
