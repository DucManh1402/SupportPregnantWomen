const express = require("express");

const userController = require("../controllers/user.controller");
const userMiddleware = require("../middlewares/user.middleware");

const router = express.Router();

//1. Tạo user
router.post(
  "/users",
  userMiddleware.checkEmailDuplicate,
  userController.createUser
);

//2. Get User
router.post("/users/get-user", userController.getUser);

module.exports = router;
