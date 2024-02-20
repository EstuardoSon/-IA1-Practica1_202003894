import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  url = 'http://localhost:8080';
  tags = [{tag: 'tag1', percent:10}, {tag: 'tag2', percent:20}, {tag: 'tag3', percent:30}];

  constructor(private http:HttpClient) { }

  public postURL(endpoint: string, body: any, options?: any) {
    return this.http.post(this.url+endpoint, body);
  }
}
