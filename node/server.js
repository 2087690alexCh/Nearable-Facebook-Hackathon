/*
 * Node.js modules
 */
var querystring = require('querystring');


/*
 * Third-party modules
 */
var express = require('express');
var request = require('request');
var unirest = require('unirest');
var Twit = require('twit');


/*
 * Custom modules
 */
var config = require('./config');
var helpers = require('./helpers');


/*
 * Initialize modules instances
 */
var app = express();


/*
 * Twitter initial config
 */
var edges = helpers.getBoundingBox([config.location.LAT, config.location.LNG], 5);
var locations = edges[0] + ',' + edges[1] + ',' + edges[2] + ',' + edges[3];
console.log(locations);

var twitterConfig = {
  consumer_key: config.twitter.CONSUMER_KEY,
  consumer_secret: config.twitter.CONSUMER_SECRET,
  access_token: config.twitter.ACCESS_TOKEN,
  access_token_secret: config.twitter.ACCESS_TOKEN_SECRET
};
var T = new Twit(twitterConfig);


/*
 * Instagram initial config
 */
var minTimestamp = Math.round(+new Date() / 1000);
var usedPostsIds = [];


/*
 * Helper functions
 */
function getInstagram() {
  var params = {
    lat: config.location.LAT,
    lng: config.location.LNG,
    access_token: config.instagram.ACCESS_TOKEN,
    distance: 5000,
    min_timestamp: minTimestamp 
  };
  var query = querystring.stringify(params);
  
  request('https://api.instagram.com/v1/media/search?' + query, function (error, response, body) {
    var newPosts = [];

    minTimestamp = Math.round(+new Date() / 1000);
    console.log(minTimestamp);
    if (!error && response.statusCode == 200) {
      var posts = [];
      try {
        posts = JSON.parse(body).data;
      } catch (e) {
        console.log('Error parsing JSON.');
      }

      for (var i = 0; i < posts.length; i++) {
        var post = posts[i];
        if (usedPostsIds.indexOf(post.id) != -1) continue;
        newPosts.push(post);

        var formData = {
          src: post.images.standard_resolution.url,
          lat: post.location.latitude,
          lng: post.location.longitude,
          urli: post.link,
          id: post.id
        };
        //console.log(formData);
        
        unirest.post('http://alex.ngrok.com/instagram')
        .type('json')
        .send(formData)
        .end(function (response) {
          console.log(response.body);
        });
      }
      setTimeout(function() { getInstagram(); }, 5000);
    } 
  });
}


/*
 * Routing
 */
app.get('/', function (req, res) {
  res.send('Hello, Moto!');
});

app.get('/send', function (req, res) {
  unirest.post('http://alex.ngrok.com/instagram')
  .type('json')
  .send({
    instagram: 'alex'
  })
  .end(function (response) {
    console.log(response.body);
  });
  res.on('OK');
});

app.get('/instagram/get', function (req, res) {
  getInstagram();
  res.end('OK');
});

app.get('/twitter/get', function (req, res) {
  var stream = T.stream('statuses/filter', {locations: locations});
  stream.on('tweet', function (tweet) {
    //console.log(tweet);

    var lat = lng = 0;
    if (tweet.geo) {
      lat = tweet.geo.coordinates[0];
      lng = tweet.geo.coordinates[1];
    } else if (tweet.coordinates) {
      lat = tweet.coordinates.coordinates[0];
      lng = tweet.coordinates.coordinates[1];
    }

    var formData = {
      text: tweet.text,
      id: tweet.id_str,
      lat: lat,
      lng: lng,
      urlt: 'https://twitter.com/statuses/' + tweet.id_str
    };
    console.log(formData);

    unirest.post('http://alex.ngrok.com/twitter')
      .type('json')
      .send(formData)
      .end(function (response) {
        console.log(response.body);
      });
  });

  res.send('OK');
});


/*
 * Start server
 */
var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Nearable app listening at http://%s:%s', host, port);
});
