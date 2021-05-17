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

//3. Update User
router.post("/users/update-user", userController.updateUser);

module.exports = router;
