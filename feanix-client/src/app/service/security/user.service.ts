import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:8081/api/v1/users/signup'

  constructor(private http: HttpClient) {
  }

  signup(username: any, password: any, address: any):Observable<any> {
    return this.http.post(this.baseUrl, {
      username: username,
      password: password,
      address: address,
    })
  }
}
