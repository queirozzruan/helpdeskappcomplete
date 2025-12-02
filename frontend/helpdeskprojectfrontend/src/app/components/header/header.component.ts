import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})

// Componente responsável pelo cabeçalho da aplicação
export class HeaderComponent implements OnInit {
  constructor(
    private router: Router,
    private authService: AuthService,
    private toast: ToastrService
  ) {}

  // Inicialização do componente
  ngOnInit(): void {}

  logout() {
    this.router.navigate(["login"]);
    this.authService.logout();
    this.toast.info("Logout realizado com sucesso", "Logout", {
      timeOut: 7000,
    });
  }
}
