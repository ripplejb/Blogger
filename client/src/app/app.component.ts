import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from "./login/login.service";
import {UserInfo} from "./models/UserInfo";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Blogger';

  userInfo: UserInfo;

  subscription: Subscription;

  isSignedIn: Boolean = false;

  constructor(private loginService: LoginService) {
  }

  signIn() {
    this.loginService.signIn();
  }

  signOut() {
    this.loginService.signOut();
  }

  ngOnInit(): void {
    this.subscription = this.loginService.getUserInfo().subscribe((userInfo) => {
      this.userInfo = new UserInfo();
      this.userInfo.name = userInfo.name;
      this.userInfo.picture = userInfo.picture;
      this.isSignedIn = true;
    },
    error => {
      this.isSignedIn = false;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
