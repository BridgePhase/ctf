exports.config = {
  seleniumAddress: 'http://localhost:4444/wd/hub',
  specs: ['*.test.js'],
  capabilities: {
    browserName: 'firefox'
  }
};