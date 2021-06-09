const uuid = require("uuid");
const moment = require("moment");

const db = require("../models/index");

const Schedule = db.schedule;

// Tạo lịch khám
module.exports.createSchedule = async (req, res) => {
  const { user_id, ghi_chu, ngay_kham, ngay_tai_kham } = req.body;

  await Schedule.create({
    schedule_id: uuid.v4(),
    user_id: user_id,
    ghi_chu: ghi_chu,
    ngay_kham: ngay_kham,
    ngay_tai_kham: ngay_tai_kham,
  });

  return res.status(200).json({ message: "Lịch khám đã được tạo thành công" });
};

// Lấy tất cả ngày khám
module.exports.getAllSchedule = async (req, res) => {
  const { user_id } = req.body;

  const diary = await Schedule.findAll({
    where: { user_id },
  });

  const rawData = JSON.parse(JSON.stringify(diary, null, 4));

  return res.status(200).send({ list: rawData });
};

// Lấy lịch khám theo ngày tái khám
module.exports.getScheduleByDate = async (req, res) => {
  const { user_id, ngay_tai_kham } = req.body;

  const schedule = await Schedule.findOne({
    where: { user_id, ngay_tai_kham },
  });

  const rawData = JSON.parse(JSON.stringify(schedule, null, 4));

  return res.status(200).send(rawData);
};

// Lấy lịch khám theo id
module.exports.getScheduleByID = async (req, res) => {
  const { schedule_id } = req.body;

  const schedule = await Schedule.findOne({
    where: { schedule_id },
  });

  const rawData = JSON.parse(JSON.stringify(schedule, null, 4));

  return res.status(200).send(rawData);
};

// Xoá lịch khám theo ngày
module.exports.deleteSchedule = async (req, res) => {
  const { user_id, schedule_id } = req.body;

  const schedule = await Schedule.destroy({
    where: { user_id, schedule_id },
  });

  return res.status(200).json({ message: "Xoá thành công" });
};

// Sửa lịch khám theo id
module.exports.updateSchedule = (req, res) => {
  const { user_id, schedule_id } = req.body;

  Schedule.findOne({ where: { user_id, schedule_id } })
    .then((schedule) => {
      Object.keys(req.body).forEach((key) => {
        schedule[key] = req.body[key];
      });
      return schedule.save();
    })
    .then(() => res.status(200).send({ message: "Thông tin đã được cập nhật" }))
    .catch((e) => {
      res.status(500).send(e.message);
    });
};
