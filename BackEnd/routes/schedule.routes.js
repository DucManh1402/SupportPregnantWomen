const express = require("express");

const scheduleController = require("../controllers/schedule.controller");

const router = express.Router();

//1. Tạo lịch khám
router.post("/schedules", scheduleController.createSchedule);

//2. lấy nhật ký theo ngày
router.post("/schedules/get-schedule", scheduleController.getScheduleByDate);

//3. Lấy hết
router.post("/schedules/get-schedules", scheduleController.getAllSchedule);

//4. Xoá theo ngày
router.post("/schedules/delete-schedule", scheduleController.deleteSchedule);

router.post("/schedules/update-schedule", scheduleController.updateSchedule);

module.exports = router;
