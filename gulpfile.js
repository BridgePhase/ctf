var gulp = require('gulp');
var debug = require('gulp-debug');
var uglify = require('gulp-uglify');


gulp.task('uglify', function() {
  gulp.src([	 
	  'src/main/resources/public/libraries/angular/angular.min.js',
	   'src/main/resources/public/libraries/angular-ui-router/release/angular-ui-router.min.js',
	  'src/main/resources/public/modules/*.js',
	  'src/main/resources/public/modules/services/**/*.js',
	  'src/main/resources/public/modules/directives/**/*.js',
	  'src/main/resources/public/modules/controllers/**/*.js'
	])
	.pipe(debug())
	.pipe(uglify())
    .pipe(gulp.dest('src/main/resources/public/'));
});

gulp.task('concatenate', function() {
	
})

gulp.task('default', function() {
});