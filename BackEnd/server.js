const express = require("express");
const cors = require("cors");

const { db } = require("./config/db");

db.authenticate()
  .then(() => console.log("Connected Database"))
  .catch((err) => console.log(err.message));

const app = express();
app.use(cors());
app.use(express.json());

const PORT = process.env.PORT || 3000;

// 1. Auth routes
app.use("/api", require("./routes/auth.routes"));
//2. User routes
app.use("/api", require("./routes/user.routes"));

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
