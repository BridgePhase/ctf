exports.config = {
  seleniumAddress: 'http://127.0.0.1:4444/wd/hub',
  specs: ['*.test.js'],
  capabilities: {
    browserName: 'firefox'
  }
};