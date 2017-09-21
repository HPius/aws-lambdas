var AWS = require('aws-sdk'),
    fs = require('fs');

// For dev purposes only
// AWS.config.update({ accessKeyId: '...', secretAccessKey: '...' });
// collected from environment variable

// Read in the file, convert it to base64, store to S3
fs.readFile('src/lambda.png', function (err, data) {
    if (err) { throw err; }

    var base64data = new Buffer(data, 'binary');

    var s3 = new AWS.S3();
    // s3.putObject({
    s3.upload({
        Bucket: 'picture-in.lambda-picture-farm',
        Key: 'lambda.png',
        Body: base64data,
        ACL: 'public-read'
    },function (resp) {
        console.log(arguments);
        console.log('Successfully uploaded package.');
    });

    // s3.listObjects({
    //     Bucket: 'picture-in.lambda-picture-farm',
    // },function (resp) {
    //     console.log(arguments);
    //     console.log(' read.');
    // });

});