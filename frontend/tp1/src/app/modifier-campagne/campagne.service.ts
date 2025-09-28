import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampagneService {
  // URL base correcte vers le backend
  private baseUrl = 'http://localhost:9090/api/campagnes';

  constructor(private http: HttpClient) {}

  getAllCampagnes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getCampaign`);
  }

  updateCampagne(id: number, campagneData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, campagneData);
  }

  createCampagne(campagne: any): Observable<any> {
    return this.http.post(this.baseUrl, campagne);
  }

  deleteCampagne(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getCampagneById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
