import { TestBed } from '@angular/core/testing';

import { ProgressOmwService } from './progress-omw-service';

describe('ProgressOmwService', () => {
  let service: ProgressOmwService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgressOmwService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
