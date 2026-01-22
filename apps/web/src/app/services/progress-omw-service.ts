import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProgressOmwService {
  private stompClient: any;
  progressSubject = new Subject<any>();

  connect() {
    const socket = new SockJS('http://localhost:8080/ws-progress');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/import-omw', (message: any) => {
        this.progressSubject.next(JSON.parse(message.body));
      });
    });
  }
}
