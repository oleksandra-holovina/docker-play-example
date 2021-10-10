# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a
Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Changed

- Update page title to add spaces between `Docker + Play`

#### Languages and services

- Update `Redis` to `6.2.6`

#### Back-end dependencies

- Update `flyway-play` to `7.13.0`
- Update `postgresql` to `42.2.23`

#### Front-end dependencies

- Update `@babel/core` to `7.15.0`
- Update `@babel/preset-env` to `7.15.0`
- Update `@babel/register` to `7.15.3`
- Update `autoprefixer` to `10.3.1`
- Update `copy-webpack-plugin` to `9.0.1`
- Update `css-loader` to `6.2.0`
- Update `css-minimizer-webpack-plugin` to `3.0.2`
- Update `mini-css-extract-plugin` to `2.2.0`
- Update `postcss` to `8.3.6`
- Update `postcss-loader` to `6.1.1`
- Update `tailwindcss` to `2.2.7`
- Update `webpack` to `5.51.1`
- Update `webpacki-cli` to `4.8.0`

### Fixed

- Move sbt apt repo from https://dl.bintray.com to https://repo.scala-sbt.org

## [0.1.1] - 2021-05-26

### Fixed

- `hello` database migration directory now gets renamed by the rename script
- Remove leading space in `_init.sql` database migration file

## [0.1.0] - 2021-05-25

### Added

- Everything!

[Unreleased]: https://github.com/oleksandra-holovina/docker-play-example/compare/0.1.1...HEAD
[0.1.1]: https://github.com/oleksandra-holovina/docker-play-example/compare/0.1.0...0.1.1
[0.1.0]: https://github.com/oleksandra-holovina/docker-play-example/releases/tag/0.1.0
