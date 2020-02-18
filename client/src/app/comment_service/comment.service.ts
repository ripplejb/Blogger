import {Injectable, isDevMode} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/Comment"

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private readonly baseUrl: string = "";

  private url: string = "/api/comments";

  constructor(private http: HttpClient) {
    if (isDevMode()) {
      this.baseUrl = "http://localhost:8080";
    }
  }

  getComments(parentCommentId: number): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(this.baseUrl + this.url + "/" + parentCommentId.toString())
  }

  setComments(comment: Comment): Observable<Comment> {
    console.log(JSON.stringify(comment));
    return this.http.post<Comment>(this.baseUrl + this.url, comment);
  }
}
