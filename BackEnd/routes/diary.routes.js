const express = require("express");

const diaryController = require("../controllers/diary.controller");

const router = express.Router();

//1. Tạo nhật ký
router.post("/diaries", diaryController.createDiary);

//2. lấy nhật ký theo ngày
router.post("/diaries/get-diary", diaryController.getDiaryByDate);

//3. Lấy hết
router.post("/diaries/get-diaries", diaryController.getAllDiary);

//4. Xoá theo ngày
router.post("/diaries/delete-diary", diaryController.deleteDiaryByDate);

router.post("/diaries/update-diary", diaryController.updateDiary);

module.exports = router;
