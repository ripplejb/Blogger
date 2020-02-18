import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from "./login_service/login.service";
import {User} from "./models/User";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Blogger';

  userInfo: User;

  subscription: Subscription;

  isSignedIn: Boolean;

  constructor(private loginService: LoginService,
              private router: Router) {
  }

  goHome() {
    this.router.navigate(["/article-list", {isSignedIn: this.isSignedIn ? '1' : '0'}]).then();
  }

  goToNewArticle() {
    this.router.navigate(["/article-viewer", {parentCommentId: 0, isSignedIn: this.isSignedIn ? '1' : '0'}]).then();
  }

  signIn() {
    this.loginService.signIn();
  }

  signOut() {
    this.loginService.signOut();
  }

  ngOnInit(): void {
    this.subscription = this.loginService.getUser().subscribe((userInfo) => {
      this.userInfo = new User();
      this.userInfo.name = userInfo.name;
      this.userInfo.picture = userInfo.picture;
      this.isSignedIn = true;
      this.goHome();
    },
    error => {
      this.isSignedIn = false;
      this.goHome();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
