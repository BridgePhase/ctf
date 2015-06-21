var gulp = require('gulp');
var debug = require('gulp-debug');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var uglifyCss = require('gulp-uglifycss');

gulp.task('uglify', function() {
  gulp.src([	 
	  'src/main/resources/public/modules/*.js',
	  'src/main/resources/public/modules/services/**/*.js',
	  'src/main/resources/public/modules/directives/**/*.js',
	  'src/main/resources/public/modules/controllers/**/*.js'
	])
	.pipe(debug())
	.pipe(uglify())
	.pipe(concat('ctf-app-module.js'))
	.pipe(gulp.dest('src/main/resources/public'));
});

gulp.task('concatenateJs', function() {
	gulp.src([
		'src/main/resources/public/libraries/angular/angular.min.js',
		'src/main/resources/public/libraries/angular-ui-router/release/angular-ui-router.min.js',
		'src/main/resources/public/ctf-app-module.js'
	])
	.pipe(debug())
	.pipe(concat('ctf-app.js'))
	.pipe(gulp.dest('src/main/resources/public'));
});

gulp.task('uglifyCss', function() {
  gulp.src([	 
		'src/main/resources/public/styles/bridgephase.css'
	])
	.pipe(debug())
	.pipe(uglifyCss())
	.pipe(concat('bridgephase.min.css'))
	.pipe(gulp.dest('src/main/resources/public/styles/'));
});

gulp.task('concatenateCss', ['uglifyCss'], function() {
	gulp.src([
		'src/main/resources/public/styles/pure-min.css',
		'src/main/resources/public/styles/grids-responsive-min.css',
		'src/main/resources/public/styles/bridgephase.min.css'
	])
	.pipe(debug())
	.pipe(concat('ctf-styles.css'))
	.pipe(gulp.dest('src/main/resources/public'));
});

gulp.task('default', ['uglify', 'concatenateJs', 'concatenateCss'], function() {
});