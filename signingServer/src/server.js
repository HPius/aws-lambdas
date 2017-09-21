const express = require('express');
const fileUpload = require('express-fileupload');
const AWS = require('aws-sdk');
const app = express();

app.use(fileUpload());

app.post('/upload', function(req, res) {
    if (!req.files)
        return res.status(400).send('No files were uploaded.');

    let sampleFile = req.files.sampleFile;

    var s3 = new AWS.S3();
    // s3.putObject({
    s3.upload({
        Bucket: 'picture-in.lambda-picture-farm',
        Key: sampleFile.name,
        Body: sampleFile.data,
        ACL: 'public-read'
    },function (err, resp) {
        if (err) {
            return res.status(500).send(err);
        }
        // res.send('File uploaded!');
        res.send(arguments);
        console.log(arguments);
        console.log('Successfully uploaded package.');
    });
});


app.get('/', function (req, res) {
    res.send('Nothing to see here.')
});


app.listen(3001, function () {
    console.log('Signing Server listening on port 3001!')
});
