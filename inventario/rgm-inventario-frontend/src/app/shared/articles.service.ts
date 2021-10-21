import { Injectable } from "@angular/core";
import { Subject } from "rxjs/Subject";
import { HttpClient } from "@angular/common/http";
import { Article } from "./article.model";
import { ResponseRs } from "./response.model";
import { environment } from "../../environments/environment";
import { Filtro } from "./filtro.model";

@Injectable()
export class ArticlesService {
  articles: Article[] = [];
  articlesChanged = new Subject<Article[]>();

  constructor(private http: HttpClient) {}

  getArticles() {
    return this.articles.slice();
  }

  addArticle(article: Article) {
    this.saveData(article).subscribe(
      (response) => {
        let savedArticle: Article = response.value;
        this.articles.push(savedArticle);
        this.articlesChanged.next(this.getArticles());
      },
      err => console.error(err)
    );
  }

  addArticles(articles: Article[]) {
    this.articles.push(...articles);
    // this.articlesChanged.next(this.getArticles());
  }

  updateArticle(index: number, newArticle: Article) {
    this.updateData(newArticle).subscribe(
      (response) => {
        let updatedArticle: Article = response.value;
        this.articles[index] = updatedArticle;
        this.articlesChanged.next(this.getArticles());
      }
    );
  }

  deleteArticle(index: number, articleId: string) {
    this.deleteData(articleId).subscribe(
      (response) => {
        let updatedArticle: Article = response.value;
        this.articles.splice(index, 1);
        this.articlesChanged.next(this.getArticles());
      }
    );
  }

  fetchData(filtro: Filtro, page: number, size: number) {
    let param = '';
    if (filtro) {
      param += '?' + filtro.getFiltro();
    }
    if (page) {
      param += (param ? '&' : '?') + 'page=' + page + '&size=' + size;
    }
    return this.http.get<ResponseRs>(environment.api_url + '/articulos' + param);
  }

  saveData(article: Article) {
    return this.http.post<ResponseRs>(environment.api_url + '/articulos', article);
  }

  updateData(article: Article) {
    return this.http.put<ResponseRs>(environment.api_url + '/articulos/' + article.codigo, article);
  }

  deleteData(articleId: string) {
    return this.http.delete<ResponseRs>(environment.api_url + '/articulos/' + articleId);
  }
}
