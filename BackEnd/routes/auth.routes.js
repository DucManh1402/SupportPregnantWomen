const express = require("express");

const authController = require("../controllers/auth.controller");
const userMiddleware = require("../middlewares/user.middleware");

const router = express.Router();

router.post(
  "/auth/login",
  userMiddleware.checkEmailExist,
  authController.login
);

module.exports = router;
