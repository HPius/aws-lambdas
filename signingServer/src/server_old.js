const express = require('express');
const app = express();

app.get('/', function (req, res) {
    res.send('Nothing to see here.')
})

app.use('/s3', require('react-s3-uploader/s3router')({
    bucket: "picture-in.lambda-picture-farm",
    region: 'eu-central-1', //optional
    // signatureVersion: 'v4', //optional (use for some amazon regions: frankfurt and others)
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:3000',
        'Access-Control-Allow-Credentials': 'true'
    }, // optional
    ACL: 'private', // this is default
    uniquePrefix: true // (4.0.2 and above) default is true, setting the attribute to false preserves the original filename in S3
}));

app.listen(3001, function () {
    console.log('Signing Server listening on port 3001!')
});
