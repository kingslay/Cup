var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonsPlugin = new webpack.optimize.CommonsChunkPlugin('common.js');
module.exports = {
    entry:{
        main: './main.js',
        bootstrap: 'bootstrap/dist/css/bootstrap.css',
        antd:'antd/lib/index.css',
        user: './src/main/component/user.jsx'
    },
    output: {
        path: path.join(__dirname, './src/main/resources/static'),
        filename: '[name].js'
    },
    module: {
        loaders: [
            {
                test: /.jsx?$/, loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
                    presets: ['es2015', 'react']
                }
            },
            { test: /\.css$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader") },
            {test: /\.json/, loaders: ['json-loader']},
            {test: /\.(png|jpg)$/, loader: 'url-loader?limit=8192'},
            { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file" },
            { test: /\.(woff|woff2)$/, loader:"url?prefix=font/&limit=5000" },
            { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=application/octet-stream" },
            { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=image/svg+xml" }
        ]
    },
    plugins: [
        commonsPlugin,
        new ExtractTextPlugin("/css/[name].css")
    ]
};