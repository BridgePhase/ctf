var gulp = require('gulp');
var debug = require('gulp-debug');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var uglifyCss = require('gulp-uglifycss');
var runSequence = require('run-sequence');
var less = require('gulp-less');

/**
 * By default, we want to turn our LESS files into CSS, and concatenate them
 * and then do the same for JavaScript (except the LESS part) 
 */
gulp.task('default', function() {
	// this neat plugin lets us run tasks in sequences since we can't
	// concatenate if we haven't uglified yet
	runSequence('lessify', 'uglifyCss', 'concatenateCss');
	runSequence('uglifyJs', 'concatenateJs');
});

gulp.task('uglifyJs', function() {
  return gulp.src([	 
	  'src/main/resources/public/modules/*.js',
	  'src/main/resources/public/modules/services/**/*.js',
	  'src/main/resources/public/modules/directives/**/*.js',
	  'src/main/resources/public/modules/controllers/**/*.js'
	])
	.pipe(debug({title: 'uglifyjs'}))
	.pipe(uglify())
	.pipe(concat('ctf-app-module.js'))
	.pipe(gulp.dest('src/main/resources/public'));
});

gulp.task('concatenateJs', function() {
	return gulp.src([
		'src/main/resources/public/libraries/angular/angular.min.js',
		'src/main/resources/public/libraries/angular-ui-router/release/angular-ui-router.min.js',
		'src/main/resources/public/libraries/angular-touch/angular-touch.min.js',
		'src/main/resources/public/libraries/angular-carousel/dist/angular-carousel.min.js',
		'src/main/resources/public/libraries/d3/d3.min.js',
		'src/main/resources/public/libraries/c3/c3.min.js',
		'src/main/resources/public/ctf-app-module.js'
	])
	.pipe(debug({title: 'concatenatejs'}))
	.pipe(concat('ctf-app.js'))
	.pipe(gulp.dest('src/main/resources/public'));
});

gulp.task('lessify', function() {
	return gulp.src([
		'src/main/resources/public/styles/ctf.less'
	])
	.pipe(debug({title:'LESSing files'}))
	.pipe(less())
	.pipe(gulp.dest('src/main/resources/public/styles'));
})

gulp.task('uglifyCss', function() {
  return gulp.src([	 
		'src/main/resources/public/styles/ctf.css'
	])
	.pipe(debug({title:'minifyCss'}))
	.pipe(uglifyCss())
	.pipe(concat('ctf.min.css'))
	.pipe(gulp.dest('src/main/resources/public/styles/'));
});

gulp.task('concatenateCss', function() {
	return gulp.src([
		'src/main/resources/public/styles/pure-min.css',
		'src/main/resources/public/styles/grids-responsive-min.css',
		'src/main/resources/public/libraries/angular-carousel/dist/angular-carousel.min.css',
		'src/main/resources/public/libraries/c3/c3.min.css',
		'src/main/resources/public/styles/ctf.min.css'
	])
	.pipe(debug({title:'concatenateCss'}))
	.pipe(concat('ctf-styles.css'))
	.pipe(gulp.dest('src/main/resources/public'));
});