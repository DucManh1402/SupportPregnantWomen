const uuid = require("uuid");

const db = require("../models/index");

const Baby = db.baby;

//1. Tạo em bé
module.exports.createBaby = async (req, res) => {
  const { user_id, tinh_trang_suc_khoe, name, gender } = req.body;

  await Baby.create({
    user_id: user_id,
    baby_id: uuid.v4(),
    tinh_trang_suc_khoe: tinh_trang_suc_khoe,
    name: name,
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

//3. Sửa thông tin baby
module.exports.updateBaby = (req, res) => {
  const { user_id } = req.body;

  Baby.findOne({ where: { user_id } })
    .then((baby) => {
      Object.keys(req.body).forEach((key) => {
        baby[key] = req.body[key];
      });
      return baby.save();
    })
    .then(() => res.status(200).send({ message: "Thông tin đã được cập nhật" }))
    .catch((e) => {
      res.status(500).send(e.message);
    });
};
