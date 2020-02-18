import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../models/User";
import {CommentService} from "../comment_service/comment.service";
import {Subscription} from "rxjs";
import {take} from "rxjs/operators";
import {Comment} from "../models/Comment";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent implements OnInit, OnDestroy {

  private subscription: Subscription;
  private routeParamSubscription: Subscription;

  comments: Array<Comment> = new Array<Comment>();
  isSignedIn: boolean;

  constructor(private commentService: CommentService,
              private router: Router,
              private route: ActivatedRoute) { }

  viewComment(commentId: number) {
    this.router.navigate(["/article-viewer", {parentCommentId: commentId, isSignedIn: this.isSignedIn ? '1' : '0'}]).then();
  }

  ngOnInit(): void {
    this.subscription = this.commentService.getComments(0).subscribe(
      comments => this.comments = Object.assign([], comments)
    );
    this.routeParamSubscription = this.route.params.subscribe(param => {
      this.isSignedIn = false;
      if (param["isSignedIn"]) {
        this.isSignedIn = param["isSignedIn"] == '1';
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.routeParamSubscription.unsubscribe();
  }

}
