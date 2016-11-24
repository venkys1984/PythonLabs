const express = require('express');

// Constants
const PORT = 8080;

// App
const app = express();
app.get('/', function (req, res) {
  res.send('Hello world\n');
});

app.listen(PORT, "0.0.0.0");
console.log('Running on datamanager-dashboard, port:' + PORT);
