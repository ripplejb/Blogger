import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "../models/User";
import {Comment} from "../models/Comment"
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {CommentService} from "../comment_service/comment.service";
import {take} from "rxjs/operators";

@Component({
  selector: 'app-article-viewer',
  templateUrl: './article-viewer.component.html',
  styleUrls: ['./article-viewer.component.css']
})
export class ArticleViewerComponent implements OnInit, OnDestroy {

  private routeParamSubscription: Subscription;


  parentCommentId: number;
  isSignedIn: boolean;

  currentComment: Comment = new Comment();
  comments: Array<Comment> = new Array<Comment>();

  onSubmit() {
    const comment = new Comment();

    if (this.parentCommentId == null)
      comment.parentId = 0;
    else
      comment.parentId = this.parentCommentId;

    comment.comment = this.currentComment.comment;

    // Save comment here with a promise.
    this.commentService.setComments(comment).pipe(take(1)).subscribe(
      c => {
        if  (this.parentCommentId == null || this.parentCommentId == 0) {
          this.parentCommentId = c.commentId;
        }
        comment.commentId = c.commentId;
        comment.author = c.author;
        this.comments.splice(1, 0, comment);
        this.currentComment = new Comment();
      },
      e => {
        console.log(e);
      }
    );

  }

  loadComments() {
    this.comments = new Array<Comment>();
    this.commentService.getComments(this.parentCommentId).pipe(take(1)).subscribe(
      comments => {
        let i = 0;
        comments.forEach((comment => {
          if (i == 0)
            this.comments.push(comment);
          else
            this.comments.splice(1, 0, comment);
          i++;
        }));
      }
    );
  }

  constructor(private route: ActivatedRoute,
              private commentService: CommentService) { }

  ngOnInit(): void {
    this.routeParamSubscription = this.route.params.subscribe(param => {
      if (param["parentCommentId"]) {
        this.parentCommentId = +param["parentCommentId"];
        if (this.parentCommentId !== 0)
          this.loadComments();
      }
      this.isSignedIn = false;
      if (param["isSignedIn"]) {
        this.isSignedIn = param["isSignedIn"] == '1';
      }
    });
  }

  ngOnDestroy(): void {
    this.routeParamSubscription.unsubscribe();
  }

}
