import {Injectable, isDevMode} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {User} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly baseUrl: string = "";

  private getUserInfoUrl: string = "/api/users";
  private signInWithGoogleUrl: string = "/oauth/login/google";
  private signOutUrl: string = "/logout";

  constructor(private http: HttpClient) {
  }

  getUser(): Observable<User> {
    return this.http.get<any>(this.baseUrl + this.getUserInfoUrl).pipe(
      map(data => {
        let user = new User();
        user.name = data.name;
        user.picture = data.picture;
        return user;
      }),
      catchError(error =>
      {
        console.log(error);
        return throwError(error);
      })
    );
  }

  signIn() {
    window.location.href = this.baseUrl + this.signInWithGoogleUrl;
  }

  signOut() {
    window.location.href = this.baseUrl + this.signOutUrl;
  }
}
