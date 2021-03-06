# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a
Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Added

- Lint Dockerfile with <https://github.com/hadolint/hadolint>

### Changed

- Update `assets/tailwind.config.js` based on the new TailwindCSS v3 defaults

#### Languages and services

- Update `OpenJDK` to `11.0.13`
- Update `Node` to `14.18.1`
- Update `PostgreSQL` to `14.1` and switch to Debian Bullseye Slim

#### Back-end dependencies

- Update `flyway-play` to `7.17.0`
- Update `postgresql` to `42.3.1`

#### Front-end dependencies

- Update `@babel/core` to `7.16.0`
- Update `@babel/preset-env` to `7.16.4`
- Update `@babel/register` to `7.16.0`
- Update `autoprefixer` to `10.4.0`
- Update `copy-webpack-plugin` to `10.0.0`
- Update `css-loader` to `6.5.1`
- Update `css-minimizer-webpack-plugin` to `3.2.0`
- Update `mini-css-extract-plugin` to `2.4.5`
- Update `postcss-loader` to `6.2.1`
- Update `postcss` to `8.4.4`
- Update `tailwindcss` to `3.0.0`
- Update `webpack-cli` to `4.9.1`
- Update `webpack` to `5.65.0`

## [0.2.0] - 2021-10-10

### Changed

- Update page title to add spaces between `Docker + Play`

#### Languages and services

- Update `Scala` to `11.0.12` and switch to Debian Bullseye Slim
- Update `PostgreSQL` to `14.0`
- Update `Redis` to `6.2.6`

#### Back-end dependencies

- Update `flyway-play` to `7.14.0`
- Update `postgresql` to `42.2.24`

#### Front-end dependencies

- Update `@babel/core` to `7.15.8`
- Update `@babel/preset-env` to `7.15.8`
- Update `@babel/register` to `7.15.3`
- Update `autoprefixer` to `10.3.7`
- Update `copy-webpack-plugin` to `9.0.1`
- Update `css-loader` to `6.4.0`
- Update `css-minimizer-webpack-plugin` to `3.1.1`
- Update `mini-css-extract-plugin` to `2.4.2`
- Update `postcss-loader` to `6.1.1`
- Update `postcss` to `8.3.9`
- Update `tailwindcss` to `2.2.16`
- Update `webpack-cli` to `4.9.0`
- Update `webpack` to `5.58.1`

### Fixed

- Move sbt apt repo from https://dl.bintray.com to https://repo.scala-sbt.org

## [0.1.1] - 2021-05-26

### Fixed

- `hello` database migration directory now gets renamed by the rename script
- Remove leading space in `_init.sql` database migration file

## [0.1.0] - 2021-05-25

### Added

- Everything!

[Unreleased]: https://github.com/oleksandra-holovina/docker-play-example/compare/0.2.0...HEAD
[0.2.0]: https://github.com/oleksandra-holovina/docker-play-example/compare/0.1.1...0.2.0
[0.1.1]: https://github.com/oleksandra-holovina/docker-play-example/compare/0.1.0...0.1.1
[0.1.0]: https://github.com/oleksandra-holovina/docker-play-example/releases/tag/0.1.0
