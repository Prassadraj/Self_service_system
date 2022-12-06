import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '@/_models';
import { AlertService } from './alert.service';


export const SUCCESS = 'success'

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    url = `http://localhost:8072/self/service/api`

 
    public currentUser: any;

    constructor(private http: HttpClient, private alertService: AlertService) {
        
    }

   

    login(loginInfo) {
        return this.http.post<any>(`${this.url}/fetchUserDetails`, loginInfo)
            .pipe(map((user: any) => {
                if(user?.status?.toLowerCase() === SUCCESS) {

                // store user details and jwt token in local storage to keep user logged in between page refreshes
                // this.currentUserSubject.next(user);
                return user.responseData;
            } else {
alert(user?.message);
return null;
            }

            }));
    }

    logout() {
        // remove user from local storage and set current user to null
        // localStorage.removeItem('currentUser');
        // this.currentUserSubject.next(null);
    }
}