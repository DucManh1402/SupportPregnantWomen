const User = require("../models/user.model");

module.exports.login = async (req, res) => {
  const { email, password } = req.body;

  const user = await User.findOne({ where: { email, password } });

  if (user) return res.status(200).send("Asdad");
  else {
    return res.status(404).send("Không thấy gì hết");
  }
};
