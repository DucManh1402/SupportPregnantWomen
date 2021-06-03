const express = require("express");
const cors = require("cors");

const db = require("./models");

const app = express();
app.use(cors());
app.use(express.json());

const PORT = process.env.PORT || 3000;

// 1. Auth routes
app.use("/api", require("./routes/auth.routes"));
//2. User routes
app.use("/api", require("./routes/user.routes"));
//3. Baby routes
app.use("/api", require("./routes/baby.routes"));

app.use("/api", require("./routes/diary.routes"));

app.use("/api", require("./routes/schedule.routes"));

db.sequelize
  .authenticate()
  .then(() => {
    app.listen(PORT, () => {
      console.log(`Server is running on port ${PORT}`);
    });
  })
  .catch((err) => console.log(err.message));
