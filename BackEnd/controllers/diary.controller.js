const uuid = require("uuid");
const moment = require("moment");

const db = require("../models/index");

const Diary = db.diary;

// Tạo nhật ký
module.exports.createDiary = async (req, res) => {
  const { user_id, content, title, subtitle } = req.body;

  await Diary.create({
    diary_id: uuid.v4(),
    user_id: user_id,
    content: content,
    title: title,
    subtitle: subtitle,
    date_diary: moment().format("YYYY-MM-DD"),
  });

  return res.status(200).json({ message: "Viết nhật ký thành công" });
};

// Lấy hết nhật ký
module.exports.getAllDiary = async (req, res) => {
  const { user_id } = req.body;

  const diary = await Diary.findAll({
    where: { user_id },
  });

  const rawData = JSON.parse(JSON.stringify(diary, null, 4));

  return res.status(200).send({ list: rawData });
};

// Lấy nhật ký theo ngày
module.exports.getDiaryByDate = async (req, res) => {
  const { date_diary } = req.body;

  const diary = await Diary.findOne({
    where: { date_diary },
  });

  const rawData = JSON.parse(JSON.stringify(diary, null, 4));

  return res.status(200).send(rawData);
};

// Lấy nhật ký theo id
module.exports.getDiaryByID = async (req, res) => {
  const { diary_id } = req.body;

  const diary = await Diary.findOne({
    where: { diary_id },
  });

  const rawData = JSON.parse(JSON.stringify(diary, null, 4));

  return res.status(200).send(rawData);
};

// Xoá nhật ký theo ngày
module.exports.deleteDiaryByDate = async (req, res) => {
  const { date_diary } = req.body;

  const diary = await Diary.destroy({
    where: { date_diary },
  });

  return res.status(200).json({ message: "Xoá thành công" });
};

// Sửa nhật ký theo id
module.exports.updateDiary = (req, res) => {
  const { diary_id } = req.body;

  Diary.findOne({ where: { diary_id } })
    .then((diary) => {
      Object.keys(req.body).forEach((key) => {
        diary[key] = req.body[key];
      });
      return diary.save();
    })
    .then(() => res.status(200).send({ message: "Thông tin đã được cập nhật" }))
    .catch((e) => {
      res.status(500).send(e.message);
    });
};
