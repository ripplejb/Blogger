import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ArticleListComponent} from "./article-list/article-list.component";
import {ArticleViewerComponent} from "./article-viewer/article-viewer.component";


const routes: Routes = [
  {
    path: 'article-list',
    component: ArticleListComponent
  },
  {
    path: 'article-viewer',
    component: ArticleViewerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
