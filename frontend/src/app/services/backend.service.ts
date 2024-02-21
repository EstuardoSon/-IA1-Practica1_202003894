import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  url = 'http://localhost:8080';
  tags:Array<any> = [];
  cont = 0;

  constructor(private http:HttpClient) { }

  public postURL(endpoint: string, body: any, options?: any) {
    return this.http.post(this.url+endpoint, body);
  }

  public setTags(tags: Array<any>) {
    this.tags = tags;
  }

  public setCont(cont: number) {
    this.cont = cont;
  }
}
