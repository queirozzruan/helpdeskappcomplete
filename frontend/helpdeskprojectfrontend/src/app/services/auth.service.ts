import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import { API_CONFIG } from "../config/api.config";
import { Credenciais } from "../models/credenciais";

@Injectable({
  providedIn: "root",
})

// Serviço responsável pela autenticação do usuário, gerenciamento do token JWT e verificação de roles
export class AuthService {
  jwtService: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {}

  authenticate(creds: Credenciais) {
    return this.http.post(`${API_CONFIG.baseUrl}/login`, creds, {
      observe: "response",
      responseType: "text",
    });
  }

  successfulLogin(authToken: string) {
    localStorage.setItem("token", authToken);
  }

  isAuthenticated() {
    let token = localStorage.getItem("token");
    if (token != null) {
      return !this.jwtService.isTokenExpired(token);
    }
    return false;
  }

  getRole(): string | null {
    const token = localStorage.getItem("token");

    if (!token) return null;

    const decoded = this.jwtService.decodeToken(token);

    // Se vier roles: ["ROLE_CLIENTE"]
    if (decoded.roles && decoded.roles.length > 0) {
      return decoded.roles[0].replace("ROLE_", "");
    }

    // Se vier role: "ROLE_CLIENTE"
    if (decoded.role) {
      return decoded.role.replace("ROLE_", "");
    }

    return null;
  }

  logout() {
    localStorage.clear();
  }
}
