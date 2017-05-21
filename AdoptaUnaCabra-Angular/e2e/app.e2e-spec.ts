import { AdoptaUnaCabraAngularPage } from './app.po';

describe('adopta-una-cabra-angular App', () => {
  let page: AdoptaUnaCabraAngularPage;

  beforeEach(() => {
    page = new AdoptaUnaCabraAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
