describe('Consider the following', function() {
  it('Has the correct homepage', function() {
    console.log('Going to URL now');
    browser.get('http://127.0.0.1:8080/ctf');

    var heading = element.all(by.css('h1'));
    console.log('Queried for heading');
    expect(heading.count()).toEqual(1);
    expect(heading.get(0).getText()).toEqual('Consider the following ...');
  });
});