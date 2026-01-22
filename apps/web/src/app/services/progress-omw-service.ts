import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import { Subject } from 'rxjs';
import { Client } from '@stomp/stompjs';
@Injectable({
  providedIn: 'root',
})
export class ProgressOmwService {
  private stompClient: Client | null = null;
  progressSubject = new Subject<any>();
  progress$ = new Subject<any>();

  connect() {
/*     const socket = new SockJS('http://localhost:8080/ws-progress');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/import-omw', (message: any) => {
        this.progressSubject.next(JSON.parse(message.body));
      });
    }); */
    const socket = new SockJS('http://localhost:8080/ws-progress');
    this.stompClient = new Client({
      webSocketFactory: () => socket,
      onConnect: () => {
        this.stompClient?.subscribe('/import-omw', (msg) => {
          this.progress$.next(JSON.parse(msg.body));
        });
      }
    });
    this.stompClient.activate();
  }
  disconnect() {
    this.stompClient?.deactivate();
  }
}
