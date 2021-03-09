const {merge} = require('webpack-merge');
const path = require('path');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');

const node_modules_path = '/node_modules';

const common = {
    watchOptions: {
        poll: (process.env.WEBPACK_WATCHER_POLL || 'false') === 'true'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: `/${node_modules_path}/`,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader'
                ]
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/i,
                type: 'asset/resource',
                generator: {
                    filename: 'fonts/[name][ext]'
                }
            },
        ]
    },
    optimization: {
        minimize: true,
        minimizer: [
            `...`,
            new CssMinimizerPlugin()
        ],
    },
    plugins: [
        new CopyWebpackPlugin({patterns: [{from: __dirname + '/static'}]}),
        new MiniCssExtractPlugin({filename: 'css/app.css'})
    ],
    entry: {
        index: path.resolve(__dirname, 'src', 'app', 'app.js')
    },
    output: {
        filename: '[name].[contenthash].js',
        path: path.resolve(__dirname, 'build'),
        publicPath: '/'
    },
};

module.exports = [
    merge(common, {
        entry: [
            __dirname + "/app/app.css",
            __dirname + "/app/app.js"
        ],
        output: {
            path: __dirname + "/../public",
            filename: "js/app.js"
        },
        resolve: {
            modules: [
                node_modules_path,
                __dirname + "/app"
            ]
        },
        plugins: [
            new CopyWebpackPlugin({patterns: [{from: __dirname + '/static'}]}),
            new MiniCssExtractPlugin({filename: "css/app.css"})
        ]
    })
];
