var path = require('path');
var webpack = require('webpack');
var plugins = [];

if(process.env.NODE_ENV === 'production') {
	plugins.push(new webpack.DefinePlugin({
		"process.env": {
			NODE_ENV: JSON.stringify("production"),
		},
	}));
}

module.exports = {
	plugins: plugins,
	entry: './src/index.js',
	output: {
		path: path.resolve(__dirname, 'static'),
		filename: 'index.js',
	},
	module: {
		loaders: [
			{
				test: /\.js$/,
				loader: 'babel-loader',
				exclude: /node_modules/
			},
			{
				test: /\.less$/,
				loader: 'style-loader!css-loader!less-loader'
			},
		]
	},
};