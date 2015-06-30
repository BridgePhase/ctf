describe('Consider the following', function() {
  it('Has the correct homepage', function() {
    browser.get('http://localhost:8080/ctf');

    var heading = element.all(by.css('h1'));
    expect(heading.count()).toEqual(1);
    expect(heading.get(0).getText()).toEqual('Consider the following ...');
  });
});