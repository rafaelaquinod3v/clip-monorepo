import { TestBed } from '@angular/core/testing';

import { FileOmwService } from './file-omw-service';

describe('FileOmwService', () => {
  let service: FileOmwService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileOmwService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
