import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { User } from "@/_models";
import { map } from "rxjs/operators";

 const SUCCESS = 'success'

@Injectable({ providedIn: "root" })
export class UserService {
  url = `http://localhost:8072/self/service/api`
  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<User[]>(`${config.apiUrl}/users`);
  }

  register(user) {
    return this.http.post(`${this.url}/saveUser`, user);
  }

  delete(id: number) {
    return this.http.delete(`${config.apiUrl}/users/${id}`);
  }
  getAllQuestions() {
    return this.http.get(`${this.url}/fetchAllQuestions`).pipe(
      map((res: any) => {
        if (res?.status?.toLowerCase() === SUCCESS) {
          return res?.responseData;
        } else {
alert(res?.message ?? 'No record found.');
        }
       
      })
    );
  }
  resetPswd(postData){
    return this.http.post(`${this.url}/getAllAnswers`, postData).pipe(map((res: any) => {
      if (res?.status?.toLowerCase() === SUCCESS) {
        return res?.responseData
      } else {
        alert(res?.message);
        return false;
      }
    }))
  }
}
